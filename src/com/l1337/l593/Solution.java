package com.l1337.l593;


import java.util.Arrays;
import java.util.Comparator;

public class Solution {

//    https://leetcode.com/problems/valid-square/discuss/103442/C++-3-lines-(unordered_set)
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        //no duplicates, all coordinats must be unique..
        //let's sort
        int [][] array = new int [4][];
        array[0] = p1;
        array[1] = p2;
        array[2] = p3;
        array[3] = p4;
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return Integer.compare(o1[1], o2[1]);
                else
                    return o1[0] - o2[0];
            }
        });


        //ensure unique
        for (int i = 1; i < array.length; ++i) {
            if (array[i][0] == array[i-1][0] && array[i][1] == array[i-1][1])
                return false;
        }

       //01, 02, 23, 13
        long l1 = distance(array[0], array[1]);
        long l2 = distance(array[0], array[2]);
        long l3 = distance(array[3], array[1]);
        long l4 = distance(array[3], array[2]);

        return l1 == l2 && l2 == l3 && l3 == l4 && (l1 + l2 == distance(array[1], array[2]));
    }

    private long distance(int []a, int []b) {
        return (a[0]-b[0]) * (a[0]- b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] p1 = new int [] {0,0};
        int [] p2 = new int [] {1,1};
        int [] p3 = new int [] {1,0};
        int [] p4 = new int [] {0,1};
        System.out.println(s.validSquare(p1,p2,p3,p4));
    }
}
