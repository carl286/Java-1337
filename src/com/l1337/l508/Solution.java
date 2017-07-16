package com.l1337.l508;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l1337.common.TreeNode;

public class Solution {
    private int dfs(TreeNode root, Map<Integer, Integer> map, int [] max_occr) {
        if (root == null)
            return 0;
        int left = dfs(root.left, map, max_occr);
        int right = dfs(root.right, map, max_occr);
        int total = left + right + root.val;

        int cur = map.getOrDefault(total, 0);
        ++cur;
        map.put(total, cur);
        if (max_occr[0] < cur)
            max_occr[0] = cur;

        return total;
    }
//    https://leetcode.com/problems/most-frequent-subtree-sum/discuss/98664/Verbose-Java-solution-postOrder-traverse-HashMap-(18ms)
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        int [] max_occr = new int[1];
        dfs(root, map, max_occr);
        List<Integer> array = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max_occr[0]) {
                array.add(entry.getKey());
            }
        }

        int[] ret = new int [array.size()];
        for (int i = 0; i < ret.length; ++i)
            ret[i] = array.get(i);
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
