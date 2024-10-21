package ru.pa.zhuchkov.homework;

import java.util.Map;

public class MsisdnEnrichment {
    private final UserRepository repository;

    MsisdnEnrichment(UserRepository repository) {
        this.repository = repository;
    }

    public Map<String, String> enrich(Map<String, String> input) {
        String msisdn = input.get("msisdn");
        if (msisdn == null) {
            return input;
        }

        UserRepository.User user = repository.findByMsisdn(msisdn);
        if (user == null) {
            return input;
        }

        input.put("firstName", user.firstName());
        input.put("lastName", user.lastName());
        return input;
    }
}
