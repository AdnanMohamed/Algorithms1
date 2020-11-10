package StackQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions - Stacks and Queues
 *
 * Question 1: Queue with two stacks
 *
 * Implement a queue with two stacks so that each queue operations takes a
 * constant amortized number of stack operations.
 */
public class Queue<T> implements Iterable<T>{
    private final Deque<T> s1, s2;  // represents stack 1, and stack 2 respectively

    public Queue() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }

    /** puts the item in the queue. */
    public void enqueue(T item) {
        s1.push(item);
    }

    /** Returns the first item entered in this queue. */
    public T dequeue() {
        if (s2.isEmpty()) {
            // Preserves FIFO order.
            // See documentation of moveItems() below.
            moveItems();
        }
        return s2.pop();
    }

    public int size() { return s1.size() + s2.size(); }

    public boolean isEmpty() { return size() == 0; }

    public QueueIterator<T> iterator() {
        return new QueueIterator<>();
    }

    //////  CLASS TO HAVE THE foreach FEATURE.  /////

    /** Returns the items in FIFO order. */
    private class QueueIterator<T> implements Iterator<T> {
        Iterator<T> it;

        public QueueIterator() {
            if (s2.isEmpty()) {
                moveItems();
            }
            it = (Iterator<T>) s2.iterator();
        }

        public boolean hasNext() {
            return it.hasNext();
        }

        public T next() {
            return it.next();
        }
    }

    /* arranges the items to be dequeued in
       FIFO order. Because we enqueued the elements
       in s1 by push operation, then to get the FIFO
       order we are popping the elements to s2. But this
       is only done when s2 is empty and this is done to
       preserve order.*/
    private void moveItems() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
}
