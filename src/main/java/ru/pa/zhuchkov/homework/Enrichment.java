package ru.pa.zhuchkov.homework;

import java.util.HashMap;
import java.util.Map;

public class Enrichment {
    public Map<String, String> enrich(Map<String, String> input) {
        Map<String, String> result = new HashMap<>(input);
        result.put("firstName", "Vasya");
        result.put("lastName", "Ivanov");
        return result;
    }
}
