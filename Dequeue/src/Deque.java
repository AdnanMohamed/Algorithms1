/*  This is an implementation for the Dequeue data-structure.

    @Author: Adnan H. Mohamed
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    /* #### CLASS INVARIANT  ####
        1- head, always points to the first node in the
           doubly-linked-list. (null if the dequeue is empty.)
        2- tail, always points to the last node in the
           doubly-linked-list. (null if the dequeue is empty.)
        3- size, contains the number of items in the dequeue.
     */


    private class Node {
        Item data;
        Node next;
        Node prev;
        public Node() {}
        public Node(Item item, Node next, Node prev) {
            this.data = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head, tail;
    private int size;


    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node node = new Node(item, head, null);

        // only when the deque is not empty, make the old first
        // node's previous pointer point to the new first node.
        if (!isEmpty())
            head.prev = node;

        // maintain the head invariant.
        head = node;

        // maintain the tail invariant.
        if (size == 0) {
            tail = head;
        }

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // using the addFirst method in case the
        // dequeue is empty when calling this method
        // since both do the same thing in this case.
        if (isEmpty()) {
            addFirst(item);
            return;
        }

        // Creating the new last node and making the old
        // last node point to it. Then maintaining the tail invariant.
        Node node = new Node(item,null, tail);
        tail.next = node;
        tail = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("There is no element to be removed.");
        }

        // Retrieving the data in first node to be returned
        // Then pointing the head to the 2nd node to become the new first node.
        Item item = head.data;

        if (size() == 1) {

            head = tail = null;
            size--;
            return item;
        }

        head.next.prev = null;
        head = head.next;

        if (size == 2) {
            // maintaining the tail to be
            // pointing to the last element
            // which in this case is the first element as well.
            tail = head;
        }

        size--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("There is no element to be removed.");
        }
        // using removeFirst function becuase it
        // already implements the same functionality
        // for size == 1.
        if (size == 1) {
            return removeFirst();
        }
        // Retrieving the data in the last node
        // then point the tail to before last node
        // to become the new last node.
        Item item = tail.data;
        tail.prev.next = null;
        tail = tail.prev;
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.data;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(5);
        d.removeFirst();
        d.isEmpty();

//        d.addFirst(1);
//        d.addFirst(2);
//        d.addLast(55);
//        d.addFirst(3);
//        d.addLast(77);
//        d.removeFirst();
//        d.removeLast();

        Iterator<Integer> it = d.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        for (int i = 1; i < 6; i++) {
            d.addFirst(i);
        }


        for (int i = 1; i < 6; i++) {
            System.out.println(d.removeFirst());
        }
        System.out.println("==========");
        for (int i = 1; i < 6; i++) {
            d.addFirst(i);
        }

        for (int i = 1; i < 6; i++) {
            System.out.println(d.removeLast());
        }

        System.out.println("==========");
        for (int i = 1; i < 6; i++) {
            d.addLast(i);
        }

        for (int i = 1; i < 6; i++) {
            System.out.println(d.removeLast());
        }
        System.out.println("==========");
        for (int i = 1; i < 6; i++) {
            d.addLast(i);
        }
        System.out.println("The dequeue size is " + d.size);

        for (int i = 1; i < 6; i++) {
            System.out.println(d.removeFirst());
        }

        System.out.println("==========");
        for (int i = 1; i < 6; i++) {
            d.addFirst(i * 10);
        }

        System.out.println("Using the iterator:");
        for(int x : d) {
            System.out.print(x + " ");
        }

    }

}