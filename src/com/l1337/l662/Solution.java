package com.l1337.l662;


import java.util.LinkedList;

import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/maximum-width-of-binary-tree/discuss/106654/JavaC++-Very-simple-dfs-solution
//    https://leetcode.com/problems/maximum-width-of-binary-tree/discuss/160651/Python-DFS(node)-tm
//    https://leetcode.com/problems/maximum-width-of-binary-tree/discuss/106645/C++Java-*-BFSDFS3liner-Clean-Code-With-Explanation
    public int widthOfBinaryTree(TreeNode root) {
        int ret = 0;
        LinkedList<TreeNode> q = new LinkedList<>();
        if (root != null)
            q.add(root);

        while (!q.isEmpty()) {
            LinkedList<TreeNode> next = new LinkedList<>();
            ret = Math.max(ret, q.size());

            while (!q.isEmpty()) {
                TreeNode cur = q.removeFirst();
                if (cur != null) {
                    next.add(cur.left);
                    next.add(cur.right);
                } else {
                    next.add(null);
                    next.add(null);
                }
            }

            //prune, becareful about the API you're using...
            while (next.size() > 0 && next.peekFirst() == null)
                next.removeFirst();
            while (next.size() > 0 && next.peekLast() == null)
                next.removeLast();

            q = next;

        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        n1.left = n3;
        n1.right = n2;
        n3.left = n5;
        n3.right = n6;
        n2.right = n9;

        System.out.println(s.widthOfBinaryTree(n1));
    }
}
