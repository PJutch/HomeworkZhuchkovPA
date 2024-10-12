package ru.pa.zhuckov.homework;

import java.util.Arrays;

/**
 * Минималистичная реализация минималистичного списка.
 * Использует массив для хранения элементов.
 *
 * @param <T> тип элемента
 * @see java.util.ArrayList Более полный аналог в стандартной библиотеке
 */
public class CustomArrayList<T> implements CustomList<T> {
    private T[] values;
    private int length;

    /**
     * Создаёт список со значениями из values
     * Время работы - O(n)
     *
     * @param values значения, которыми будет инициализирован список
     */
    @SafeVarargs
    public CustomArrayList(T... values) {
        this.values = values;
        length = this.values.length;
    }

    /**
     * Добавляет value в конец списка
     * Время работы - O(n), амортизированное O(1)
     *
     * @param value значение элемента, добавленного в конец списка
     * @throws IllegalArgumentException если value == null
     */
    @Override
    public void add(T value) throws IllegalArgumentException {
        add(length, value);
    }

    /**
     * Добавляет value по индексу
     * Время работы - O(n)
     *
     * @param index индекс, который получит новй элемент
     *              (т. е. вставка <b>перед</b> index).
     *              Если index == length, вставка в конец
     * @param value значение элемента, добавленного в конец списка
     * @throws IndexOutOfBoundsException если индекс вне [index, length]
     * @throws IllegalArgumentException если value == null
     */
    @Override
    public void add(int index, T value)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        if (value == null) {
            throw new NullPointerException("null values aren't allowed in CustomArrayList");
        }

        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException(index);
        }

        if (length >= values.length) {
            values = Arrays.copyOf(values, Math.max(2 * values.length, 1));
        }

        for (int i = index; i < length; ++i) {
            values[i + 1] = values[i];
        }
        values[index] = value;
        ++length;
    }

    /**
     * Получает элемент с индексом index
     * Время работы - O(1)
     *
     * @param index индекс элемента (начиная с 0)
     * @return значение элемента
     * @throws IndexOutOfBoundsException если index вне [0, length)
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(index);
        }
        return values[index];
    }

    /**
     * Удаляет элемент с индексом index
     * Время работы - O(n)
     * Время удаления в конце - O(1)
     *
     * @param index индекс удаляемого элемента (начиная с 0)
     * @return значение удалённого элемента
     * @throws IndexOutOfBoundsException если index вне [0, length)
     * @implNote не уменьшает выделенный массив
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(index);
        }

        T oldValue = values[index];
        values[index] = null;
        Arrays.sort(values, index, values.length, (T o1, T o2) -> {
            if (o1 == null && o2 == null) return 0;
            if (o1 == null) return 1;
            if (o2 == null) return -1;
            return 0;
        });
        --length;

        return oldValue;
    }

    /**
     * Выводит все элементы.
     * Время работы - O(n)
     *
     * @return строку с toString всех элементов
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(values, length));
    }
}
