package ru.pa.zhuchkov.homework;

import java.util.List;

public class BuiltinSorterStrategy implements Sorter.Strategy {
    @Override
    public List<Integer> sort(ImmutableListWrapper<Integer> list) {
        List<Integer> result = list.copy();
        result.sort(null);
        return result;
    }

    public List<Integer> sort(List<Integer> list) {
        return sort(new ImmutableListWrapper<>(list));
    }

    @Override
    public Sorter.Algorithm algorithm() {
        return Sorter.Algorithm.MERGE;
    }
}
