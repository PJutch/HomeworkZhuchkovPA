package ru.pa.zhuchkov.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BuiltinSorterStrategyTests {
    @Test
    void sort() {
        final BuiltinSorterStrategy strategy = new BuiltinSorterStrategy();

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
        final BuiltinSorterStrategy strategy = new BuiltinSorterStrategy();

        final List<Integer> expected = List.of(1, 2, 3);
        final List<Integer> actual = strategy.sort(List.of(3, 1, 2));

        assertEquals(expected, actual);
    }

    @Test
    void sortEmpty() {
        final BuiltinSorterStrategy strategy = new BuiltinSorterStrategy();

        final List<Integer> expected = List.of();
        final List<Integer> actual = strategy.sort(List.of());

        assertEquals(expected, actual);
    }
}
