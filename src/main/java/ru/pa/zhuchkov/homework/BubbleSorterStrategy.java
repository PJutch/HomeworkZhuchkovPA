package ru.pa.zhuchkov.homework;

import java.util.ArrayList;
import java.util.List;

public class BubbleSorterStrategy implements Sorter.Strategy {
    @Override
    public List<Integer> sort(List<Integer> list) {
        List<Integer> result = new ArrayList<>(list);
        for (int iteration = 0; iteration < result.size(); ++iteration) {
            for (int i = 0; i + 1 < result.size(); ++i) {
                if (result.get(i) > result.get(i + 1)) {
                    int temp = result.get(i);
                    result.set(i, result.get(i + 1));
                    result.set(i + 1, temp);
                }
            }
        }
        return result;
    }
}
