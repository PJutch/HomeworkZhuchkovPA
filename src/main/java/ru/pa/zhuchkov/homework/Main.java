package ru.pa.zhuchkov.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Integer> readList(BufferedReader br) throws IOException {
        String lines = br.readLine();
        String[] strs = lines.trim().split("\\s+");

        List<Integer> result = new ArrayList<>(strs.length);
        for (String str : strs) {
            result.add(Integer.parseInt(str));
        }
        return result;
    }

    public static void printAlgorithmTypes() {
        boolean hasPrevious = false;
        for (Sorter.Algorithm algorithm : Sorter.Algorithm.values()) {
            if (hasPrevious) {
                System.out.print(", ");
            }
            System.out.print(algorithm.name().toLowerCase());
            if (algorithm == Sorter.Algorithm.ANY) {
                System.out.print(" - default");
            }

            hasPrevious = true;
        }
    }

    public static void main(String[] args) throws IOException {
        final Sorter sorter = new Sorter(new BubbleSorterStrategy(), new BuiltinSorterStrategy());

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Input list to sort (one line): ");
        List<Integer> list = readList(br);

        System.out.print("Input sorting algorithm (");
        printAlgorithmTypes();
        System.out.print("): ");

        String algorithmName = br.readLine().trim();
        Sorter.Algorithm algorithm = Sorter.Algorithm.ANY;
        try {
            if (!algorithmName.isEmpty()) {
                algorithm = Sorter.Algorithm.valueOf(algorithmName.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            System.out.print("Unknown algorithm: " + algorithmName);
            return;
        }

        System.out.print(sorter.sort(list, algorithm));
    }
}
