package com.l1337.l546;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

//    https://leetcode.com/problems/remove-boxes/discuss/101310/Java-top-down-and-bottom-up-DP-solutions
    public int removeBoxes(int[] boxes) {
        if (boxes.length == 0)
            return 0;
        int [][] dp = new int[boxes.length][];
        for (int i = 0; i < dp.length; ++i)
            dp[i] = new int [boxes.length];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < boxes.length; ++i) {
            List<Integer> cur = map.getOrDefault(boxes[i], new ArrayList<>());
            cur.add(i);
            map.put(boxes[i], cur);
        }

        for (int i = dp.length - 1; i >= 0; --i) {
            for (int j = i; j < dp[i].length; ++j) {
                //question, how to expands
                List<Integer> cur = map.get(boxes[j]);
                int index = Collections.binarySearch(cur, j);
                dp[i][j] = 1 + (i <= (j - 1) ? dp[i][j-1] : 0);
                //Count accs;
                int acc = 0;
//                https://leetcode.com/problems/remove-boxes/discuss/101319/Passed-5160-test-cases-straight-forward-DP-using-2D-Array-anyone-know-why-am-I-wrong
                //please think hard why this is not working....
                for(int k = index - 1; k >= 0 && (i <= cur.get(k)); k--) {
                    if (cur.get(k) + 1 != cur.get(k+1)) {
                        //there is a gap
                        acc += dp[cur.get(k) + 1][cur.get(k+1) - 1];
                    }
                    dp[i][j] = Math.max(dp[i][j], acc + (index - k + 1) * (index - k + 1) + (cur.get(k) > 0 ? dp[i][cur.get(k)-1]: 0));
                }
            }
        }

        return dp[0][boxes.length - 1];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.removeBoxes(new int []{1, 3, 2, 2, 2, 3, 4, 3, 1}));
        System.out.println(s.removeBoxes(new int []{3, 8, 8, 5, 5, 3}));
//        System.out.println(s.removeBoxes(new int []{3, 8, 8, 5, 5, 3, 9, 2, 4, 4, 6, 5, 8, 4, 8, 6, 9, 6, 2, 8, 6, 4, 1, 9, 5, 3, 10, 5, 3, 3, 9, 8, 8, 6, 5, 3, 7, 4, 9, 6, 3, 9, 4, 3, 5, 10, 7, 6, 10, 7}));
    }
}
