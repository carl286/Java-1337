package com.l1337.l270;


//	Closest Binary Search Tree Value I, 270
//Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
//Note: Given target value is a floating point. You are guaranteed to have only one unique value in the BST that is closest to the target.

//	http://buttercola.blogspot.com/2015/09/leetcode-closest-binary-search-tree.html
//OR recursions....
//	https://segmentfault.com/a/1190000003797291

import com.l1337.common.TreeNode;

public class Solution {

    //You should optimize it a bit more instead of like right now.....
    public int closestValue(TreeNode root, double target) {
        //root is null at begin
        int ret = root.val;
        double delta = Math.abs(root.val - target);
        while (root != null) {
            double curDelta = Math.abs(root.val - target);
            if (curDelta < delta) {
                delta = curDelta;
                ret = root.val;
            }
            if (root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return ret;
    }


    /*
    //Below code is complete wrong...
    public int closestValueI(TreeNode root, double target) {
        //root is null at begin
        int ret = root.val;
        double delta = Math.abs(root.val - target);
        boolean flag;


        //less ,true
        if (root.val < target)
            flag = true;
        else
        //larger, false;
            flag = false;

        while (root != null) {
            double curDelta = Math.abs(root.val - target);
            if (curDelta < delta) {
                delta = curDelta;
                ret = root.val;
            }

            if (root.val < target && !flag) {
                break;
            } if (root.val > target && flag) {
                break;
            }
            if (root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return ret;
    }
    */

    public static void main(String [] args) {
        Solution s = new Solution();
        int n = 4;
    }
}
