package com.l1337.l637;


import java.util.ArrayList;
import java.util.List;

import com.l1337.common.TreeNode;

public class Solution {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ret = new ArrayList<>();

        List<TreeNode> cur = new ArrayList<>();
        if (root != null)
            cur.add(root);

        while (!cur.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            double acc = 0; // we might still have overflow issues for long....
            int count = 0;
            for (int i = 0; i < cur.size(); ++i) {
                TreeNode node = cur.get(i);
                acc += node.val;
                ++count;
                if (node.left != null)
                    next.add(node.left);
                if (node.right != null)
                    next.add(node.right);
            }

            ret.add(acc/count);
            cur = next;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
