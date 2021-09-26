package com.company;
import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Brute {
    public Brute(Point[] point_array) {
        List<ArrayList<Point>> big_list_boi = new ArrayList<ArrayList<Point>>();
        for (int a = 0; a < point_array.length; a++) {
            // Point 1
            for (int b = 0; b < point_array.length; b++) {
                // Point 2
                for (int c = 0; c < point_array.length; c++) {
                    // Point 3
                    for (int d = 0; d < point_array.length; d++) {
                        // Point 4
                        if (check_order(point_array[a], point_array[b], point_array[c], point_array[d])) {
                            if (check_slope(point_array[a], point_array[b], point_array[c], point_array[d])) {
                                ArrayList<Point> arraylist_boi = new ArrayList<Point>();
                                arraylist_boi.add(point_array[a]);
                                arraylist_boi.add(point_array[b]);
                                arraylist_boi.add(point_array[c]);
                                arraylist_boi.add(point_array[d]);
                                big_list_boi.add(arraylist_boi);
                            }
                        }
                    }
                }
            }
        }
        for (ArrayList sublist : big_list_boi){
            String line_str = ""; // Create a empty string
            for (Object p: sublist) {
                //For each Point Object add the string version of it and add the arrow " -> "
                line_str = line_str + p + " -> ";
            }
            System.out.println(line_str.substring(0, line_str.length() - 4)); //Remove the last four characters since it's just an arrow pointing at nothing and then print out the entire string
        }
    }

    private boolean check_order(Point a, Point b, Point c, Point d){
        /*Check if the points are in lexiographic order.
        We use the compareTo function to check if a is smaller than b, b smaller than c, and c smaller than d.
        If all thoes statements are true we know then the points are in lexiographic order
        */
        return a.compareTo(b) == -1 && b.compareTo(c) == -1 && c.compareTo(d) == -1;
    }

    private boolean check_slope(Point a, Point b, Point c, Point d){
        /*Check if the slope is the same for the points
        since we know we only call this function when check_order returns true.
        then we know a will be the smallest point (lexicographically).
        Then we check if we get the same slope order by using the comparitor that we created in Point.java
        */
        if(a.SLOPE_ORDER.compare(b,c) == 0){
            //We check if the slopes of a,b and c are the same and if so we then return the result of comparing a,b and d
            return a.SLOPE_ORDER.compare(b,d) == 0;
        }
        else{
            //If a, b and c do not have the same slope we simply return false
            return false;
        }
    }

    public static void main(String[] args) {
        String input_from_S2_main_1 = "input_from_S2_main_2";
        // String input48

        In in = new In(input_from_S2_main_1);
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        //Point[] points_sorted = Arrays.stream(points).sorted();
        Arrays.sort(points);
        Brute brute_boi = new Brute(points);
        //System.out.println(n);

    }
}