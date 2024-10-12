package ru.pa.zhuckov.homework;

public class Main {
    public static void main(String[] args) {
        System.out.println("Empty creation: ");
        CustomArrayList<String> list1 = new CustomArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        System.out.println(list1);

        System.out.println("Vararg constructor: ");
        CustomArrayList<Integer> list2 = new CustomArrayList<>(1, 2, 3);
        System.out.println(list2);

        System.out.println("Add to the end: ");
        list2.add(4);
        System.out.println(list2);

        System.out.println("Get: ");
        System.out.println(list2.get(1));

        System.out.println("Remove from the middle: ");
        System.out.println(list2.remove(1));
        System.out.println(list2);
        System.out.println(list2.get(1));

        System.out.println("Remove from the end: ");
        System.out.println(list2.remove(2));
        System.out.println(list2);

        System.out.println("Add in the middle: ");
        list2.add(1, 2);
        System.out.println(list2);

        System.out.println("Boundary check: ");
        System.out.println(list2.get(3));
    }
}
