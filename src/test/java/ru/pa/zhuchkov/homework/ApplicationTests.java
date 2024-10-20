package ru.pa.zhuchkov.homework;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ApplicationTest {

    @Test
    void enrich() {
        Message message = new Message();
        EnrichmentService service = new EnrichmentService();

        Message enrichedMessage = service.enrich(message);

        // Проверить обогащенное сообщение на соответствие определенным условиям
    }

    @Test
    void enrichConcurrent() throws InterruptedException {
        EnrichmentService app = new EnrichmentService();
        List<Message> enrichmentResults = new CopyOnWriteArrayList<>();
        try (final ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            CountDownLatch latch = new CountDownLatch(5);
            for (int i = 0; i < 5; i++) {
                executorService.submit(() -> {
                    enrichmentResults.add(
                            app.enrich(new Message()) // message где-то создается
                    );
                    latch.countDown();     // уменьшаем значение latch на 1
                });
            }
            latch.await(); // ждем, пока latch не станет равным 0, то есть пока не закончат работу все джобы в цикле
        }
        // проверяем валидность полученных сообщений в enrichmentResult
    }
}
