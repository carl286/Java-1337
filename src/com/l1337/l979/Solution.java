package com.l1337.l979;


import com.l1337.common.TreeNode;

public class Solution {

    class Node
    {
        int numNodes;
        int numCoins;
        int inBounds;
    }
    private Node distributeCoinsHelper(TreeNode root)
    {
        Node ret = new Node();
        if (root == null)
            return ret;
        //inbound from outside
        Node left = distributeCoinsHelper(root.left);
        Node right = distributeCoinsHelper(root.right);

        ret.inBounds += left.inBounds;
        ret.inBounds += right.inBounds;
        int delta = (1 + left.numNodes + right.numNodes) - (root.val + left.numCoins + right.numCoins);
        if (delta > 0)
            ret.inBounds += delta;

        //inbound due to left child
        delta = left.numCoins - left.numNodes;
        if (delta > 0)
            ret.inBounds += delta;

        delta = right.numCoins - right.numNodes;
        if (delta > 0)
            ret.inBounds += delta;

        ret.numCoins = left.numCoins + right.numCoins + root.val;
        ret.numNodes = 1 + left.numNodes + right.numNodes;

        return ret;
    }
    public int distributeCoins(TreeNode root) {
        return distributeCoinsHelper(root).inBounds;
    }

//    https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221930/JavaC%2B%2BPython-Recursive-Solution
//    https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221939/C%2B%2B-with-picture-post-order-traversal
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
