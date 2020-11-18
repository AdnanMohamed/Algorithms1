import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    /* #######  CLASS INVARIANT  #########
        1- data[] stores the items on the queue.
        2- size, the number of items on the queue.
        3- INIT_SIZE, the initial size of the queue.
        4- RESIZE_RATE - data[] array is resized by RESIZE_RATE
           whenever data needs more space.
        5- LOW_BOUND- is the threshold for the ratio of the number of items to
           the size of the array data. if size()/data.length < LOW_BOUND then
           data[] is resized by half.
     */

    private Item[] data;
    private int size;
    private final static int INIT_SIZE = 4;
    private final static double RESIZE_RATE = 2;
    private final static double LOW_BOUND = 0.25;
    private final Random rand = new Random();


    // construct an empty randomized queue
    public RandomizedQueue() {
        data = (Item[]) new Object[INIT_SIZE];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == data.length) {
            resize(RESIZE_RATE);
        }

        data[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No items to be dequeued!");
        }

        int index = rand.nextInt(size);
        swap(data, index, size-1);
        Item item = data[--size];
        data[size] = null;

        if (size != 0 && (size() / (double)data.length) < LOW_BOUND) {
            resize(0.5);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("No items to be dequeued!");
        }
        return data[rand.nextInt(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QIterator();
    }

    /*  #### HELPER METHODS. #### */

    /** Resizes data by 'factor'. (i.e. data.length
     *  becomes data.length *= factor.
     */
    private void resize(double factor) {
        int newSize = (int)(data.length * factor);
        Item[] copyData = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            copyData[i] = data[i];
        }
        data = copyData;
    }

    private void swap(Item[] arr, int i, int j) {
        Item tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private class QIterator implements Iterator<Item> {
        private Item[] items = (Item[]) new Object[size];
        private int currentIndex;

        public QIterator() {
            for (int i = 0; i < size; ++i) {
                items[i] = data[i];
            }
            StdRandom.shuffle(items);
        }

        @Override
        public boolean hasNext() {
            return currentIndex < items.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[currentIndex++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(266);
        rq.dequeue();
        rq.size();
        rq.enqueue(363);
        rq.dequeue();
        rq.size();
        rq.size();
        rq.size();
        rq.enqueue(316);

        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        for (int i = 0; i < 5; ++i) {
            q.enqueue(i);
        }
        System.out.println("Size = " + q.size());
        if (!q.isEmpty()) {
            System.out.println("The Queue is not Empty!");
        } else {
            System.out.println("The queue is empty.");
        }
        System.out.println("Printing the queue:");
        for (int i : q) {
            System.out.println(i);
        }
        while (!q.isEmpty()) {
            q.dequeue();
        }
        System.out.println("Add 77");
        q.enqueue(77);
        System.out.println("Dequeue 77:");
        System.out.println(q.dequeue());
    }

}