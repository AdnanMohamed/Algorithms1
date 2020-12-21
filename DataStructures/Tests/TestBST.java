package DataStructures.Tests;

import DataStructures.BST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class TestBST {
    @Test
    public void testRankAndSelect() {
        BST<Integer, String> bst = new BST<>();
        bst.insert(7, "Adnan7");
        bst.insert(8, "Adnan8");
        bst.insert(3, "Adnan3");
        bst.insert(6, "Adnan6");
        bst.insert(1, "Adnan1");
        bst.insert(56, "Adnan56");

        assertEquals(new Integer(0), (Integer)bst.rank(1));
        assertEquals(new Integer(1), (Integer)bst.rank(3));
        assertEquals(new Integer(2), (Integer)bst.rank(6));
        assertEquals(new Integer(3), (Integer)bst.rank(7));
        assertEquals(new Integer(4), (Integer)bst.rank(8));
        assertEquals(new Integer(5), (Integer)bst.rank(56));

        assertEquals(new Integer(1), (Integer)bst.select(0));
        assertEquals(new Integer(3), (Integer)bst.select(1));
        assertEquals(new Integer(6), (Integer)bst.select(2));
        assertEquals(new Integer(7), (Integer)bst.select(3));
        assertEquals(new Integer(8), (Integer)bst.select(4));
        assertEquals(new Integer(56), (Integer)bst.select(5));
    }

    @Test
    public void testInsertDelete() {
        BST<Integer, String> bst = new BST<>();
        Random rand = new Random();
        final int KEYS = 100;
        Integer[] keys = new Integer[KEYS];
        for (int i = 0; i < KEYS; ++i) {
            int x = rand.nextInt(200);
            keys[i] = x;
            bst.insert(x, "string"+x);
        }

        bst.print();
        System.out.println("===========");
        int expected_size = KEYS;
        for (int k : keys) {
            System.out.println("Removing " + k + ":\n");
            assertEquals(expected_size--, bst.size());
            bst.delete(k);
            bst.print();
            System.out.println("===========");
        }
    }
}
