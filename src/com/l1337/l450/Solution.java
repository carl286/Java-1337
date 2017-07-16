package com.l1337.l450;


import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/delete-node-in-a-bst/description/
//    https://leetcode.com/problems/delete-node-in-a-bst/discuss/93298/Iterative-solution-in-Java-O(h)-time-and-O(1)-space
    //how to make your code cleaner
//    https://leetcode.com/problems/delete-node-in-a-bst/discuss/93296/Recursive-Easy-to-Understand-Java-Solution
//    https://leetcode.com/problems/delete-node-in-a-bst/discuss/93328/Java-Easy-to-Understand-Solution
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode oldRoot = root, pre = null;
        boolean isLeftChild = true;
        while (root != null && root.val != key) {
            pre = root;
            if (key  < root.val) {
                root = root.left;
                isLeftChild = true;
            } else {
                root = root.right;
                isLeftChild = false;
            }
        }

        //not found
        if (root == null)
            return oldRoot;

        if (root.right != null) {
            TreeNode cur, subPre;
            for (cur = root.right, subPre=null; cur.left != null; subPre = cur, cur = cur.left);

            if (subPre != null) {
                subPre.left = cur.right;
                cur.right = root.right;
                cur.left = root.left;
            } else {
                cur.left = root.left;
            }

            if (pre != null) {
                if (isLeftChild)
                    pre.left = cur;
                else
                    pre.right = cur;
            } else {
                oldRoot = cur;
            }

        } else {
            if (pre != null) {
                if (isLeftChild)
                    pre.left = root.left;
                else
                    pre.right = root.left;
            } else {
                oldRoot = root.left;
//                root.left = null;
            }
        }
        return oldRoot;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
