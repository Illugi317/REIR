
/****************************************************************************
 *  Compilation:  javac PointSET.java
 *  Execution:    
 *  Dependencies:
 *  Author: petursg20, illugif
 *  Date:
 *
 *  Data structure for maintaining a set of 2-D points, 
 *    including rectangle and nearest-neighbor queries
 *
 *************************************************************************/
package com.company;
import edu.princeton.cs.algs4.*;

import java.util.*;

public class PointSET {
    // construct an empty set of points
    public SET<Point2D> set_boi;

    public PointSET() {
        set_boi = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set_boi.isEmpty();

    }

    // number of points in the set
    public int size() {
        return set_boi.size();

    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        set_boi.add(p);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return set_boi.contains(p);
    }

    // draw all of the points to standard draw
    public void draw() {
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {

        // Create a new iterable which we will store the points
        Point2D[] gamer = new Point2D[set_boi.size()];
        //ArrayList<Point2D> gamer = new ArrayList<Point2D>();
        // Iterate through the tree with all the points
        int index = 0;
        for (Object p : set_boi) {
            // If the rectangle contains the point at hand, we add it to our final array
            if (rect.contains((Point2D) p)){
                // Inserting the point into the array
                gamer[index] = (Point2D) p;
                index++;
            }
        }


        Point2D[] short_gamer = Arrays.copyOfRange(gamer, 0,index);
        // Return the array of points within the rectangle
        return Arrays.asList(short_gamer);

    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        // If the set is empty, return null
        if (isEmpty()){
            return null;
        }
        // Our candidate for the nearest neighbour
        Point2D champion = null;

        // Iterate through the set
        for (Object point_boi:set_boi) {
            // If we have not set a candidate for the nearest point, we just say the first one is the nearest one
            if (champion == null) {
                // Assert the point as the nearest point to p
                champion = (Point2D) point_boi;
                continue;
            }
            // Calculate distances between p and the current nearest point
            double dist_p_to_champion = p.distanceSquaredTo(champion);
            // Calculate distances between p and the point at hand
            double dist_p_to_point_boi = p.distanceSquaredTo((Point2D) point_boi);

            // Compare the two distances
            if(dist_p_to_point_boi < dist_p_to_champion) {
                // If the distance was less between the point at hand and p, we set that point as our new candidate as the nearest point
                champion = (Point2D) point_boi;
            }
        }
        return champion;
    }


    public static void main(String[] args) {

        if (false) {
            In in = new In("SomeInputs/input1M.txt");

            // initialize the data structures with N points from standard input
            PointSET tree = new PointSET();
            for (int i = 0; !in.isEmpty(); i++) {
                double x = in.readDouble();
                double y = in.readDouble();
                Point2D p = new Point2D(x, y);
                tree.insert(p);
            }
            int summy_boi = 0;
            for (int i = 0; i < 10; i++) {
                Stopwatch stop_watch_boi = new Stopwatch();
                int counter = 0;
                while (true) {
                    double x = StdRandom.uniform();
                    double y = StdRandom.uniform();
                    tree.nearest(new Point2D(x, y));
                    counter++;
                    if (stop_watch_boi.elapsedTime() > 1){
                        System.out.println(counter);
                        summy_boi += counter;
                        break;
                    }
                }
            }
            System.out.println("mean ops: ");
            System.out.println(summy_boi / 10);
        }

        if (false) {


            int N = 10_000_000;
            PointSET tree = new PointSET();
            Stopwatch stop_watch_boi = new Stopwatch();
            for (int i = 0; i < N; i++) {
                double x = StdRandom.uniform();
                double y = StdRandom.uniform();
                tree.insert(new Point2D(x,y));
            }
            System.out.println(stop_watch_boi.elapsedTime());
        }
        if (true) {


            // String filename = "SomeInputs/circle10k.txt";
            In in = new In();

            // initialize the data structures with N points from standard input
            PointSET brute = new PointSET();
            for (int i = 0; !in.isEmpty(); i++) {
                double x = in.readDouble();
                double y = in.readDouble();
                Point2D p = new Point2D(x, y);
                brute.insert(p);
            }
        }



    }

}
