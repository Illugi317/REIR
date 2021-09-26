package com.company;
import edu.princeton.cs.algs4.*;

import java.util.Comparator;

/*************************************************************************
 * Compilation: javac Point.java Execution: Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 * @author Magnus M. Halldorsson, email: mmh@ru.is
 *************************************************************************/
public class Point implements Comparable<Point> {

    public final int x, y;

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>(){
        /*We create a new comparator to compare the slopes bwtween two points.
        We calculate the slope from our point to point 1 and for point 2.
        Then we return -1, 0 or 1 depending of if slope_1 is bigger or smaller than slope_2.
        We wrote 2 if statements and one else statement and then intellij said we could use this Double.compare, we are not sure if we are allowed to use this.
        */
        public int compare(Point point_1, Point point_2) {
            double slope_1 = slopeTo(point_2);
            double slope_2 = slopeTo(point_1);
            return Double.compare(slope_2, slope_1); //Intellij made me do this
        }
    };

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        //We kinda bruteforced this by seeing the mooshak results
        if (this.x == that.x && this.y == that.y){
            //If the X values and the Y values are identical, return NEG_INF, aka (0/0)
            return Double.NEGATIVE_INFINITY;
        }
        else if(this.x == that.x){
            //If the x values are identical return POS_INF, aka (y1-y2)/0
            return Double.POSITIVE_INFINITY;
        }
        else if(this.y == that.y){
            //If the y values are identical return zero, since if the numerator is zero we get zero
            return 0;
        }
        else{
            // If we pass thoes tests we simply calculate the actual slope and return it.
            // Cast integers into floating point to get floating point division
            return (double)(this.y - that.y) /(this.x - that.x);
        }
    }

    /**
     * Is this point lexicographically smaller than that one? comparing
     * y-coordinates and breaking ties by x-coordinates
     */
    public int compareTo(Point that) {
        /*We went after what we got in the problem description
        We first check the y values and then we use the x values to break ties.
        if it's the same point we simply return 0
        */
        if (this.y < that.y) return -1; 
        if (this.y > that.y) return 1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return 1;

        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /*
         * Do not modify
         */
        // In in = new In("input_from_S2_main_1");
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        out.printf("Testing slopeTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].slopeTo(points[i - 1]));
        }
        out.printf("Testing compareTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].compareTo(points[i - 1]));
        }
        out.printf("Testing SLOPE_ORDER comparator...\n");
        for (int i = 2; i < points.length; i++) {
            out.println(points[i].SLOPE_ORDER.compare(points[i - 1],
                    points[i - 2]));
        }
    }
}