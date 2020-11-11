import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

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
        head = new Node();
        tail = head;
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
        Node node = new Node(item, head, null);
        if (!isEmpty())
            head.prev = node;
        head = node;
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (isEmpty()) {
            addFirst(item);
            return;
        }
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
        Item item = head.data;
        head = head.next;
        if (size() == 1) {
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
        if (size == 1) {
            return removeFirst();
        }
        Item item = tail.data;
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
        assert d.isEmpty();

        for (int i = 1; i < 6; i++) {
            d.addFirst(i);
        }

        assert !d.isEmpty();

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