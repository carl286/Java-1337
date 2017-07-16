package com.l1337.l519;


import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class Solution {
//    public static class Node implements Comparator<Node> {
//        int row;
//        int col;
//        Node(int row, int col) {
//            this.row = row;
//            this.col = col;
//        }
//
//        public boolean equals(Object that) {
//            Node nt = (Node) that;
//            return this.row == nt.row && this.col == nt.col;
//        }
//
//        public int compare(Node n1, Node n2) {
//            if (n1.row == n2.row)
//                return n1.col - n2.col;
//            else
//                return n1.row - n2.row;
//        }
//    }

//    Memory Limit Exceeded
    private final int n_rows;
    private final int n_cols;
    private final int [] map;
    private int last_index;

    private void swap(int i, int j) {
        int tmp = map[i];
        map[i] = map[j];
        map[j] = tmp;
    }

    public Solution(int n_rows, int n_cols) {
        this.n_rows = n_rows;
        this.n_cols = n_cols;
        map = new int [n_rows * n_cols];
        for (int i = 0; i < map.length; ++i)
            map[i] = i;
        last_index = map.length - 1;
    }

    public int[] flip() {
        if (last_index >= 0) {
            int index = (int)(Math.random() * (last_index + 1));
            int val = map[index];
//            System.out.println("debug:" + val + "\t" + index + "\t" + last_index);
            swap(index, last_index--);
            return new int []{val / n_cols, val % n_cols};
        } else {
            return null;
        }
    }

    public void reset() {
        last_index = map.length - 1;
    }

//    https://leetcode.com/problems/random-flip-matrix/discuss/154053/Java-AC-Solution-call-Least-times-of-Random.nextInt()-function
    /*
    Similar idea with some explanation:
Here we are modeling the matrix as a 1d array with initial size of row*cols.
For each flip, randomly pick an index from 0 to size-1 and flip it.
int r = rand.nextInt(total--);
Then swap that flipped element with the tail element (index: size-1), store that mapping info (key: origin index, value: index of the tail) into a Map and decrease the size.
map.put(r, map.getOrDefault(total, total));
Next time when we randomly pick a same index we can go to the map and find the actual element stores in that index
int x = map.getOrDefault(r, r);
     */
//    https://leetcode.com/problems/random-flip-matrix/discuss/155341/Step-By-Step-Algorithm-Explanation

    /*
    Map<Integer, Integer> map;
    int m, n, total;
    Random rand;

    public Excel(int n_rows, int n_cols) {
        map = new HashMap<>();
        rand = new Random();
        m = n_rows;
        n = n_cols;
        reset();
    }

    public int[] flip() {
        int i = rand.nextInt(total--);
        int x = map.getOrDefault(i, i);
        map.put(i, map.getOrDefault(total, total));
        map.put(total, x);
        return new int[]{x / n, x % n};
    }

    public void reset() {
        total = m * n;
    }
     */
    public static void main(String [] args) {
        Solution s = new Solution(2,2);
        for (int i = 0; i < 4; ++i) {
            int [] output = s.flip();
            System.out.println(output[0] + "\t" + output[1]);
        }
//        System.out.println((int)0.99);
    }
}
