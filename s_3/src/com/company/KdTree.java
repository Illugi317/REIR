package com.company;
import java.awt.*; //for color
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.*;

public class KdTree {
    private ArrayList<Point2D> da_points;
    private Point2D champion;
    double champion_distance;
    private Node root;
    private class Node {
        private double key;           // sorted by key
        private Point2D val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;
        private int height;          // At what height is the node


        public Node(double key, Point2D val, int height,int size) {
            this.key = key;
            this.val = val;
            this.height = height;
            this.size = size;
        }
    }
    //RedBlackBST red_black_boi;
    // construct an empty set of points
    public KdTree() {
        //red_black_boi = new RedBlackBST();
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        //return red_black_boi.isEmpty();
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        if (root == null){
            return 0;
        }
        //return red_black_boi.size();
        return root.size;
    }

    private int size(Node x){
        if (x == null) return 0;
        else return x.size;
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        //red_black_boi.put(p, p);
        if(contains(p)) return;
        root = insert(root, p, 0);
    };

    private int compare_boi(Point2D a, Point2D b, boolean compare_x){
        if(compare_x) {
            if (a.x() < b.x()) return 1;
            if (a.x() >= b.x()) return 2;

        } else {
            // Compare y
            if (a.y() < b.y()) return -1;
            if (a.y() >= b.y()) return -2;
        }
        return 1;
    }

    private Node insert(Node x,Point2D value, int height){
        if(x == null) {
            // Determine key here bro, it's just a prank bro
            double key;
            if (height % 2 == 0) {
                // Store x as key
                key = value.x();
            }else{
                // Store y as key
                key = value.y();
            }
            return new Node(key,value,height,1);
        }
        int compare_values = compare_boi(value, x.val, height % 2 == 0);
        if((compare_values == 1) || (compare_values == -1)){
            x.left = insert(x.left, value, height +1);
        }         //X val comparison, a is less than b
        else if((compare_values == 2) || (compare_values == -2)){
            x.right = insert(x.right, value,height+1);
        }    //X val comparison, a is greater/equal than b
        x.size = 1 + size(x.left) + size(x.right);

        return x;
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        if (get(p) != null) {
            return true;
        }
        return false;
    }

    private Point2D get(Point2D value) {
        return get_rec(root, value, 0);
    }

    private Point2D get_rec(Node node, Point2D value, int height) {
        if (node == null) return null;
        if (node.val.equals(value)) {
            return value;
        }

        int compare_values = compare_boi(value, node.val, height % 2 == 0);

        if ((compare_values == 1) || (compare_values == -1)) {
            return get_rec(node.left, value, height + 1);

        }         //X val comparison, a is less than b
        else if ((compare_values == 2) || (compare_values == -2)) {
            return get_rec(node.right, value, height + 1);
        }    //X val comparison, a is greater/equal than b
        return node.val;
    }

    // draw all of the points to standard draw
    public void draw() {
        //recursively draw the fuckers ?
        draw_the_bois(root,0.0,1.0,0.0,1.0 ,root.height % 2 == 0);
    }

    private void draw_the_bois(Node node,double xmin,double xmax,double ymin,double ymax, boolean draw_vertical){
        if (node == null) return; //if null node, just stop
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.black);
        node.val.draw(); //draw a smol point boi thats black
        if(draw_vertical) {
            //node is x level
            StdDraw.setPenColor(Color.red); //vertical boi
            StdDraw.setPenRadius(); //smallest radius size
            StdDraw.line(node.val.x(), ymin, node.val.x(), ymax);
            /*
            Cases for vertical boii
            #1
            xmin: node.val.x
            xmax: xmax
            ymin: ymin
            ymax: ymax

            #2
            xmin: xmin
            xmax: node.val.x
            ymin: ymin
            ymax: ymax

             */
            //draw the left subtree but give the bounds for the rectangle
            draw_the_bois(node.left,xmin,node.val.x(),ymin,ymax,!(node.height%2==0));
            //draw the right subtree but give the bounds for the rectangle
            draw_the_bois(node.right,node.val.x(),xmax,ymin,ymax,!(node.height%2==0));
        } else {
            //node is y level
            StdDraw.setPenColor(Color.blue); //!vertical boi
            StdDraw.setPenRadius();
            StdDraw.line(xmin, node.val.y(), xmax, node.val.y());
            /*
            Cases for not vertical boii
            #1
            xmin: xmin
            xmax: xmax
            ymin: node.val.y
            ymax: ymax

            #2
            xmin: xmin
            xmax: xmax
            ymin: ymin
            ymax: node.val.y
             */
            //draw the left subtree but give the bounds for the rectangle
            draw_the_bois(node.left,xmin,xmax,ymin,node.val.y(),!(node.height%2==0));
            //draw the left subtree but give the bounds for the rectangle
            draw_the_bois(node.right,xmin,xmax,node.val.y(),ymax,!(node.height%2==0));
        }
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        da_points = new ArrayList<>();
        get_range_rec(rect, root);
        return da_points;
    }
    private int compare_with_rect(RectHV rect, Node x, boolean compare_x){
        //Compare rectangle with node x and determine if we need to go left or right
        // 0 = both, 1 = right, -1 = left
        // If rectangle contains the point, we check both children
        if (rect.contains(x.val)) return 0;

        if (compare_x) {
            // If the point X is within the rectangle X then check both children
            /*
            p
            []
            p
             */
            if (rect.xmin() <= x.val.x() && x.val.x() <= rect.xmax()) return 0;
            // If point is X greater than rect X max, check left
            /*
               p
            []
               p
             */
            if (x.val.x() > rect.xmax()) return -1;
            // If point is X less than rect X min, check right
            /*
            p
              []
            p
             */
            return 1;
        } else {
            // If the point Y is within the rectangle Y then check both children
            /*
            p[]p
             */
            if (rect.ymin() <= x.val.y() && x.val.y() <= rect.ymax()) return 0;
            // If point is Y greater than rect Y max, check left
            /*
            p  p
             []
             */
            if (x.val.y() > rect.ymax()) return -1;

            // If point is Y less than rect Y min, check right
            /*
             []
            p  p
             */
            return 1;

        }
    }

    private void get_range_rec(RectHV rect, Node node) {
        // Compare if the rectangle is "greater" or "less" than point
        // Depending each time on the height of the node in the tree
        // Comparing if the rectangle is greater / less / equal depending on x or y each time

        // If rectangle is greater, check right sub-tree if not null

        // If rectangle is less, check the left sub-tree if not null
        // If rectangle contains the point, we check both sub-trees if not null

        if(node == null) return;
        int compare_boi = compare_with_rect(rect, node, node.height % 2 == 0);
        if(rect.contains(node.val)){
            da_points.add(node.val);
        }
        if (compare_boi == 1) {
            //check right
            get_range_rec(rect,node.right);
        }else if(compare_boi == -1){
            //check left
            get_range_rec(rect,node.left);
        }else{
            //check both
            //left leaning
            get_range_rec(rect,node.left);
            get_range_rec(rect,node.right);
        }
    }

    private boolean node_line_to_q_greater_than_champ_dist(Node line_point_node, Point2D query_point) {
        // Get line->q distance
        boolean compare_x =  line_point_node.height % 2 == 0;
        double line_q_distance;
        if (compare_x) {
            // Only look at x values
            line_q_distance = Math.abs(line_point_node.val.x() - query_point.x());
        } else {
            // Only look at y values
            line_q_distance = Math.abs(line_point_node.val.y() - query_point.y());
        }
        return champion_distance < line_q_distance*line_q_distance;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        champion = root.val;
        champion_distance = root.val.distanceSquaredTo(p);
        nearest_rec(p, root);
        return champion;
    }

    private void nearest_rec(Point2D p, Node node) {
        /*
        Evaluate p to the querypoint.
        If champion.dist > p->Q
            champion = p
         */
        if(node == null) return;
        double tmp_distance = node.val.distanceSquaredTo(p);
        if(champion_distance > tmp_distance){
            champion = node.val;
            champion_distance = tmp_distance;
        }
        /*
        Check if node line to query point is longer than campion distance.
        (Check line X to champion X OR line Y to champion Y).

            is_greater_boi = is_greater()
         */
        boolean is_line_greater_boi = node_line_to_q_greater_than_champ_dist(node, p);
        boolean check_both;
        boolean vert = node.height % 2 == 0;


        /*
        If line->query point is greater than champ distance, we only check one child
            If (is_greater_boi)
                check_both = False
        */
        check_both = !is_line_greater_boi;
        /*
        If line->query point is less than champ distance, we check both childs
            If (!is_greater_boi)
                check_both = True
         */

        if(vert){
            //compare
            if(node.val.x() < p.x()){
                //greater
                nearest_rec(p,node.right);
                if(check_both){
                    nearest_rec(p,node.left);
                }
            } else{
                //less
                nearest_rec(p, node.left);
                if(check_both){
                    nearest_rec(p,node.right);
                }
            }
        } else{
            if(node.val.y() < p.y()){
                //greater
                nearest_rec(p, node.right);
                if(check_both){
                    nearest_rec(p,node.left);
                }
            } else{
                //less
                nearest_rec(p,node.left);
                if(check_both){
                    nearest_rec(p,node.right);
                }
            }
        }
        /*
        If Q is greater than node.val, go right then left
            right()
            If (check_both)
                left()
         */
         /*
        If Q is less than node.val, go left then right
            left()
            If (check_both)
                right()
         */

    }

    /*******************************************************************************
     * Test client
     ******************************************************************************/
    public static void main(String[] args) {

        if (false) {
            In in = new In("SomeInputs/input1M.txt");

            // initialize the data structures with N points from standard input
            KdTree tree = new KdTree();
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
            In in = new In("SomeInputs/circle10.txt");

            // initialize the data structures with N points from standard input
            KdTree tree = new KdTree();
            for (int i = 0; !in.isEmpty(); i++) {
                double x = in.readDouble();
                double y = in.readDouble();
                Point2D p = new Point2D(x, y);
                tree.insert(p);
            }
            tree.draw();
        }

        if (false) {
            /*
        Point2D point_1 = new Point2D(2, 5);
        Point2D point_2 = new Point2D(1, 3);
        Point2D point_3 = new Point2D(5, 7);
        Point2D point_4 = new Point2D(1, 10);
        Point2D point_5 = new Point2D(6, 9); // nice
        Point2D point_6 = new Point2D(3, 7);

        Point2D[] da_baby = new Point2D[]{point_1, point_2, point_3, point_4, point_5, point_6};


        Point2D point_10 = new Point2D(3, 6);
        Point2D point_20 = new Point2D(2, 7);
        Point2D point_30 = new Point2D(17, 15);
        Point2D point_40 = new Point2D(6, 12);
        Point2D point_50 = new Point2D(13, 15);
        Point2D point_60 = new Point2D(9, 1);
        Point2D point_70 = new Point2D(10, 19);

        Point2D[] da_baby_2 = new Point2D[]{point_10, point_20, point_30, point_40, point_50, point_60, point_70};


        KdTree tree = new KdTree();
        for (int i = 0; i < da_baby.length; i++) {
            // tree.insert(da_baby[i]);
            tree.insert(da_baby[i]);
        }

        KdTree bruhhh = new KdTree();
        boolean contains_point_bruh = bruhhh.contains(new Point2D(2, 7));
        System.out.println("bruh lmao does it contain that point i just made xd?");
        System.out.println(contains_point_bruh);


        System.out.println("lmao");
        */
            System.out.println("Rect testing!");
            double xmin = -0.0478515625;
            double ymin = 0.2890625;
            double xmax = 1.0771484375;
            double ymax = 0.998046875;
            //[-0.0478515625, 1.0771484375] x [0.2890625, 0.998046875]
            RectHV rect = new RectHV(xmin,ymin,xmax,ymax);
            // Object a = tree.range(rect);

            Point2D point_1 = new Point2D(0.206107, 0.095492);
            Point2D point_2 = new Point2D(0.975528, 0.654508);
            Point2D point_3 = new Point2D(0.024472, 0.345492);
            Point2D point_4 = new Point2D(0.793893, 0.095492);
            Point2D point_5 = new Point2D(0.793893, 0.904508);
            Point2D point_6 = new Point2D(0.975528, 0.345492);
            Point2D point_7 = new Point2D(0.206107, 0.904508);
            Point2D point_8 = new Point2D(0.500000, 0.000000);
            Point2D point_9 = new Point2D(0.024472, 0.654508);
            Point2D point_10 = new Point2D(0.500000, 1.000000);
            Point2D[] da_baby = new Point2D[]{point_1, point_2, point_3, point_4, point_5, point_6, point_7,point_8,point_9,point_10};


            KdTree tree = new KdTree();
            for (int i = 0; i < da_baby.length; i++) {
                tree.insert(da_baby[i]);
            }

            Object a = tree.nearest(new Point2D(0.541015625, 0.7900390625));
            System.out.println(a);

        }
        if (true) {

            In in = new In();
            Out out = new Out();
            int nrOfRecangles = in.readInt();
            int nrOfPointsCont = in.readInt();
            int nrOfPointsNear = in.readInt();
            RectHV[] rectangles = new RectHV[nrOfRecangles];
            Point2D[] pointsCont = new Point2D[nrOfPointsCont];
            Point2D[] pointsNear = new Point2D[nrOfPointsNear];
            for (int i = 0; i < nrOfRecangles; i++) {
                rectangles[i] = new RectHV(in.readDouble(), in.readDouble(),
                        in.readDouble(), in.readDouble());
            }
            for (int i = 0; i < nrOfPointsCont; i++) {
                pointsCont[i] = new Point2D(in.readDouble(), in.readDouble());
            }
            for (int i = 0; i < nrOfPointsNear; i++) {
                pointsNear[i] = new Point2D(in.readDouble(), in.readDouble());
            }
            KdTree set = new KdTree();
            for (int i = 0; !in.isEmpty(); i++) {
                double x = in.readDouble(), y = in.readDouble();
                set.insert(new Point2D(x, y));
            }
            for (int i = 0; i < nrOfRecangles; i++) {
                // Query on rectangle i, sort the result, and print
                Iterable<Point2D> ptset = set.range(rectangles[i]);
                int ptcount = 0;
                for (Point2D p : ptset)
                    ptcount++;
                Point2D[] ptarr = new Point2D[ptcount];
                int j = 0;
                for (Point2D p : ptset) {
                    ptarr[j] = p;
                    j++;
                }
                Arrays.sort(ptarr);
                out.println("Inside rectangle " + (i + 1) + ":");
                for (j = 0; j < ptcount; j++)
                    out.println(ptarr[j]);
            }
            out.println("Contain test:");
            for (int i = 0; i < nrOfPointsCont; i++) {
                out.println((i + 1) + ": " + set.contains(pointsCont[i]));
            }

            out.println("Nearest test:");
            for (int i = 0; i < nrOfPointsNear; i++) {
                out.println((i + 1) + ": " + set.nearest(pointsNear[i]));
            }

            out.println();

        }
    }
}
