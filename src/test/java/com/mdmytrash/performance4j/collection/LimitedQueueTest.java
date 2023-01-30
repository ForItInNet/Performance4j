package com.mdmytrash.performance4j.collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LimitedQueueTest {

    private final int SIZE = 5;

    private final LimitedQueue<Integer> queue = new LimitedQueue<>(SIZE);

    @AfterEach
    public void doAfterEach() {
        queue.clear();
    }

    @Test
    void add() {

        for(int i = 0; i <= 5; ++i) {
            queue.add(i);
        }

        Assertions.assertEquals(SIZE, queue.size());
        Assertions.assertEquals(1, queue.getFirst());
    }

    @Test
    void addFirst() {

        for(int i = 0; i < 5; ++i) {
            queue.add(i);
        }

        queue.addFirst(5);

        Assertions.assertEquals(SIZE, queue.size());
        Assertions.assertEquals(3, queue.getLast());
    }

    @Test
    void addLast() {

        for(int i = 0; i < 5; ++i) {
            queue.add(i);
        }

        queue.addLast(5);

        Assertions.assertEquals(SIZE, queue.size());
        Assertions.assertEquals(1, queue.getFirst());
    }

    @Test
    void addAll() {

        for(int i = 0; i < 5; ++i) {
            queue.add(i);
        }

        queue.addAll(Set.of(11));

        Assertions.assertEquals(SIZE, queue.size());
        Assertions.assertEquals(1, queue.getFirst());
    }
}