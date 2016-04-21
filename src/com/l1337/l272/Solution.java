package com.l1337.l272;


import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.List;


//	Closest Binary Search Tree Value II, 272
//Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
//Note: Given target value is a floating point.
// You may assume k is always valid, that is: k ≤ total nodes.
// You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
// Follow up: Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

public class Solution {

    //You should optimize it a bit more instead of like right now.....
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ret = new ArrayList<>();

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int n = 4;
    }
}

//	Hint:
//	Consider implement these two helper functions:
//	getPredecessor(N), which returns the next smaller node to N.
//	getSuccessor(N), which returns the next larger node to N.
//	Try to assume that each node has a parent pointer, it makes the problem much easier.
//	Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
//	You would need two stacks to track the path in finding predecessor and successor node separately.
//	https://leetcode.com/discuss/55240/ac-clean-java-solution-using-two-stacks
//	https://leetcode.com/discuss/55682/o-logn-java-solution-with-two-stacks-following-hint