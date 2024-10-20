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

        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = strategy.sort(list);

        assertEquals(expected, actual);
    }

    @Test
    void sortImmutable() {
        final BubbleSorterStrategy strategy = new BubbleSorterStrategy();

        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = strategy.sort(List.of(3, 1, 2));

        assertEquals(expected, actual);
    }

    @Test
    void sortEmpty() {
        final BubbleSorterStrategy strategy = new BubbleSorterStrategy();

        final List<Integer> expected = List.of();
        final List<Integer> actual = strategy.sort(List.of());

        assertEquals(expected, actual);
    }
}
