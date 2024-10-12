package ru.pa.zhuchkov.homework;

import java.util.ArrayList;
import java.util.List;

public class BuiltinSorterStrategy implements Sorter.Strategy {
    @Override
    public List<Integer> sort(List<Integer> list) {
        List<Integer> result = new ArrayList<>(list);
        result.sort(null);
        return result;
    }

    @Override
    public Sorter.Algorithm algorithm() {
        return Sorter.Algorithm.MERGE;
    }
}
