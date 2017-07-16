package com.l1337.l1376;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (n <= 1)
            return 0;
        //src -> direct subordinates
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < manager.length; ++i)
        {
            if (manager[i] >= 0)
            {
                map.putIfAbsent(manager[i], new ArrayList<>());
                map.get(manager[i]).add(i);
            }
        }

        List<Integer> cur = new ArrayList<>();
        List<Integer> acc = new ArrayList<>();
        cur.add(headID);
        acc.add(0);

        int ret = 0;
        while (!cur.isEmpty())
        {
            List<Integer> nextCur = new ArrayList<>();
            List<Integer> nextAcc = new ArrayList<>();
            for (int i = 0; i < cur.size(); ++i)
            {
                if (map.containsKey(cur.get(i)))
                {
                    for(int j = 0; j < map.get(cur.get(i)).size(); ++j)
                    {
                        nextCur.add(map.get(cur.get(i)).get(j));
                        nextAcc.add(acc.get(i) + informTime[cur.get(i)]);
                        ret = Math.max(ret, acc.get(i) + informTime[cur.get(i)]);
                    }
                }

            }

            cur = nextCur;
            acc = nextAcc;
        }

        return ret;
    }

//    https://leetcode.com/problems/time-needed-to-inform-all-employees/discuss/532560/JavaC%2B%2BPython-DFS
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
