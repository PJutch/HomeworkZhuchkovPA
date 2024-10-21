package ru.pa.zhuchkov.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MsisdnEnrichmentTest {
    @Test
    void enrich() {
        UserRepository repository = new UserRepository() {
            @Override
            public User findByMsisdn(String msisdn) {
                return new User("Vasya", "Ivanov");
            }

            @Override
            public void updateUserByMsisdn(String msisdn, User user) {
                throw new UnsupportedOperationException();
            }
        };

        MsisdnEnrichment enrichment = new MsisdnEnrichment(repository);

        Map<String, String> expected = Map.of(
                "test", "some data",
                "msisdn", "some msisdn",
                "firstName", "Vasya",
                "lastName", "Ivanov"
        );
        Map<String, String> actual = enrichment.enrich(new HashMap<>(Map.of(
                "test", "some data",
                "msisdn", "some msisdn"
        )));

        assertEquals(expected, actual);
    }

    @Test
    void enrichNotFound() {
        UserRepository repository = new UserRepository() {
            @Override
            public User findByMsisdn(String msisdn) {
                return null;
            }

            @Override
            public void updateUserByMsisdn(String msisdn, User user) {
                throw new UnsupportedOperationException();
            }
        };

        MsisdnEnrichment enrichment = new MsisdnEnrichment(repository);

        Map<String, String> content = new HashMap<>(Map.of(
                "test", "some data",
                "msisdn", "some msisdn"
        ));

        Map<String, String> expected = content;
        Map<String, String> actual = enrichment.enrich(content);

        assertEquals(expected, actual);
    }

    @Test
    void enrichNoMsisdn() {
        MsisdnEnrichment enrichment = new MsisdnEnrichment(null);

        Map<String, String> content = new HashMap<>(Map.of(
                "test", "some data"
        ));

        Map<String, String> expected = content;
        Map<String, String> actual = enrichment.enrich(content);

        assertEquals(expected, actual);
    }
}
