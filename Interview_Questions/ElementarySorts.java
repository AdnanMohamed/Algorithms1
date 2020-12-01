import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ElementarySorts {

    /*
        Question 1
        Intersection of two sets.
        Given two arrays a[] and b[], each containing N distinct 2D points in the plane,
        design a subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].
     */
    private static class Point implements Comparable<Point> {
        private Integer x, y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point that) {
            int delta_x = this.x - that.x;
            if (delta_x != 0) return delta_x;
            return this.y - that.y;
        }
    }

    public static int intersections(Point[] a, Point[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int count = 0;
        for (Point p : a) {
            if (Arrays.binarySearch(b, p) >= 0) {
                count++;
            }
        }
        return count;
    }

    // Q2: Given two integer arrays of size N, design a subquadratic algorithm to determine whether one is a permutation of the other.
    //    That is, do they contain exactly the same entries but, possibly, in a different order.
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


    private static class Bucket implements Comparable<Bucket> {

        private final static int RED = 0,
                                 WHITE = 1,
                                 BLUE = 2;

        private final int color;

        public Bucket(int color) {
            this.color = color;
        }

        @Override
        public int compareTo(Bucket that) {
            if (this.color > that.color) return 1;
            else if (this.color < that.color) return -1;
            else return 0;
        }
    }

    public static void SortBuckets(Bucket[] buckets) {
        int i = 0, j;
        Bucket RED = new Bucket(0);
        Bucket WHITE = new Bucket(1);

        j = Arrays.binarySearch(buckets, i, buckets.length, RED);
        while (j >= i) {
            swap(buckets, i, j);
            i++;
            j = Arrays.binarySearch(buckets, i, buckets.length, RED);
        }

        j = Arrays.binarySearch(buckets, i, buckets.length, WHITE);
        while (j >= i) {
            swap(buckets, i, j);
            i++;
            j = Arrays.binarySearch(buckets, i, buckets.length, WHITE);
        }
    }

    private static <T> void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void printFlag(Bucket[] buckets) {
        for (Bucket b : buckets) {
            switch (b.color) {
                case 0:
                    System.out.print("R");
                    break;
                case 1:
                    System.out.print("W");
                    break;
                case 2:
                    System.out.print("B");
                    break;
                default:
                    System.out.println("This case is not supported!");
                    System.exit(0);
            }
        }
    }

    public static void main(String[] args) {

        int SIZE = 20;
        Bucket[] buckets = new Bucket[SIZE];
        Random rand = new Random();
        for (int i = 0; i < SIZE; ++i) {
            buckets[i] = new Bucket(rand.nextInt(3));
        }

        Arrays.sort(buckets);
        printFlag(buckets);
    }
}