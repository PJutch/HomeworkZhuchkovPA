package ru.pa.zhuchkov.homework;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ImmutableListWrapper<E> implements List<E> {
    private final List<E> wrapped;

    private class ImmutableListIteratorWrapper implements ListIterator<E> {
        private final ListIterator<E> wrapped;

        public ImmutableListIteratorWrapper(ListIterator<E> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public boolean hasNext() {
            return wrapped.hasNext();
        }

        @Override
        public E next() {
            return wrapped.next();
        }

        @Override
        public boolean hasPrevious() {
            return wrapped.hasPrevious();
        }

        @Override
        public E previous() {
            return wrapped.previous();
        }

        @Override
        public int nextIndex() {
            return wrapped.nextIndex();
        }

        @Override
        public int previousIndex() {
            return wrapped.previousIndex();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
        }
    }

    public ImmutableListWrapper(List<E> wrapped) {
        this.wrapped = wrapped;
    }

    public int size() {
        return wrapped.size();
    }

    public boolean isEmpty() {
        return wrapped.isEmpty();
    }

    public boolean contains(Object o) {
        return wrapped.contains(o);
    }

    public Iterator<E> iterator() {
        return new ImmutableListIteratorWrapper(wrapped.listIterator());
    }

    public Object[] toArray() {
        return wrapped.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return wrapped.toArray(a);
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    public boolean containsAll(Collection<?> c) {
        return wrapped.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    public E get(int index) {
        return wrapped.get(index);
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("ImmutableListWrapper is immutable");
    }

    public int indexOf(Object o) {
        return wrapped.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return wrapped.lastIndexOf(o);
    }

    public ListIterator<E> listIterator() {
        return new ImmutableListIteratorWrapper(wrapped.listIterator());
    }

    public ListIterator<E> listIterator(int index) {
        return new ImmutableListIteratorWrapper(wrapped.listIterator(index));
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return new ImmutableListWrapper<>(wrapped.subList(fromIndex, toIndex));
    }

    public List<E> copy() {
        List<E> copy;
        try {
            //> All general-purpose Collection implementation classes
            //> should provide two "standard" constructors: a void (no arguments) constructor,
            //> which creates an empty collection,
            //> and *a constructor with a single argument of type Collection*,
            //> which creates a new collection with the same elements as its argument.
            copy = (List<E>)
                    wrapped.getClass().getConstructor(Collection.class).newInstance(wrapped);
        } catch (NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException e) {
            return new ArrayList<>(wrapped);
        }

        try {
            // Проверяем, что копия изменяема
            copy.retainAll(copy);
            return copy;
        } catch (UnsupportedOperationException ignored) {
            return new ArrayList<>(copy);
        }
    }
}
