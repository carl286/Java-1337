package com.l1337.l889;


import com.l1337.common.TreeNode;

public class Solution {

    public TreeNode constructFromPrePost(int[] pre, int i, int[] post, int u, int length) {
        if (length <= 0)
            return null;
        if (length == 1)
            return new TreeNode(pre[i]);

        //length must be >= 2 here
        TreeNode root = new TreeNode(pre[i]);

        int leftLegnth = 1;
        int val = pre[i + 1];

        while (leftLegnth < length)
        {
            if (post[u + leftLegnth - 1] == val)
                break;
            else
                ++leftLegnth;
        }
        root.left = constructFromPrePost(pre, i + 1, post, u, leftLegnth);

        root.right = constructFromPrePost(pre, i + leftLegnth + 1, post, u + leftLegnth, length - leftLegnth - 1);

        return root;
    }

//    https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161268/C%2B%2BJavaPython-One-Pass-Real-O(N)
    public TreeNode constructFromPrePost(int[] pre, int[] post) {

        return constructFromPrePost(pre, 0, post, 0, pre.length);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int pre [] = new int [] {1,2,4,5,3,6,7};
        int post [] = new int [] {4,5,2,6,7,3,1};
        TreeNode root = (s.constructFromPrePost(pre, post));
        System.out.println(root.val);
    }
}
