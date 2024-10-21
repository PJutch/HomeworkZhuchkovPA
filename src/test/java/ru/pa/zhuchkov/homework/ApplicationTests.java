package ru.pa.zhuchkov.homework;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    @Test
    void enrich() {
        UserRepository repository = new HashMapUserRepository();
        repository.updateUserByMsisdn("88005553535",
                new UserRepository.User("Vasya", "Ivanov"));
        repository.updateUserByMsisdn("88005553727",
                new UserRepository.User("Petya", "Petrov"));

        EnrichmentService service = new EnrichmentService();
        service.addEnrichment(Message.EnrichmentType.MSISDN, new MsisdnEnrichment(repository));

        Message message = new Message(
                Map.of("test", "some data", "msisdn", "88005553535"),
                Message.EnrichmentType.MSISDN);

        Message expected = new Message(Map.of(
                "test", "some data",
                "msisdn", "88005553535",
                "firstName", "Vasya",
                "lastName", "Ivanov"
        ), Message.EnrichmentType.MSISDN);
        Message actual = service.enrich(message);

        assertEquals(expected, actual);
    }

    @Test
    void enrichConcurrent() throws InterruptedException, ExecutionException {
        UserRepository repository = new HashMapUserRepository();
        repository.updateUserByMsisdn("88005553535",
                new UserRepository.User("Vasya", "Ivanov"));
        repository.updateUserByMsisdn("88005553727",
                new UserRepository.User("Petya", "Petrov"));

        EnrichmentService service = new EnrichmentService();
        service.addEnrichment(Message.EnrichmentType.MSISDN, new MsisdnEnrichment(repository));

        List<Map<String, String>> contents = List.of(
                Map.of("test", "data1", "msisdn", "88005553535"),
                Map.of("test", "data2", "msisdn", "88005553727"),
                Map.of("test", "data3", "msisdn", "88005553535"),
                Map.of("test", "data4", "msisdn", "88005553727"),
                Map.of("test", "data5")
        );

        List<Message> expected = List.of(
                new Message(Map.of(
                        "test", "data1",
                        "msisdn", "88005553535",
                        "firstName", "Vasya",
                        "lastName", "Ivanov"), Message.EnrichmentType.MSISDN),
                new Message(Map.of(
                        "test", "data2",
                        "msisdn", "88005553727",
                        "firstName", "Petya",
                        "lastName", "Petrov"), Message.EnrichmentType.MSISDN),
                new Message(Map.of(
                        "test", "data3",
                        "msisdn", "88005553535",
                        "firstName", "Vasya",
                        "lastName", "Ivanov"), Message.EnrichmentType.MSISDN),
                new Message(Map.of(
                        "test", "data4",
                        "msisdn", "88005553727",
                        "firstName", "Petya",
                        "lastName", "Petrov"), Message.EnrichmentType.MSISDN),
                new Message(Map.of("test", "data5"), Message.EnrichmentType.MSISDN)
        );

        List<Message> actual = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 5; ++i) {
            actual.add(i, new Message(null, null));
        }

        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        ArrayList<Future<?>> futures = new ArrayList<>(5);
        for (int i_ = 0; i_ < 5; i_++) {
            final int i = i_;
            futures.add(executorService.submit(() -> {
                Message message = new Message(contents.get(i),
                        Message.EnrichmentType.MSISDN);

                actual.set(i, service.enrich(message));
            }));
        }

        for (Future<?> future : futures) {
            future.get();
        }

        assertEquals(expected, actual);
    }
}
