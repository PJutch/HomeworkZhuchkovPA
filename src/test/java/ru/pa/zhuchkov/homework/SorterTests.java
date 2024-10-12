package ru.pa.zhuchkov.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SorterTests {
    @Test
    void sort() {
        final Sorter sorter = new Sorter();
        sorter.addStrategy(new BuiltinSorterStrategy());

        assertEquals(List.of(1, 2, 3), sorter.sort(List.of(3, 2, 1)));
    }

    @Test
    void multipleSorts() {
        final Sorter sorter = new Sorter();
        sorter.addStrategy(new BuiltinSorterStrategy());
        sorter.addStrategy(new BubbleSorterStrategy());

        final List<Integer> list = new ArrayList<>();

        assertEquals(List.of(1, 2, 3), sorter.sort(List.of(3, 1, 2)));
    }

    @Test
    void noSorts() {
        final Sorter sorter = new Sorter();
        assertThrows(IllegalStateException.class, () -> {
            sorter.sort(List.of(3, 2, 1));
        });
    }
}
