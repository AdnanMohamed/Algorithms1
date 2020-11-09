import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {

    /**    CLASS INVARIANT.
     *   1- sites array contain the sites in the n-by-n grid.
     *      index 0 and (N^2 + 1) contains virtual sites.
     *      The virtual site 0 is connected to all sites in row 1
     *      and virtual site N^2 + 1 is connected to all sites in the last row.
     *
     *   2- openSites is the count for how many open sites are there.
     */

    private final int N;
    private int openSites;
    private boolean[] isOpen;
    private WeightedQuickUnionUF sites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive.");
        }
        N = n;
        isOpen = new boolean[N*N + 2];
        isOpen[0] = isOpen[N*N + 1] = true;
        sites = new WeightedQuickUnionUF(N*N + 2);
        for (int i = 1; i <= N; ++i) {
            sites.union(0, i);
            sites.union(N*N+1, N*N+1 - i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;

        validate(row, col);
        isOpen[index(row, col)] = true;
        if (row + 1 <= N && isOpen(row + 1, col)) {
            sites.union(index(row, col), index(row + 1, col));
        }
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            sites.union(index(row, col), index(row - 1, col));
        }
        if (col + 1 <= N && isOpen(row , col + 1)) {
            sites.union(index(row, col), index(row, col+ 1));
        }
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            sites.union(index(row, col), index(row, col - 1));
        }
        openSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return isOpen[index(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return isOpen(row, col) && sites.connected(index(row, col), 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.connected(0, N*N+1);
    }

    /** Helper Methods. */

    /** Returns the index of this site in the
     *  array representation of the sites.
     */
    private int index(int row, int col) {
        return (row - 1) * N + col;
    }

    private void validate(int row, int col) {
        if(row < 1 || row > N || col < 1 || col > N) {
            throw new IllegalArgumentException("The row or col are not valid.");
        }
    }


    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        assertEquals(0, p.numberOfOpenSites());

        p.open(1,2);
        p.open(2,2);
        assertEquals(2, p.numberOfOpenSites());
        assertFalse(p.isFull(2, 1));
        assertTrue(p.isFull(1, 2) && p.isFull(2, 2));
        p.open(2,3);
        assertFalse(p.percolates());
        p.open(3, 1);
        p.open(3, 2);
        assertTrue(p.percolates());
        assertEquals(5, p.numberOfOpenSites());
    }

}
