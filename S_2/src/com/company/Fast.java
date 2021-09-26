package com.company;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Fast {

    public Fast(Point[] point_array) {

        // Select an origin p and iterate through every other point in the set
        for (Point p : point_array) {

            // Store the slope of p to a potential q, initialize it at NaN
            double slope_boi = Double.NaN;

            // Sort the list of all points using the slope from p to q as a comparator
            // EXAMPLE
            // P is the origin
            // Q1, Q2, Q3, Q4 are points in the set
            // We sort the points above according to how their slopes are in respect to p
            // Say that:
            // P.slopeTo(Q1) = -1
            // P.slopeTo(Q2) = 5
            // P.slopeTo(Q3) = -2
            // P.slopeTo(Q4) = 7
            // Our sorted list of Q's is the following:
            // [Q3, Q1, Q2, Q4]

            ArrayList<Point> list = Arrays.stream(point_array)
                    .sorted(p.SLOPE_ORDER)
                    .collect(Collectors.toCollection(ArrayList::new));

            // Create an inner array which holds the potential collinear points that all q points share the same slope
            // to p
            ArrayList<Point> inner_array = new ArrayList<>();

            // Now iterate through our sorted list of Q points
            for (Point q : list) {

                // If we land on the same point we have marked as the origin, we skip this iteration
                if(p == q) continue;

                // If our current slope is not a number
                if(Double.isNaN(slope_boi)){
                    // Check if we have the right alignment of p to q, we only want to consider it a candidate if it is
                    // above p or to the right of p
                    if(p.compareTo(q) < 0){
                        // Now we consider q to be potentially collinear to p
                        // We store the slope from p to q and add q to our collinear array
                        slope_boi = p.slopeTo(q);
                        inner_array.add(q);
                    }
                    // We skip the rest because that logic was the only thing we wanted to do
                    // The rest of the algorithm relies on the current slope to be the same as the slope from p to q,
                    // which we just declared above
                    continue;
                }

                // If our slope that we had before is the same as the slope from this current iteration
                // (slope from p to q)
                // Then we know that this q is also a candidate for our collinear array, so we add it
                if(slope_boi == p.slopeTo(q)){
                    inner_array.add(q);
                }
                // If the slope from p to q is now different from the previous pair of p and q, we want to do two things
                // Number one: We want to see if our collinear array of q points was sufficient enough to be displayed
                // (were there more or equal to three)
                // Number two: Since this means that we hit a new slope, we have to reset our potential collinear array
                // of potential candidates

                else {
                    // Check if we had a sufficient amount of collinear points
                    if (inner_array.size() >= 3) {
                        // If we did, then we add our origin point p to the start of the array and print it out
                        inner_array.add(0,p);
                        print_collinear(inner_array, p);
                    }
                    // We empty the array for one of two reasons
                    // Either we printed out all the collinear points and we are moving on to the next slope
                    // Or we did not get enough collinear points and we are just starting another search for a set of
                    // candidates for collinear points
                    inner_array = new ArrayList<>();

                    // Now that we have a now slope on our hands, we have to check if its direction from the origin is
                    // correct (q has to be above or to the right of p)
                    if(p.compareTo(q) < 0) {
                        // If it is above or to the right we store that slope as our slope to look out for in the next
                        // iteration
                        slope_boi = p.slopeTo(q);
                        // Add the point q to the potential candidate array of collinear points
                        inner_array.add(q);
                    } else {
                        // If the placement of q was not above or to the right, we just say that we have no slope as
                        // the criteria for next iteration
                        slope_boi = Double.NaN;
                    }
                }
            }
            // This handles the edge case of the last slopes in the array being equal, if they were equal we did not
            // print them out because we expected an end to the streak
            // So we just check if we had at least three potential candidates for collinear points and if we did,
            // we print them out
            if (inner_array.size() >= 3) {
                inner_array.add(0,p);
                print_collinear(inner_array, p);
            }
        }
    }

    private void print_collinear(ArrayList<Point> inner_array, Point p) {
            String line_str = "";

            for (Point i: inner_array) {
                line_str = line_str + i + " -> ";
            }

            System.out.println(line_str.substring(0, line_str.length() - 4));
    }


    public static void main(String[] args){
        String input_from_S2_main_1 = "input_from_S2_main_2";
        String input_500 = "generator/4000.txt";
        // String input48

        In in = new In(input_500);
        // In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        //Point[] points_sorted = Arrays.stream(points).sorted();
        Arrays.sort(points);
        Stopwatch watch_boi = new Stopwatch();
        Fast fast_boi = new Fast(points);
        System.out.println(watch_boi.elapsedTime());
    }
}