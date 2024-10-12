package ru.pa.zhuckov.homework;

import java.util.ArrayList;
import java.util.List;

public class Sorter {
    public interface Strategy {
        List<Integer> sort(List<Integer> list);
    }

    // Список вместо цепочки ответственности,
    // т. к. я не хочу чтобы стратегия знала, что её используют не одну
    private final List<Strategy> strategies = new ArrayList<>();

    public Sorter() {}

    public void addStrategy(Strategy strategy) {
        strategies.add(strategy);
    }

    List<Integer> sort(List<Integer> list) throws IllegalStateException {
        RuntimeException lastException = null;
        for (Strategy strategy : strategies) {
            try {
                return strategy.sort(list);
            } catch (RuntimeException exception) {
                lastException = exception;
            }
        }

        if (lastException != null) {
            throw lastException;
        } else {
            throw new IllegalStateException("Sorter requires at least one strategy to be provided");
        }
    }
}
