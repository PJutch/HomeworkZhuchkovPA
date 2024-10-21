package ru.pa.zhuchkov.homework;

import java.util.HashMap;
import java.util.Map;

public class EnrichmentService {
    private final HashMap<Message.EnrichmentType, Enrichment> enrichments = new HashMap<>();

    public interface Enrichment {
        Map<String, String> enrich(Map<String, String> input);
    }

    public void addEnrichment(Message.EnrichmentType type, Enrichment enrichment) {
        enrichments.put(type, enrichment);
    }

    public Message enrich(Message message) {
        Enrichment enrichment = enrichments.get(message.enrichmentType());
        if (enrichment == null) {
            return message;
        }

        Map<String, String> newContent = enrichment.enrich(new HashMap<>(message.content()));
        return new Message(newContent, message.enrichmentType());
    }
}
