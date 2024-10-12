package ru.pa.zhuchkov.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.awt.image.AreaAveragingScaleFilter;
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

    @Test
    void fallbackSort() {
        final Sorter sorter = new Sorter();
        sorter.addStrategy(new BubbleSorterStrategy());
        sorter.addStrategy(new BuiltinSorterStrategy());

        final int LIST_SIZE = 11000;
        List<Integer> sorted = new ArrayList<>(LIST_SIZE);
        for (int i = 0; i < LIST_SIZE; i++) {
            sorted.add(i);
        }

        List<Integer> reversed = new ArrayList<>(LIST_SIZE);
        for (int i = LIST_SIZE - 1; i >= 0; i--) {
            reversed.add(i);
        }

        assertEquals(sorted, sorter.sort(reversed));
    }

    @Test
    void allSortsFailed() {
        final Sorter sorter = new Sorter();
        sorter.addStrategy(new BubbleSorterStrategy());

        final int LIST_SIZE = 11000;
        List<Integer> list = new ArrayList<>(LIST_SIZE);
        for (int i = LIST_SIZE - 1; i >= 0; i--) {
            list.add(i);
        }

        assertThrows(IllegalArgumentException.class, () -> {
            sorter.sort(list);
        });
    }
}
