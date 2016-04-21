package com.l1337.l298;


import com.l1337.common.TreeNode;


//	Binary Tree Longest Consecutive Sequence, 298
//Given a binary tree, find the length of the longest consecutive sequence path.
//The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
//	For example,
//	1
//	 \
//	  3
//	 / \
//	2   4
//	     \
//		  5
//	Longest consecutive sequence path is 3-4-5, so return 3.
//			2
//			\
//			3
//			/
//		   2
//		  /
//		 1
//	Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.
//	http://buttercola.blogspot.com/2015/12/blog-post.html

public class Solution {

    private int global;

    //Try have a solution without returning a object/ or having this global variable...
    int longestConsecutiveHelper(TreeNode root) {
        if (root == null)
            return 0;

        int left = longestConsecutiveHelper(root.left);
        int right = longestConsecutiveHelper(root.right);

        int local = 1;
        if (left != 0 && root.left.val == root.val + 1)
            local = 1 + left;
        if (right != 0 && root.right.val == root.val + 1)
            local = Math.max(1 + right, local);
        global = Math.max(local, global);

        return local;
    }

    public int longestConsecutive(TreeNode root) {
        global = 0;

        longestConsecutiveHelper(root);
        return global;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
