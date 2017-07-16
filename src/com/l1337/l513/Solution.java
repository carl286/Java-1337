package com.l1337.l513;


import java.util.ArrayList;
import java.util.List;

import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/find-bottom-left-tree-value/discuss/98802/Simple-Java-Solution-beats-100.0!
    public int findBottomLeftValue(TreeNode root) {
        TreeNode ret = root;
        List<TreeNode> row = new ArrayList<>();
        row.add(root);

        while (row.size() != 0) {
            List<TreeNode> nextRow = new ArrayList<>();
            ret = row.get(0);
            for (int i = 0; i < row.size(); ++i) {
                TreeNode n = row.get(i);
                if (n.left != null)
                    nextRow.add(n.left);
                if (n.right != null)
                    nextRow.add(n.right);
            }

            row = nextRow;
        }


        return ret.val;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
