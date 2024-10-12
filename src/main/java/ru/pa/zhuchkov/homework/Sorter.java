package ru.pa.zhuchkov.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Sorter {
    public enum Algorithm {
        ANY,
        BUBBLE,
        MERGE
    }

    public interface Strategy {
        List<Integer> sort(List<Integer> list);
        Algorithm algorithm();
    }

    // Список вместо цепочки ответственности,
    // т. к. я не хочу чтобы стратегия знала, что её используют не одну
    private final List<Strategy> strategies = new ArrayList<>();

    public Sorter(Strategy... strategies) {
        this.strategies.addAll(List.of(strategies));
    }

    public void addStrategy(Strategy strategy) {
        strategies.add(strategy);
    }

    public List<Integer> sort(List<Integer> list, Algorithm type)
            throws IllegalStateException {
        RuntimeException lastException = null;
        for (Strategy strategy : strategies) {
            if (type != Algorithm.ANY && strategy.algorithm() != type) {
                continue;
            }

            try {
                return strategy.sort(list);
            } catch (RuntimeException exception) {
                lastException = exception;
            }
        }

        if (lastException != null) {
            throw lastException;
        }
        throw new NoSuchElementException("No strategy with provided algorithm exists");
    }

    public List<Integer> sort(List<Integer> list) throws IllegalStateException {
        return sort(list, Algorithm.ANY);
    }
}
