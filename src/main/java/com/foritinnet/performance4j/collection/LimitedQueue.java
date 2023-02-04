package com.foritinnet.performance4j.collection;

import java.util.ArrayDeque;
import java.util.Collection;

public class LimitedQueue<T> extends ArrayDeque<T> {

    private final int SIZE;

    public LimitedQueue(int size) {
        super(size);

        SIZE = size;
    }

    @Override
    public boolean add(T t) {

        if(size() == SIZE) {
            super.removeFirst();
        }

        return super.add(t);
    }

    @Override
    public void addFirst(T t) {

        if(size() == SIZE) {
            super.removeLast();
        }

        super.addFirst(t);
    }

    @Override
    public void addLast(T t) {

        if(size() == SIZE) {
            super.removeFirst();
        }

        super.addLast(t);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {

        for(T element: c) {
            add(element);
        }

        return true;
    }
}
