package DataStructures.Tests;

import DataStructures.AVL;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestAVL {
    @Test
    public void test1() {
        AVL<Integer, String> avl = new AVL<>();

        for (int i = 0; i <= 30; ++i) {
            avl.insert(i, "string"+i);
        }
        assertTrue(avl.isAVL());

        AVL<Integer, String> avl2 = new AVL<>();
        for (int i = 0; i <= 100; ++i) {
            avl2.insert(i, "string"+i);
            assertTrue(avl2.isAVL());
        }

        AVL<Integer, String> avl3 = new AVL<>();
        for (int i = 100; i <= 0; ++i) {
            avl3.insert(i, "string"+i);
            assertTrue(avl3.isAVL());
        }

    }

    @Test
    public void test2() {
        AVL<Integer, String> avl = new AVL<>();

        Random rand = new Random();
        for (int i = 0; i <= 50; ++i) {
            int k = rand.nextInt(60);
            avl.insert(k, "string"+k);
            assertTrue(avl.isAVL());
        }

        AVL<Integer, String> avl2 = new AVL<>();
        for (int i = 0; i <= 100; ++i) {
            int k = rand.nextInt(100);
            avl2.insert(k, "string"+k);
            assertTrue(avl2.isAVL());
        }

        AVL<Integer, String> avl3 = new AVL<>();
        for (int i = 0; i <= 10000; ++i) {
            int k = rand.nextInt(10000);
            avl3.insert(k, "string"+k);
            assertTrue(avl3.isAVL());
        }
    }

    @Test
    public void testGet() {
        AVL<Integer, String> avl = new AVL<>();
        Dictionary<Integer, String> dict = new Hashtable<>();
        Random rand = new Random();
        int bound = 50;
        for (int i = 0; i <= 50; ++i) {
            int k = rand.nextInt(bound);
            String val = "string"+rand.nextInt();
            avl.insert(k, val);
            dict.put(k, val);
            assertTrue(avl.isAVL());
            int x = rand.nextInt(bound);
            assertTrue(avl.get(x) == null || avl.get(x).equals(dict.get(x)));
        }
    }

    private class Pair {
        private Integer key;
        private String val;
        public Pair(Integer key, String val) {
            this.key = key;
            this.val = val;
        }
    }

    public void f(List<Pair> entries) {
        for (Pair p : entries) {
            System.out.println("k = "+p.key+";");
            System.out.println("val = \""+p.val+"\";");
            System.out.println("avl.insert(k, val);");
            System.out.println("dict.put(k, val);");
            System.out.println("avl.print();");
            System.out.println("System.out.println(\"========================\");");
        }
    }

    @Test
    public void testSize() {
        AVL<Integer, String> avl = new AVL<>();
        Dictionary<Integer, String> dict = new Hashtable<>();
        List<Pair> entries = new ArrayList<>();
        assertEquals(0, avl.size());
        Random rand = new Random();
        int bound = 50;
        for (int i = 0; i <= 50; ++i) {
            int k = rand.nextInt(bound);
            String val = "string"+rand.nextInt();
            avl.insert(k, val);
            dict.put(k, val);
            entries.add(new Pair(k, val));
            assertEquals(dict.size(), avl.size());
        }


        bound = 1000;
        AVL<Integer, String> avl2 = new AVL<>();
        Dictionary<Integer, String> dict2 = new Hashtable<>();
        for (int i = 0; i <= bound; ++i) {
            int k = rand.nextInt(bound);
            String val = "string"+rand.nextInt();
            avl.insert(k, val);
            dict.put(k, val);
            assertEquals(dict.size(), avl.size());
        }

    }
}