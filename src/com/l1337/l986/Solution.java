package com.l1337.l986;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private boolean isIntersect(int [] a, int []b)
    {
        return !((a[1] < b[0]) || (b[1] < a[0]));
    }
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        ArrayList<int []> tmp = new ArrayList<>();
        for (int i = 0, j = 0; i < A.length && j < B.length;)
        {
            if (isIntersect(A[i], B[j]))
            {
                tmp.add(new int []{Math.max(A[i][0], B[j][0]), Math.min(A[i][1], B[j][1])});
            }

            if (A[i][1] < B[j][1])
            {
                ++i;
            }
            else
            {
                ++j;
            }
        }


        return tmp.toArray(new int [tmp.size()][]);
    }


























    public int[][] intervalIntersectionMarch20_21g(int[][] firstList, int[][] secondList) {
        List<int[]> tmp = new ArrayList<>();
        for(int i = 0, j = 0; i < firstList.length && j < secondList.length;)
        {
            if(isIntersect(firstList[i], secondList[j]))
            {
                tmp.add(new int [] {Math.max(firstList[i][0], secondList[j][0]), Math.min(firstList[i][1], secondList[j][1])});
            }

            if (firstList[i][1] < secondList[j][1])
            {
                ++i;
            }
            else
            {
                ++j;
            }
        }


        int [][] ret= new int [tmp.size()][2];
        for(int i = 0; i < ret.length; ++i)
            ret[i] = tmp.get(i);
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int[][] firstList = new int [][]{{0,2}, {5,10}};
        int[][] secondList = new int [][]{{1,5}};
        int [][] ret = s.intervalIntersectionMarch20_21g(firstList, secondList);
        for(int i = 0; i < ret.length; ++i)
        {
            System.out.print(ret[i][0] + "\t" + ret[i][1]);
        }
    }
}
