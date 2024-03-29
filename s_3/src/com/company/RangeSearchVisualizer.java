package com.company; /******************************************************************************
 *  Compilation:  javac RangeSearchVisualizer.java
 *  Execution:    java RangeSearchVisualizer input.txt
 *  Dependencies: PointST.java KdTreeST.java
 *
 *  Read points from a file (given as the command-line argument) and
 *  draw to standard draw. Also draw all of the points in the rectangle
 *  the user selects by dragging the mouse.
 *
 *  The range search results using the brute-force algorithm are drawn
 *  in red; the results using the kd-tree algorithms are drawn in blue.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class RangeSearchVisualizer {

    public static void main(String[] args) {
        In in = new In("1M.txt");

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-0.25,1.25);
        StdDraw.setYscale(-0.25,1.25);
        StdDraw.show();

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
        double x1 = 0.0, y1 = 0.0;      // current location of mouse
        boolean isDragging = false;     // is the user dragging a rectangle

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        brute.draw();  // for (Point2D p : brute.points())        p.draw();
        StdDraw.show();

        while (true) {
            // user starts to drag a rectangle
            if (StdDraw.isMousePressed() && !isDragging) {
                x0 = StdDraw.mouseX();
                y0 = StdDraw.mouseY();
                isDragging = true;
                continue;
            }

            // user is dragging a rectangle
            else if (StdDraw.isMousePressed() && isDragging) {
                x1 = StdDraw.mouseX();
                y1 = StdDraw.mouseY();
                //continue;
            }

            // mouse no longer pressed
            else if (!StdDraw.isMousePressed() && isDragging) {
                isDragging = false;
            }


            RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                                     Math.max(x0, x1), Math.max(y0, y1));
            
//            RectHV wholeplane = new RectHV()
            // draw the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            brute.draw();  // for (Point2D p : brute.points())     p.draw();

            // draw the rectangle
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius();
            rect.draw();

            if (!isDragging) {
                // draw the range search results for brute-force data structure in red
                StdDraw.setPenRadius(.03);
                StdDraw.setPenColor(StdDraw.RED);
                for (Point2D p : brute.range(rect))
                    p.draw();

                // draw the range search results for kd-tree in blue
                StdDraw.setPenRadius(.02);
                StdDraw.setPenColor(StdDraw.BLUE);
                for (Point2D p : kdtree.range(rect))
                    p.draw();
            }
            
            StdDraw.show();
            StdDraw.pause(40);
        }
    }
}
