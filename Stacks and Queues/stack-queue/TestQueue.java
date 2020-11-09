import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestQueue {
    @Test
    public void test1() {
        Queue<Integer> q = new Queue<>();
        assertTrue(q.isEmpty());
        q.enqueue(4);
        q.enqueue(3);
        assertFalse(q.isEmpty());
        assertEquals(2, q.size());
    }

    @Test
    public void testEnqDeq() {
        Queue<Integer> myQ = new Queue<>();
        Queue<Integer> javaQ = new Queue<>();

        myQ.enqueue(5);
        javaQ.enqueue(5);
        assertEquals(javaQ.dequeue(), myQ.dequeue());

        Random rand = new Random();
        final int TEST_SIZE = 10;
        for (int i = 0; i < TEST_SIZE; ++i) {
            int num = rand.nextInt();
            myQ.enqueue(num);
            javaQ.enqueue(num);
        }
        while (!myQ.isEmpty()) {
            int expected = javaQ.dequeue();
            int actual = myQ.dequeue();
            assertEquals(expected, actual);
        }
        assertTrue(javaQ.isEmpty());
    }

    @Test
    public void test3() {
        Queue<Integer> myQ = new Queue<>();
        Queue<Integer> javaQ = new Queue<>();

        final int SIZE1 = 8, SIZE2 = 13, SIZE3 = 17;
        Random rand = new Random();

        for (int i = 0; i < SIZE1; ++i) {
            int num = rand.nextInt();
            myQ.enqueue(num);
            javaQ.enqueue(num);
        }

        while (myQ.size() > 2) {
            int expected = javaQ.dequeue();
            int actual = myQ.dequeue();
            assertEquals(expected, actual);
        }

        for (int i = 0; i < SIZE2; ++i) {
            int num = rand.nextInt();
            myQ.enqueue(num);
            javaQ.enqueue(num);
        }

        while (myQ.size() > 4) {
            int expected = javaQ.dequeue();
            int actual = myQ.dequeue();
            assertEquals(expected, actual);
        }

        for (int i = 0; i < SIZE3; ++i) {
            int num = rand.nextInt();
            myQ.enqueue(num);
            javaQ.enqueue(num);
        }

        while (!myQ.isEmpty()) {
            int expected = javaQ.dequeue();
            int actual = myQ.dequeue();
            assertEquals(expected, actual);
        }
        assertTrue(javaQ.isEmpty());
    }

    @Test
    public void testIterator() {
        Queue<Integer> myQ = new Queue<>();
        Queue<Integer> javaQ = new Queue<>();

        final int SIZE1 = 7;
        Random rand = new Random();

        for (int i = 0; i < SIZE1; ++i) {
            int num = rand.nextInt(20);
            myQ.enqueue(num);
            javaQ.enqueue(num);
        }

        System.out.println("My iterator:");
        for (int num : myQ) {
           System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Java's iterator:");
        for (int num : javaQ) {
            System.out.print(num + " ");
        }
        assertEquals(myQ.size(), javaQ.size());
    }
}

