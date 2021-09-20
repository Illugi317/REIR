package com.company;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Objects;


public class Percolation {

    private static final class Pair {
        private final int row;
        private final int col;

        private Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    private final boolean[][] opened;
    private final int top_root;
    private final int bottom_root;
    //private int[] bottom_cells;
    private final int size;
    private int sites = 0;
    //private WeightedQuickUnionUF

    private final WeightedQuickUnionUF perc_uf;
    private final WeightedQuickUnionUF is_fullUF;
    public Percolation(int N){
        //Create n-by-n-grid with all cells blocked
        if (N<=0){
            throw new IllegalArgumentException();
        }
        size = N;
        //uf = new WeightedQuickUnionUF(size*size + 1);
        perc_uf = new WeightedQuickUnionUF((size*size)+2);
        is_fullUF = new WeightedQuickUnionUF((size*size)+1);
        top_root = (size*size);
        bottom_root =  (size*size) + 1;
        //bottom_cells = new int[size];
        // top_root to connect [0-N-1] 0.1 ... 0,n-1
        // bottom_root to connect [n-1,[0-n-1]] n-1,0 ... n-1,n-1
        for (int i = 0; i < size; i++) {
            perc_uf.union(top_root, get_node_index(0, i));
            perc_uf.union(bottom_root, get_node_index(size - 1, i));
            is_fullUF.union(top_root, get_node_index(0, i));
            //bottom_cells[i] = get_node_index(size-1,i);
        }
        opened = new boolean[size][size];

    }
    public void open(int row, int col){
        //open the site (row,col) if it is not open already
        if (!is_coords_valid(row,col)){
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)){
            sites += 1;
        }
        else{
            return;
        }
        opened[row][col] = true;
        Pair[] array_boi = get_valid_neighbours(row,col);
        for(Pair pairs: array_boi){
            if (pairs != null) {
                if (isOpen(pairs.getRow(), pairs.getCol())){
                    perc_uf.union(get_node_index(row,col),get_node_index(pairs.getRow(), pairs.getCol()));
                    is_fullUF.union(get_node_index(row,col),get_node_index(pairs.getRow(), pairs.getCol()));
                }
            }
        }
    }

    public boolean isOpen(int row , int col){
        // return true if cell is open else return false
        if (!is_coords_valid(row,col)){
            throw new IndexOutOfBoundsException();
        }
        return opened[row][col];
    }
    public boolean isFull(int row, int col) {
        //return true if the site is fucking blocked else false
        //qf
        if (isOpen(row,col)) {
            return is_fullUF.find(get_node_index(row, col)) == is_fullUF.find(top_root);
        }
        else{
            return false;
        }
    }
    public int numberOfOpenSites(){
        return sites;
    }
    public boolean percolates() {
        // does the system percolate
        //QF
        //for(int cell : bottom_cells){
        //    if (quickFindUF.find(cell) == quickFindUF.find(top_root)) {
        //        return true;
        //    }
        //}
        //return false;

        //Edge case where N=1
        if (size == 1){
            if (isOpen(0,0)) {
                return true;
            }
            else{
                return false;
            }
        }
        return perc_uf.find(bottom_root) == perc_uf.find(top_root);
        //WQU
        //return uf.connected(bottom_y,top_y)
    }

    private boolean is_coords_valid(int row, int col){
        return (0 <= row && row <= size - 1) && (0 <= col && col <= size - 1);
    }

    private Pair[] get_valid_neighbours(int row, int col) {
        //Pair current_node = new Pair(row, col);
        //Pair[] testboi = new Pair(0,0);
        Pair[] array_boi = new Pair[4];
        int counter = 0;
        //check above
        if (is_coords_valid(row-1,col)){
            // if (isFull(row-1, col))
            array_boi[counter] = new Pair(row-1,col);
            counter++;
        }
        //check left
        if (is_coords_valid(row,col-1)){
            array_boi[counter] = new Pair(row,col-1);
            counter++;
        }
        //check below
        if (is_coords_valid(row+1,col)){
            array_boi[counter] = new Pair(row+1,col);
            counter++;
        }
        //check right
        if (is_coords_valid(row,col+1)){
            array_boi[counter] = new Pair(row,col+1);
        }
        return array_boi;
    }

    private int get_node_index(int row, int col) {
        return row * size + col;
    }

    public static void main(String[] args){
        //create more unit tests
        Percolation perkyboi = new Percolation(1);
        System.out.println(perkyboi.isFull(0,0));
        System.out.println(perkyboi.isOpen(0,0));
        perkyboi.open(0,0);
        System.out.println(perkyboi.numberOfOpenSites());
        System.out.println(perkyboi.isOpen(0,0));
        System.out.println(perkyboi.isFull(0,0));
        System.out.println(perkyboi.percolates());
        /*Pair[]dong = perkyboi.get_valid_neighbours(1, 0);
        System.out.println(dong);
        for (Pair pair : dong) {
            if (pair != null) {
                System.out.println(pair.getRow() + " " + pair.getCol());
            }
        }
        */
    }
}