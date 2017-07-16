package com.l1337.l498;


import java.util.*;

import static java.util.Arrays.asList;

public class Solution {
    private static int [][][] directiosn = {{{-1,+1}, {0, +1}, {+1, 0}}, {{+1,-1}, {+1, 0}, {0, +1}}};

    private boolean isInBoard(int [][] matrix, int i, int j) {
        return (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length);
    }

//    https://leetcode.com/problems/diagonal-traverse/discuss/97756/Java-solution-easy-to-understand-O(n)-7ms
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0)
            return new int [matrix.length];

        int[] ret = new int [matrix.length * matrix[0].length];
        int cur = 0;
        int row = 0;
        int i = 0, j = 0;
        ret[cur++] = matrix[i][j];
        while (cur < ret.length) {
            int k = 0;
            while (k < directiosn[row].length) {
                int i2 = i + directiosn[row][k][0];
                int j2 = j + directiosn[row][k][1];
                if (isInBoard(matrix, i2, j2)) {
                    i = i2;
                    j = j2;
                    break;
                }
                ++k;
            }

            ret[cur++] = matrix[i][j];
            if (k != 0)
                row = 1 - row;
        }

        return ret;
    }


    public int[] findDiagonalOrder498V1(int[][] matrix) {

        //seems the test cases didn't like my assumption
        if (matrix.length <= 0 || matrix[0].length <= 0)
            return new int [0];

        boolean up = true;
        int startR = 0, startC = 0, total = 0;
        int m = matrix.length, n = matrix[0].length;
        int [] ret = new int [m * n];
        while (total < m*n)
        {
            int r = startR, c = startC;
            if (up)
            {
                while (r >= 0 && c < n)
                {
                    ret[total++] = matrix[r][c];
                    --r;
                    ++c;
                }
                up = false;
                //go back by 1
                ++r; --c;
                // (r, c) -> (r, c + 1)
                if (c + 1 < n)
                {
                    startR = r;
                    startC = c + 1;
                }
                else
                {
                    startR = r + 1;
                    startC = c;

                }
            }
            else
            {
                while (c >= 0 && r < m)
                {
                    ret[total++] = matrix[r][c];
                    --c;
                    ++r;
                }
                up = true;
                // go back by 1
                ++c; --r;
                if (r + 1 < m)
                {
                    startR = r + 1;
                    startC = c;
                }
                else
                {
                    startR = r;
                    startC = c + 1;
                }
            }
        }

        return ret;
    }


//    https://leetcode.com/problems/diagonal-traverse-ii
    //this is a wired changed problem....

    //    https://leetcode.com/problems/diagonal-traverse-ii/discuss/597741/Clean-Simple-Easiest-Explanation-with-a-picture-and-code-with-comments
//    https://leetcode.com/problems/diagonal-traverse-ii/discuss/597698/JavaC%2B%2B-HashMap-with-Picture-Clean-code-O(N)
//    https://leetcode.com/problems/diagonal-traverse-ii/discuss/597690/Python-Simple-BFS-solution-with-detailed-explanation
    // you can get the boundary from day 1
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        if (nums.size() <= 0 )
            return new int[0];
        int m = nums.size();
        List<Integer> tmp = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < m; ++i){
            int size = nums.get(i).size();
            if (size > 0)
                map.put(i, size);
        }

        //seems findDiagonalOrder498V1 helps it a lot
        int total = 0, startR = 1;
        while (map.size() > 0)
        {
            Set<Integer> toRemove = new HashSet<>();
            for (Integer key : map.headMap(startR, false).descendingKeySet())
            {
                tmp.add(nums.get(key).get(total - key));
                if (total - key + 1 >= nums.get(key).size())
                    toRemove.add(key);
            }
            for (Integer key : toRemove)
                map.remove(key);
            //always go up
            ++startR;
            ++total;
        }

        int [] ret = new int [tmp.size()];
        for (int i = 0; i < tmp.size(); ++i)
            ret[i] = tmp.get(i);
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        for(int i : s.findDiagonalOrder498V1(new int [][]{{1},{2},{3}})){
//            System.out.print(i + "\t");
//        }
        for(int i : s.findDiagonalOrder(asList(
                asList( 1, 2, 3 ),
                asList( 4, 5, 6 ),
                asList( 7, 8, 9 ) ))){
            System.out.print(i + "\t");
        }
    }
}
