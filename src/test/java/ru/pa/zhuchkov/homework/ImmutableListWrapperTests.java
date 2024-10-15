package ru.pa.zhuchkov.homework;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImmutableListWrapperTests {
    @Test
    void testGet() {
        List<Integer> list = new ImmutableListWrapper<>(List.of(1, 2, 3));

        assertEquals(list.get(0), 1);
        assertEquals(list.get(1), 2);
        assertEquals(list.get(2), 3);
    }

    @Test
    void testIterate() {
        List<Integer> list = new ImmutableListWrapper<>(List.of(1, 2, 3));

        int i = 0;
        for (Integer e : list) {
            switch (i) {
                case 0 -> assertEquals(e, 1);
                case 1 -> assertEquals(e, 2);
                case 2 -> assertEquals(e, 3);
                default -> fail();
            }

            ++i;
        }
    }

    @Test
    void testImmutable() {
        List<Integer> list = new ImmutableListWrapper<>(new ArrayList<>(List.of(1, 2, 3)));
        assertThrows(UnsupportedOperationException.class, () -> list.add(0, 0));
        assertThrows(UnsupportedOperationException.class, () -> list.add(0));
        assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> list.sort(null));
        assertThrows(UnsupportedOperationException.class, () -> list.addAll(List.of(1, 2, 3)));
        assertThrows(UnsupportedOperationException.class, () -> list.removeAll(List.of(1, 2, 3)));
        assertThrows(UnsupportedOperationException.class, () -> list.retainAll(List.of(1, 2, 3)));
        assertThrows(UnsupportedOperationException.class, () -> list.replaceAll((x) -> x));
        assertThrows(UnsupportedOperationException.class, list::clear);
    }

    @Test
    void subList() {
        List<Integer> list = new ImmutableListWrapper<>(List.of(1, 2, 3));

        assertEquals(list.subList(1, 3).get(0), 2);
        assertEquals(list.subList(1, 3).get(1), 3);
    }

    @Test
    void copy() {
        List<Integer> wrapped = new ArrayList<>(List.of(1, 2, 3));
        ImmutableListWrapper<Integer> wrapper = new ImmutableListWrapper<>(wrapped);
        List<Integer> copy = wrapper.copy();

        assertEquals(copy.getClass(), wrapped.getClass());
        assertEquals(copy, wrapped);

        copy.set(0, 5);

        assertEquals(copy.get(0), 5);
        assertEquals(wrapped.get(0), 1);
    }

    @Test
    void copyRetainClass() {
        List<Integer> wrapped = new LinkedList<>(List.of(1, 2, 3));
        ImmutableListWrapper<Integer> wrapper = new ImmutableListWrapper<>(wrapped);
        List<Integer> copy = wrapper.copy();

        assertEquals(copy.getClass(), wrapped.getClass());
        assertEquals(copy, wrapped);

        copy.set(0, 5);

        assertEquals(copy.get(0), 5);
        assertEquals(wrapped.get(0), 1);
    }

    @Test
    void copyFallback() {
        List<Integer> wrapped = List.of(1, 2, 3);
        ImmutableListWrapper<Integer> wrapper = new ImmutableListWrapper<>(wrapped);
        List<Integer> copy = wrapper.copy();

        assertEquals(copy.getClass(), ArrayList.class);
        assertEquals(copy, wrapped);

        copy.set(0, 5);

        assertEquals(copy.get(0), 5);
        assertEquals(wrapped.get(0), 1);
    }
}
