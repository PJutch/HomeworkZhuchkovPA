package ru.pa.zhuchkov.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BuiltinSorterStrategyTests {
    private final BuiltinSorterStrategy strategy = new BuiltinSorterStrategy();

    @Test
    void sort() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(2);
        list.add(1);

        assertEquals(List.of(1, 2, 3), strategy.sort(list));
    }

    @Test
    void sortImmutable() {
        assertEquals(List.of(1, 2, 3), strategy.sort(List.of(3, 1, 2)));
    }
}
