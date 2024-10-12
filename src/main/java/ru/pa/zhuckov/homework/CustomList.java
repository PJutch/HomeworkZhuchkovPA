package ru.pa.zhuckov.homework;

/**
 * Минималистичный упорядоченный список.
 * Не поддерживает итераторы.
 *
 * @param <T> тип элемента
 * @see java.util.List Более полный аналог в стандартной библиотеке
 */
public interface CustomList<T> {
    /**
     * Добавляет элемент в конец
     *
     * @param value значение нового элемента
     * @throws IllegalArgumentException если value null и в коллекцию нельзя добавить null
     */
    void add(T value) throws IllegalArgumentException;

    /**
     * Добавляет элемент по индексу.
     * Старые элементы сдвигаются вправо
     *
     * @param index индекс, который получит новй элемент
     *              (т. е. вставка <b>перед</b> index).
     *              Если index == length, вставка в конец
     * @param value значение нового элемента
     * @throws IndexOutOfBoundsException если индекс вне [0, length]
     * @throws IllegalArgumentException если value null и в коллекцию нельзя добавить null
     */
    void add(int index, T value) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * Получает значение элемента с данным индексом
     *
     * @param index индекс этого элемента (начиная с 0)
     * @return значение элемента с индексом index
     * @throws IndexOutOfBoundsException если index вне [0, length)
     */
    T get(int index) throws IndexOutOfBoundsException;

    /**
     * Удаляет элемент с данным индексом
     *
     * @param index индекс этого элемента (начиная с 0)
     * @return значение удалённого элемента
     * @throws IndexOutOfBoundsException если index вне [0, length)
     */
    T remove(int index) throws IndexOutOfBoundsException;
}
