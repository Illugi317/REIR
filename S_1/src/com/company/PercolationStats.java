package com.company;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double[] percolation_thresholds;
    private final double time_elapsed;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T){
        if (N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        percolation_thresholds = new double[T];
        Stopwatch totalTime = new Stopwatch();
        for (int ki = 0; ki < T; ki++) {

            Percolation percolation_experement = new Percolation(N);
            for (int j = 0; j < N*N; j++) {
                int random_row = StdRandom.uniform(0, N);
                int random_col = StdRandom.uniform(0, N);
                percolation_experement.open(random_row, random_col);
                if (percolation_experement.percolates()) {
                    /*
                    System.out.println("Tries: " + (j + 1));
                    System.out.println("Open sites: " + percolation_experement.numberOfOpenSites());
                    System.out.println("Tries / N*N = " + (j + 1) + "/" + (N*N) + " = " + ((float)(j+1)/(N*N)));
                    System.out.println("Open sites / N*N = " + percolation_experement.numberOfOpenSites() + "/" + (N*N) + " = " + ((float)percolation_experement.numberOfOpenSites()/(N*N)));
                     */
                    percolation_thresholds[ki] = (float)percolation_experement.numberOfOpenSites()/(N*N);
                    break;
                }
            }
        }
        //double mew = StdStats.mean(percolation_thresholds);
        //double sigma = StdStats.var(percolation_thresholds);
        time_elapsed = totalTime.elapsedTime();

    }
    private double time_elapsed(){
        return time_elapsed;
    }
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(percolation_thresholds);
    }
    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(percolation_thresholds);
    }
    // low  endpoint of 95% confidence interval
    public double confidenceLow(){
        return mean() - ((float) (1.96 * stddev()) / Math.sqrt(percolation_thresholds.length));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return mean() + ((float) (1.96 * stddev()) / Math.sqrt(percolation_thresholds.length));
    }
    public static void main(String[] args) {
            System.out.println("----------");
            PercolationStats perk = new PercolationStats(400, 200);
            System.out.println("Mean: " + perk.mean());
            System.out.println("Standard dev: " + perk.stddev());
            System.out.println("Confidence LOW: "+ perk.confidenceLow());
            System.out.println("Confidence HIGH: "+ perk.confidenceHigh());
            System.out.println("Running time for experiment: " + perk.time_elapsed());
    }
}