import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    double[] percolations;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be positive integers");
        }
        percolations = new double[trials];
        Percolation percolation = new Percolation(n);
        for (int i = 0; i < trials; ++i) {
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
            }
            percolations[i] =  percolation.numberOfOpenSites() / ((double)(n*n));
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolations);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolations);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(percolations.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(percolations.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("You must supply two positive integers for the command line arguments.");
            System.exit(0);
        }

        int n = Integer.parseInt(args[0]), T = Integer.parseInt(args[1]);
        PercolationStats percStat = new PercolationStats(n, T);

        System.out.println("mean = " + percStat.mean());
        System.out.println("stddev = " + percStat.stddev());
        System.out.println("95% confidence interval = [" + percStat.confidenceLo() +
                ", " + percStat.confidenceHi() + "]");
    }

}
