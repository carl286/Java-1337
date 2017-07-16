package com.l1337.l958;


import com.l1337.common.TreeNode;

import java.util.LinkedList;

public class Solution {

    public boolean isCompleteTree(TreeNode root) {
        if (root == null)
            return true;
        //BFS
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

//        boolean preRowFullyExpanded = true;
        boolean noMoreChildren = false;
        while (!queue.isEmpty())
        {
            TreeNode cur = queue.pollFirst();
            if (noMoreChildren && (cur.left != null || cur.right != null))
                return false;
            if (cur.left == null && cur.right != null)
                return false;
            if (cur.left != null)
                queue.addLast(cur.left);
            else
                noMoreChildren = true;

            if (cur.right != null)
                queue.addLast(cur.right);
            else
                noMoreChildren = true;
        }
        return true;
    }

//    https://leetcode.com/problems/check-completeness-of-a-binary-tree/discuss/205682/JavaC%2B%2BPython-BFS-Solution-and-DFS-Soluiton
//    https://leetcode.com/problems/check-completeness-of-a-binary-tree/discuss/205699/C%2B%2BJava-track-leftmost-height
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
