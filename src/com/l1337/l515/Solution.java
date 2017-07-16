package com.l1337.l515;


import java.util.ArrayList;
import java.util.List;

import com.l1337.common.TreeNode;

public class Solution {

    private void nlr(TreeNode root, int depth, List<Integer> list) {
        if (root == null)
            return;

        if (list.size() - 1>= depth) {
            if (list.get(depth) < root.val) {
                list.set(depth, root.val);
            }
        } else {
            list.add(root.val);
        }

        nlr(root.left, 1 + depth, list);
        nlr(root.right, 1 + depth, list);
    }

//    https://leetcode.com/problems/find-largest-value-in-each-tree-row/discuss/167898/python-BFS-using-deque
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();

        nlr(root, 0, ret);

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
