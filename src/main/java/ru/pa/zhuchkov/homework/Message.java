package ru.pa.zhuchkov.homework;

import java.util.Map;

public record Message(Map<String, String> content, EnrichmentType enrichmentType) {
    public enum EnrichmentType {
        MSISDN
    }
}
