package ru.pa.zhuchkov.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SorterTests {
    @Test
    void sort() {
        final Sorter sorter = new Sorter();
        sorter.addStrategy(new BuiltinSorterStrategy());

        final List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(1);

        assertEquals(List.of(1, 2, 3), sorter.sort(list));
    }
}
