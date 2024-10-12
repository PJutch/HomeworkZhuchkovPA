package ru.pa.zhuchkov.homework;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BubbleSorterStrategyTests {
    @Test
    void sort() {
        final BubbleSorterStrategy strategy = new BubbleSorterStrategy();

        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(1);

        assertEquals(List.of(1, 2, 3), strategy.sort(list));
    }

    @Test
    void sortImmutable() {
        final BubbleSorterStrategy strategy = new BubbleSorterStrategy();

        assertEquals(List.of(1, 2, 3), strategy.sort(List.of(3, 1, 2)));
    }
}
