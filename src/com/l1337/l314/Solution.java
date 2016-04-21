package com.l1337.l314;

//	Binary Tree Vertical Order Traversal, 314
//	http://buttercola.blogspot.com/2016/01/leetcode-binary-tree-vertical-order.html, clear problem desciption
//	http://algorithms.tutorialhorizon.com/print-the-binary-tree-in-vertical-order-path/, better problem desciption
//http://www.programcreek.com/2014/04/leetcode-binary-tree-vertical-order-traversal-java/   <- this might be a relative reasonable sol...

//Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
//
//	If two nodes are in the same row and column, the order should be from left to right.
//
//			Examples:
//	Given binary tree [3,9,20,null,null,15,7],
//
//			3
//			/ \
//			9  20
//			/  \
//			15   7
//
//
//			return its vertical order traversal as:
//
//			[
//			[9],
//			[3,15],
//			[20],
//			[7]
//			]
//
//
//	Given binary tree [3,9,20,4,5,2,7],
//
//	_3_
//	/   \
//			9    20
//			/ \   / \
//			4   5 2   7
//
//
//			return its vertical order traversal as:
//
//			[
//			[4],
//			[9],
//			[3,5,2],  <--- especial this one, no sense of doing this at all.......
//			[20],
//			[7]
//			]

import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Solution {

    private void verticalOrderHelper(TreeMap<Integer, List<Integer>>map, TreeNode root, int pos) {
        if (root == null)
            return;

        if (!map.containsKey(pos))
            map.put(pos, new ArrayList<>());
        map.get(pos).add(root.val);
        verticalOrderHelper(map, root.left, pos-1);
        verticalOrderHelper(map, root.left, pos+1);
    }

    //Below solution is wrong... See posts for why.....
    //Main problem is that the problem itself is extremely unclear....
    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        verticalOrderHelper(map, root, 0);
        List<List<Integer>> ret = new ArrayList<>();
        for (List<Integer> l : map.values())
            ret.add(l);
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
