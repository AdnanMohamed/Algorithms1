package StackQueue;
import java.util.NoSuchElementException;

/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions - Stacks and Queues
 *
 * Question 2: Stack with max
 *
 * Create a data structure that efficiently supports the stack operations
 * (push and pop) and also a return-the-maximum operation. Assume the elements
 * are reals numbers so that you can compare them.
 */

public class StackWithMax<T extends Comparable<T>> {

    /* ####  CLASS INVARIANT  ####
       1- data[] is the array holding the items on the stack.
       2- SIZE - initial data[] size.
       3- RESIZE_RATE - data[] array is resized by RESIZE_RATE
          whenever data is full and push() cannot push the item on the stack.
       4- LOW_BOUND- is the threshold for the ratio of the number of items to
          the size of the array data. if size()/data.length < LOW_BOUND then
          data[] is resized by half.
       5- size-  number of items on the stack.
     */


    private T[] data;
    private final int SIZE = 4;  // initial array size.
    private final double RESIZE_RATE = 2;
    private final double LOW_BOUND = 0.25;
    private int size;

    public StackWithMax() {
        data = (T[]) new Comparable[SIZE];
    }

    /** pushes item on the stack. */
    public void push(T item) {
        if (size == data.length) {
            resize(RESIZE_RATE);
        }
        data[size++] = item;

    }

    /** Returns and removes the top item on the stack iff the stack is NOT empty.
     *  Throws NoSuchElementException iff the stack is empty.
     */
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("The stack is empty!");
        }
        T top = data[--size];
        if ((size() / (double)data.length) < LOW_BOUND) {
            resize(0.5);
        }
        return top;
    }

    /** Returns -without removing- the top item on the stack iff the stack is NOT empty.
     *  Throws NoSuchElementException iff the stack is empty.
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("The stack is empty!");
        }
        return data[size - 1];
    }

    /** Returns the Max item on the stack iff the stack is not empty.
     *
     * Throws NoSuchElementException iff the stack is empty.
     */
    public T max() {
        if (isEmpty()) {
            throw new NoSuchElementException("The stack is empty!");
        }
        T max = data[0];
        for (int i = 1; i < size; ++i) {
            if (max.compareTo(data[i]) < 1) {
                max = data[i];
            }
        }
        return max;
    }

    /** Returns true iff no items are on the stack. */
    public boolean isEmpty() { return size() == 0; }

    /** Returns the number of items on the stack. */
    public int size() {return size;}


    /*  #### HELPER METHODS. #### */

    /** Resizes data by 'factor'. (i.e. data.length
     *  becomes data.length *= factor.
     */
    private void resize(double factor) {
        int newSize = (int)(data.length * factor);
        T[] copyData = (T[]) new Comparable[newSize];
        for (int i = 0; i < size; i++) {
            copyData[i] = data[i];
        }
        data = copyData;
    }
}
