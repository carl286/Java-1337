package com.l1337.l501;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l1337.common.TreeNode;

public class Solution {
    private int visit(TreeNode node, Map<Integer, Integer> map, int maxCount) {
        //should not happen
//        if (node == null)
//            return;
        int newCount = map.getOrDefault(node.val,0) + 1;
        map.put(node.val, newCount);
        return Math.max(newCount, maxCount);
    }


//    https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java

//    https://leetcode.com/problems/find-mode-in-binary-search-tree/discuss/98101/Proper-O(1)-space
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 1;
        //convert object [] to primitive []
        while (root != null) {
            if (root.left == null) {
                maxCount = visit(root, map, maxCount);
                root = root.right;
            } else {
                TreeNode left = root.left;
                while (left.right != null && left.right != root) {
                    left = left.right;
                }
                if (left.right == null) {
                    left.right = root;
                    root = root.left;
                } else {
                    left.right = null;
                    maxCount = visit(root, map, maxCount);
                    root = root.right;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        Integer imaxCount = new Integer(maxCount);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(imaxCount))
                list.add(entry.getKey());
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
