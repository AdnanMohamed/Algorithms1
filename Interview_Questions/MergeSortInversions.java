import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MergeSortInversions {

    /*
    Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j]
    such that i < j but a[i] > a[j]a[i]>a[j]. Given an array, design a linearithmic
    algorithm to count the number of inversions.
     */
    public static int inversions(int[] a) {
        return sortAndCount(a, 0, a.length-1);
    }

    private static int sortAndCount(int[] a, int low, int hi) {
        if (low >= hi) return 0;
        else if (hi - low == 1 && a[0] > a[1]) {
            swap(a, 0, 1);
            return 1;
        }

        int mid = (hi + low) / 2;

        int leftInversions = sortAndCount(a, low, mid);
        int rightInversions = sortAndCount(a, mid + 1, hi);
        int splitInversions = mergeAndCountSplit(a, low, mid, hi);

        return leftInversions + rightInversions + splitInversions;
    }

    private static int mergeAndCountSplit(int[] a, int low, int mid, int hi) {
        int inversions = 0;
        int[] aux = new int[hi - low + 1];

        int l = low, r = mid + 1;
        for (int i = 0; i < aux.length; ++i) {
            if (l > mid) aux[i] = a[r++];
            else if (r > hi) aux[i] = a[l++];
            else if (a[l] < a[r]) aux[i] = a[l++];
            else {
                inversions += mid - l + 1;
                aux[i] = a[r++];
            }
        }

        for (int i = low, j = 0; i <= hi; ++i, ++j) {
            a[i] = aux[j];
        }

        return inversions;

    }

    private static void swap (int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    

    // for testing
    private static int[] generateArray(int N) {
        int[] a = new int[N];
        Set set = new HashSet();
        Random rand = new Random();
        int i = 0;
        while (set.size() < N){
            int x = rand.nextInt(N + 3);
            if (!set.contains(x)) {
                set.add(x);
                a[i] = x;
                ++i;
            }

        }
        return a;
    }

    // for testing
    private static void printArray(int[] a) {
        for(int x : a) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

//        sort(a);
//        for (int num : a) System.out.print(num + " ");
//        System.out.println();
//        System.out.println("Inversions: " + inversions(a));
        int size = 7;
        for (int i = 0; i < size; ++i) {
            int [] a = generateArray(size);
            System.out.println("Array items:-");
            printArray(a);
            System.out.println("Number of Inversions: " + inversions(a));
            System.out.println("============");
        }
    }
}
