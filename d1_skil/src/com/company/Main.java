package com.company;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdStats;
public class Main {

    static int couponCollectorTest(int N){
        int count = 0;
        int dist = 0;
        boolean[] numbers = new boolean[N];
        while (dist < N) {
            int val = StdRandom.uniform(N);
            count++;
            if (!numbers[val]) {
                dist++;
                numbers[val] = true;
            }
        }
        return count;
    }

    public static class CouponCollectorStats {
        int numberOfCards;
        int tries;
        int count;
        int[] coupons;
        double[] trials;
        public CouponCollectorStats(int N, int T){
            numberOfCards = N;
            tries = T;
            trials = new double[T];
            coupons = new int[T];
            StdOut.println("TESTING N : " + N + " T: " + T);
            for(int i=0;i<T;i++){
                Stopwatch cwatch = new Stopwatch();
                count = couponCollectorTest(N);
                double ctime = cwatch.elapsedTime();
                trials[i] = ctime;
                coupons[i] = count;
            }
        }
        public double[] mean(){
            double [] mean_pair = new double[2];
            mean_pair[0] = StdStats.mean(trials);
            mean_pair[1] = StdStats.mean(coupons);
            return mean_pair;
        }
        public double[] stddev(){
            double [] stddev_pair = new double[2];
            stddev_pair[0] = StdStats.stddev(trials);
            stddev_pair[1] = StdStats.stddev(coupons);
            return stddev_pair;
        }
        public double total_time(){
            double sum=0;
            for (double trial : trials) {
                sum += trial;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        int[] trial_nums = new int[]{10,100,1000};
        for (int i = 0; i < 3; i++) {
            CouponCollectorStats stats = new CouponCollectorStats(100000, trial_nums[i]);
            double[] mean_pair = stats.mean();
            double[] stddev_pair = stats.stddev();
            StdOut.println("TOTAL TIME: " + stats.total_time() + "\nMEAN TIME: " + mean_pair[0] + "\nMEAN COUPONS: " + mean_pair[1] + "\nSTDDEV TIME: " + stddev_pair[0] + "\nSTDDEV COUPONS: " + stddev_pair[1] + "\n");
        }
    }
}