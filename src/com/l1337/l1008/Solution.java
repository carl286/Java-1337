package com.l1337.l1008;


import com.l1337.common.TreeNode;

public class Solution {

    public TreeNode bstFromPreorder(int[] preorder, int start, int end) {
        if (start >= end)
            return null;
        TreeNode root = new TreeNode(preorder[start]);
        int leftStart = start + 1;
        int leftEnd = leftStart;
        while (leftEnd < end && preorder[leftEnd] < preorder[start])
            ++leftEnd;
        TreeNode left = bstFromPreorder(preorder, leftStart, leftEnd);
        TreeNode right = bstFromPreorder(preorder, leftEnd, end);
        root.left = left;
        root.right = right;
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {

        return bstFromPreorder(preorder, 0, preorder.length);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
