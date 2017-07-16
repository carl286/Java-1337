package com.l1337.l497;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Solution {

    //Below method got TLE if the rectangulars are very sparse and sporatic in large domain
//
//    public static class Node {
//        int start;
//        int end;
//        int rightMost;
//        int val;
//        Node left;
//        Node right;
//
//        Node(int s, int e, int val) {
//            start = s;
//            end = e;
//            rightMost = e;
//            this.val = val;
//            left = null;
//            right = null;
//        }
//
//        public boolean collison(Node that) {
//            return !(this.start > that.end || that.start > this.end);
//        }
//    }
//
//    public static class IntervalSearchTree {
//        private Node root;
//
//        public IntervalSearchTree() {
//            root = null;
//        }
//
//        public void insert(int s, int e, int val) {
//            Node newNode = new Node(s,e,val);
//            Node pre = null;
//            Node cur = root;
//            while (cur != null) {
//                pre = cur;
//                cur.rightMost = Math.max(newNode.rightMost, cur.rightMost);
//                if (cur.end < newNode.end) {
//                    cur = cur.right;
//                } else {
//                    cur = cur.left;
//                }
//            }
//
//            if (pre == null) {
//                root = newNode;
//            } else if (pre.end < newNode.end){
//                pre.right = newNode;
//            } else {
//                pre.left = newNode;
//            }
//        }
//
//        public boolean searchCollision(int s, int e, int val) {
//            Node cur = root;
//            Node that = new Node(s,e,val);
//            while (cur != null) {
//                if (cur.collison(that))
//                    return true;
//
//                if (cur.end < that.end) {
//                    if (that.end > cur.rightMost)
//                        return false;
//                    else {
//                        cur = cur.right;
//                    }
//                } else {
//                    cur = cur.left;
//                }
//            }
//
//            return false;
//        }
//
//        private void searchAllCollisionHelper(List<Integer> ret, Node cur, Node that) {
//            if (cur == null)
//                return;
//
//            //is there any possibility
//            if (cur.rightMost < that.rightMost)
//                return;
//
//            if (cur.collison(that)) {
//                ret.add(cur.val);
//            }
//
//            searchAllCollisionHelper(ret, cur.left, that);
//            searchAllCollisionHelper(ret, cur.right, that);
//
//        }
//
//        public List<Integer> searchAllCollision(int s, int e, int val) {
//            List<Integer> ret = new ArrayList<>();
//            Node that = new Node(s,e,val);
//            searchAllCollisionHelper(ret, root, that);
//            return ret;
//        }
//    }
//
//    private IntervalSearchTree ist;
//    private int[][] rects;
//    private Random rx, ry;
//    private int min_x, max_x, min_y, max_y;
//
//    //Interval Tree
//    public Excel(int[][] rects) {
//        ist = new IntervalSearchTree();
//        min_x = Integer.MAX_VALUE;
//        max_x = Integer.MIN_VALUE;
//        min_y = Integer.MAX_VALUE;
//        max_y = Integer.MIN_VALUE;
//        rx = new Random();
//        ry = new Random();
//
//        //insert all elements
//        for (int i = 0; i < rects.length; ++i) {
//            ist.insert(rects[i][0], rects[i][2], i);
//            min_x = Math.min(min_x, rects[i][0]);
//            max_x = Math.max(max_x, rects[i][2]);
//            min_y = Math.min(min_y, rects[i][1]);
//            max_y = Math.max(max_y, rects[i][3]);
//        }
//
//        this.rects = rects;
//    }
//
//    public int[] pick() {
//        int[] ret = new int[2];
//        while (true) {
//            ret[0] = rx.nextInt((max_x - min_x) + 1) + min_x;
//            ret[1] = ry.nextInt((max_y - min_y) + 1) + min_y;
//
//            List<Integer> list = ist.searchAllCollision(ret[0], ret[0], 0);
//
//            IntervalSearchTree ist2 = new IntervalSearchTree();
//
//            for (int i : list) {
//                ist2.insert(rects[i][1], rects[i][3], i);
//            }
//
//            if (ist2.searchCollision(ret[1], ret[1], 0)) {
//                return ret;
//            }
//        }
//    }
//
//    public static void main(String [] args) {
//        Excel s = new Excel(new int[][]{{-58953616,-40483558,-58953446,-40482555},
//                {76369640, 94978791, 76371036, 94979394},
//                {80970826, -37466957, 80971657, -37466388},
//                {-79821573, -4177978, -79820536, -4177925}});
////        Excel s = new Excel(new int[][]{{-2,-2,-1,-1}, {1,0,3,0}});
//        int rounds = 1;
//        int i = 0;
//        while (i < rounds) {
//            int [] p = s.pick();
//            System.out.println(p[0] + "\t" + p[1]);
//            ++i;
//        }
//        System.out.println();
//    }



    //blow method can not handle if rectangular degrades to interval
//    private int[][] rects;
//    private long [] x;
//    private long [] y;
//    private long [] squares;
//    private Random rx, ry;
//    private long total;
//
//
//    private long getArea(int [] rect) {
//        return ((long)(rect[2] - rect[0])) * (rect[3] - rect[1]);
//    }
//
//    public Excel(int[][] rects) {
//        this.rects = rects;
//        long acc = 0;
//        squares = new long [rects.length];
//        x = new long [rects.length];
//        y = new long [rects.length];
//        for (int i = 0; i < rects.length; ++i) {
//            x[i] = (rects[i][2] - rects[i][0]);
//            y[i] = (rects[i][3] - rects[i][1]);
//            acc += x[i] * y[i];
//            squares[i] = acc;
//        }
//
//        total = squares[squares.length - 1];
//        rx = new Random();
//        ry = new Random();
//    }
//
//    public int[] pick() {
//        long square = ThreadLocalRandom.current().nextLong(total + 1);
//        int index = Arrays.binarySearch(squares, square);
//        if (index < 0) {
//            index = - (1 + index);
//        }
//
//        int[] ret = new int[2];
//        ret[0] = rx.nextInt((rects[index][2] - rects[index][0]) + 1) + rects[index][0];
//        ret[1] = ry.nextInt((rects[index][3] - rects[index][1]) + 1) + rects[index][1];
//        return ret;
//    }
//
//        public static void main(String [] args) {
////        Excel s = new Excel(new int[][]{{-58953616,-40483558,-58953446,-40482555},
////                {76369640, 94978791, 76371036, 94979394},
////                {80970826, -37466957, 80971657, -37466388},
////                {-79821573, -4177978, -79820536, -4177925}});
//        Excel s = new Excel(new int[][]{{-2,-2,-1,-1}, {1,0,3,0}});
//        int rounds = 10;
//        int i = 0;
//        while (i < rounds) {
//            int [] p = s.pick();
//            System.out.println(p[0] + "\t" + p[1]);
//            ++i;
//        }
//        System.out.println();
//    }

    private int[][] rects;
    private long points [];
    private long x [];
    private long y [];
    private long totalPoints;

    public Solution(int[][] rects) {
        totalPoints = 0;
        points = new long [rects.length];
        x = new long [rects.length];
        y = new long [rects.length];
        for (int i = 0; i < rects.length; ++i) {
            x[i] = (long) rects[i][2] - rects[i][0] + 1;
            y[i] = (long) rects[i][3] - rects[i][1] + 1;
            totalPoints += x[i] * y[i];
            points[i] = totalPoints;
        }

        this.rects = rects;
    }

//    https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/discuss/155452/Java-TreeMap-solution-only-one-random-per-pick
    public int[] pick() {
        long point = ThreadLocalRandom.current().nextLong(1, totalPoints + 1);
//        System.out.println("point: \t" + point);
        int index = Arrays.binarySearch(points, point);
        if (index < 0) {
            index = - (1 + index);
        }
        if (index != 0) {
            point = points[index] - points[index - 1];
        }

        --point;
        int ret [] = new int[2];

        ret[0] = (int)(point % x[index]) + rects[index][0];
        ret[1] = (int)(point / x[index]) + rects[index][1];
        return ret;
    }

            public static void main(String [] args) {
//        Excel s = new Excel(new int[][]{{-58953616,-40483558,-58953446,-40482555},
//                {76369640, 94978791, 76371036, 94979394},
//                {80970826, -37466957, 80971657, -37466388},
//                {-79821573, -4177978, -79820536, -4177925}});
        Solution s = new Solution(new int[][]{{-2,-2,-1,-1}, {1,0,3,0}});
        int rounds = 20;
        int i = 0;
        while (i < rounds) {
            int [] p = s.pick();
            System.out.println(p[0] + "\t" + p[1]);
            ++i;
        }
        System.out.println();
    }
}
