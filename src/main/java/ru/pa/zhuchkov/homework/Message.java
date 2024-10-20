package ru.pa.zhuchkov.homework;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private final Map<String, String> content;
    private final EnrichmentType enrichmentType;

    public enum EnrichmentType {
        MSISDN;
    }

    public Message(Map<String, String> content, EnrichmentType enrichmentType) {
        this.content = content;
        this.enrichmentType = enrichmentType;
    }

    public Message(Message message) {
        content = message.content;
        enrichmentType = message.enrichmentType;
    }

    public String get(String key) {
        return content.get(key);
    }

    public void put(String key, String value) {
        content.put(key, value);
    }

    public EnrichmentType enrichmentType() {
        return enrichmentType;
    }
}
