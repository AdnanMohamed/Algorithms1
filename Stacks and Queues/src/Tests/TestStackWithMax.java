package Tests;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.Stack;
import org.junit.Test;
import StackQueue.StackWithMax;

import java.util.*;

public class TestStackWithMax {

    public static boolean equals(Double d1, Double d2) {
        return Math.abs(d1 - d2) < 10e-5;
    }
    @Test
    public void test1() {
        StackWithMax<Double> s = new StackWithMax<>();
        assertTrue(s.isEmpty());

        s.push(3.1);
        s.push(-3.1);
        s.push(10.0);
        s.push(10.1);

        assertTrue(equals(10.1, s.max()));

        assertTrue(equals(s.pop(), 10.1));
        assertTrue(equals(s.peek(), 10.0));
        assertTrue(equals(s.pop(), 10.0));
        assertTrue(equals(s.pop(), -3.1));
        assertTrue(equals(s.pop(), 3.1));
    }

    @Test
    public void testMax() {
        StackWithMax<Integer> s = new StackWithMax<>();
        Random rand = new Random();

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt();
            s.push(x);
            set.add(x);
        }
        assertEquals(Collections.max(set), s.max());

        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt();
            s.push(x);
            set.add(x);
        }
        assertEquals(Collections.max(set), s.max());

        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt();
            s.push(x);
            set.add(x);
        }
        assertEquals(Collections.max(set), s.max());

    }

    @Test
    public void testPushPopPeek() {
        StackWithMax<Integer> s = new StackWithMax<>();
        Stack<Integer> javaStack = new Stack<>();
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt();
            s.push(x);
            javaStack.push(x);
        }
        assertEquals(javaStack.pop(), s.pop());
        assertEquals(javaStack.peek(), s.peek());
        assertEquals(javaStack.size(), s.size());

        for (int i = 0; i < 7; i++) {
            int x = rand.nextInt();
            s.push(x);
            javaStack.push(x);
        }

        while (javaStack.size() > 2) {
            assertEquals(javaStack.peek(), s.peek());
            assertEquals(javaStack.pop(), s.pop());
        }
        assertEquals(javaStack.size(), s.size());

        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt();
            s.push(x);
            javaStack.push(x);
        }

        while (javaStack.size() > 20) {
            assertEquals(javaStack.peek(), s.peek());
            assertEquals(javaStack.pop(), s.pop());
        }
        assertEquals(javaStack.size(), s.size());

        for (int i = 0; i < 1000; i++) {
            int x = rand.nextInt();
            s.push(x);
            javaStack.push(x);
        }

        while (!s.isEmpty()) {
            assertEquals(javaStack.peek(), s.peek());
            assertEquals(javaStack.pop(), s.pop());
        }
        assertTrue(javaStack.isEmpty());
    }
}
