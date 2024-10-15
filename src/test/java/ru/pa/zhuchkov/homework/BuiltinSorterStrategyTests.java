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

        assertEquals(List.of(1, 2, 3), strategy.sort(list));
    }

    @Test
    void sortImmutable() {
        final BuiltinSorterStrategy strategy = new BuiltinSorterStrategy();
        assertEquals(List.of(1, 2, 3), strategy.sort(List.of(3, 1, 2)));
    }

    @Test
    void sortEmpty() {
        final BuiltinSorterStrategy strategy = new BuiltinSorterStrategy();
        assertEquals(List.<Integer>of(), strategy.sort(List.<Integer>of()));
    }
}
