package com.l1337.l1027;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int longestArithSeqLength(int[] A) {
        if (A.length <= 2)
            return A.length;
        int ret = 1;
        List<Map<Integer, Integer>> list = new ArrayList<>();

        //init
        list.add(new HashMap<>());

        for (int i = 1; i < A.length; ++i )
        {
            list.add(new HashMap<>());

            //Think about why the reverse order didn't work...
            for (int j = i - 1; j >= 0; --j)
            // for (int j = 0; j < i; ++j)
            {
                int delta = A[i] - A[j];

                int len = 1 + list.get(j).getOrDefault(delta, 0);
                ret = Math.max(len, ret);
                if (!list.get(i).containsKey(delta) || list.get(i).get(delta) < len)
                {
                    list.get(i).put(delta, len);
                }

//                list.get(i).put(delta, Math.max(1 + list.get(j).getOrDefault(delta, 0), list.get(i).getOrDefault(delta, 0)));
//                ret = Math.max(ret, list.get(i).get(delta));
            }
        }

        return ret + 1;
    }

    public int longestArithSeqLength2(int[] A) {
        int res = 2, n = A.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for (int j = 0; j < A.length; j++) {
            dp[j] = new HashMap<>();
            for (int i = 0; i < j; i++) {
                int d = A[j] - A[i];
                dp[j].put(d, dp[i].getOrDefault(d, 1) + 1);
                res = Math.max(res, dp[j].get(d));
            }
        }
        return res;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.longestArithSeqLength(new int [] {22,8,57,41,36,46,42,28,42,14,9,43,27,51,0,0,38,50,31,60,29,31,20,23,37,53,27,1,47,42,28,31,10,35,39,12,15,6,35,31,45,21,30,19,5,5,4,18,38,51,10,7,20,38,28,53,15,55,60,56,43,48,34,53,54,55,14,9,56,52}));

    }
}
