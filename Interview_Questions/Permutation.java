/*
   Given two integer arrays of size n, design a subquadratic algorithm to determine whether
   one is a permutation of the other. That is, do they contain exactly the same entries but,
   possibly, in a different order.

   @author: Adnan H. Mohamed
 */

import edu.princeton.cs.algs4.Knuth;

import java.util.Arrays;
import java.util.Random;

public class Permutation {

    // Complexity: O(nlogn), see comments inside to know why.
    public static boolean isPermutation(Integer[] a, Integer[] b) {
        if (a.length != b.length) return false;

        Arrays.sort(a);  // complexity: O(nlogn)
        Arrays.sort(b);  // uses TimSort.

        for (int i = 0; i < a.length; ++i) {   // complexity: O(n) -- linear.
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = {1, 3, 5, 6, 4};
        Integer[] b = {1, 3, 5, 4, 6};

        if(!isPermutation(a, b)){System.out.println("BUG!");}

        Integer[] a2 = {1, 2}, b2 = {1};

        if(isPermutation(a2, b2)){System.out.println("BUG!");}

        int size = 10;
        Integer[] a3 = new Integer[size];
        Integer[] b3 = new Integer[size];
        Random rand = new Random();
        for (int i = 0; i < size; ++i) {
            int num = rand.nextInt();
            a3[i] = b3[i] = num;
        }
        Knuth.shuffle(b3);

        if(isPermutation(a, b)){System.out.println("BUG!");}
    }
}
