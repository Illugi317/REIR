package com.company;

import edu.princeton.cs.algs4.*;

public class Main {

    public static void main(String[] args) {
        // number of line segments to plot
        int n = StdIn.readInt();

        // the function y = sin(3x)/2 * tan(x)/cos(x), sampled at n+1 points
        // between x = 0 and x = pi
        double[] x = new double[n+1];
        double[] y = new double[n+1];
        for (int i = 0; i <= n; i++) {
            x[i] = 2 * Math.PI * i / n;
            y[i] = Math.sin(i) * 14;
        }

        // rescale the coordinate system
        StdDraw.setXscale(-10, +10);
        StdDraw.setYscale(-15, +15);

        // plot the approximation to the function
        for (int i = 0; i < n; i++) {
            StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
        }
    }
}
