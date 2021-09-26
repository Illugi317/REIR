package com.company;
import edu.princeton.cs.algs4.*;

import java.sql.Array;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.*;
public class Brute {

    private LinkedList linkedlist_boi;
    private ArrayList arrayList_boi;
    private List big_list_boi;

    public Brute(Point[] point_array) {
        int counter = 0;
        LinkedList linkedlist_boi = new LinkedList();
        List<ArrayList<Point>> big_list_boi = new ArrayList<ArrayList<Point>>();
        Point[] same_slope_points_boi = new Point[4];
        for (int a = 0; a < point_array.length; a++) {
            // Point 1
            for (int b = 0; b < point_array.length; b++) {
                // Point 2
                for (int c = 0; c < point_array.length; c++) {
                    // Point 3
                    for (int d = 0; d < point_array.length; d++) {
                        // Point 4
                        if (check_order(point_array[a], point_array[b], point_array[c], point_array[d])) {
                            boolean abc = point_array[a].SLOPE_ORDER.compare(point_array[b], point_array[c]) == 0;
                            boolean bcd = point_array[b].SLOPE_ORDER.compare(point_array[c], point_array[d]) == 0;
                            System.out.println("--------------------------");
                            System.out.println("Point a: " + point_array[a]);
                            System.out.println("Point b: " + point_array[b]);
                            System.out.println("Point c: " + point_array[c]);
                            System.out.println("Point d: " + point_array[d]);
                            System.out.println("Is a in line with b and c: " + abc);
                            System.out.println("Is b in line with c and d: " + bcd);
                            System.out.println("Are a b c d all in line: " + (abc && bcd));
                            System.out.println("--------------------------");
                            if (check_slope(point_array[a], point_array[b], point_array[c], point_array[d])) {
                                counter++;
                                ArrayList<Point> arraylist_boi = new ArrayList<Point>();
                                arraylist_boi.add(point_array[a]);
                                arraylist_boi.add(point_array[b]);
                                arraylist_boi.add(point_array[c]);
                                arraylist_boi.add(point_array[d]);
                                big_list_boi.add(arraylist_boi);
                                System.out.println("Count: " + counter);
                            }
                        }
                    }
                }
            }
        }
        for (ArrayList sublist : big_list_boi){
            System.out.println(sublist);
        }
    }

    private boolean check_order(Point a, Point b, Point c, Point d){

        if (false) {
            System.out.println("--------------------------");
            System.out.println("Point a: " + a);
            System.out.println("Point b: " + b);
            System.out.println("Point c: " + c);
            System.out.println("Point d: " + d);
            System.out.println("A is below B: " + (a.compareTo(b) == -1));
            System.out.println("A is below C: " + (a.compareTo(c) == -1));
            System.out.println("A is below D: " + (a.compareTo(d) == -1));
            System.out.println("B is below C: " + (b.compareTo(c) == -1));
            System.out.println("B is below D: " + (b.compareTo(d) == -1));
            System.out.println("C is below D: " + (c.compareTo(d) == -1));
            System.out.println("--------------------------");
        }

        return a.compareTo(b) == -1 && a.compareTo(c) == -1 && a.compareTo(d) == -1 && b.compareTo(c) == -1 && b.compareTo(d) == -1 && c.compareTo(d) == -1;
    }

    private boolean check_slope(Point a, Point b, Point c, Point d){
        if(a.SLOPE_ORDER.compare(b,c) == 0){
            return a.SLOPE_ORDER.compare(b,d) == 0;
        }
        else{
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
        System.out.println(n);

    }
}