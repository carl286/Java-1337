package com.l1337.l114;


import com.l1337.common.TreeNode;

public class Solution {

    public void flatten(TreeNode root) {

        TreeNode pre = null;
        TreeNode next = null;
        while (root != null)
        {
            if (root.left == null)
            {
                //root.left = pre;
                root.left = null;
                pre = root;
                next = root.right;
            }
            else if (root.right == null) {
                next = root.left;
                root.right = root.left;
                //root.left = pre;
                root.left = null;
                pre = root;
            }
            else
            {
                TreeNode left = root.left;
                next = root.left;
                while (left.right != null)
                    left = left.right;
                left.right = root.right;
                //root.right = null;

                root.right = root.left;
                //root.left = pre;
                root.left = null;
                pre = root;
            }

            root = next;
        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);


        n1.left = n2;
        n1.right = n5;
        n2.left = n3;
        n2.right = n4;
        n5.right = n6;

/*
        n1.left = n2;
        n2.left = n5;
        n5.left = n3;
        n3.left = n4;
        n4.right = n6;
*/
        s.flatten(n1);
        System.out.println(n1.val);
    }
}
