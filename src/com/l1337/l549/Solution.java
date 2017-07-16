package com.l1337.l549;

import com.l1337.common.TreeNode;

public class Solution {

    /*
    Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:

Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:

Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].

     */

    static class Node {
//        int longest_increase_sequence_end_with_node;
//        int longest_decrese_sequence_end_with_node;
        int longest_increase_sequence_start_with_node;
        int longest_decrease_sequence_start_with_node;
        int optimzal;
        Node() {
            this.longest_increase_sequence_start_with_node = 0;
            this.longest_decrease_sequence_start_with_node = 0;
//            this.longest_increase_sequence_end_with_node = 0;
//            this.longest_decrese_sequence_end_with_node = 0;
            this.optimzal = 0;
        }
    }

    Node helper(TreeNode root) {
        Node ret = new Node();
        if (root != null) {
            Node left = helper(root.left);
            Node right = helper(root.right);
            //init result
//            ret.longest_decrese_sequence_end_with_node = 1;
//            ret.longest_increase_sequence_end_with_node = 1;
            ret.longest_decrease_sequence_start_with_node = 1;
            ret.longest_increase_sequence_start_with_node = 1;
            ret.optimzal = 1;

            ret.optimzal = Math.max(ret.optimzal, Math.max(left.optimzal, right.optimzal));

            if (root.left != null) {
                if (root.val > root.left.val) {
//                    ret.longest_increase_sequence_start_with_node = 1;
                    ret.longest_decrease_sequence_start_with_node = 1 + left.longest_decrease_sequence_start_with_node;
//                    ret.longest_increase_sequence_end_with_node = 1 + left.longest_increase_sequence_end_with_node;
//                    ret.longest_decrese_sequence_end_with_node = 1;
                } else if (root.val < root.left.val) {
                    ret.longest_increase_sequence_start_with_node = 1 + left.longest_increase_sequence_start_with_node;
//                    ret.longest_decrease_sequence_start_with_node = 1;
//                    ret.longest_increase_sequence_end_with_node = 1;
//                    ret.longest_decrese_sequence_end_with_node = 1 + left.longest_decrese_sequence_end_with_node;
                } else {
//                    ret.longest_increase_sequence_start_with_node = 1;
//                    ret.longest_decrease_sequence_start_with_node = 1;
//                    ret.longest_increase_sequence_end_with_node = 1;
//                    ret.longest_decrese_sequence_end_with_node = 1;
                }
            }

            if (root.right != null) {
                if (root.val > root.right.val) {
//                    ret.longest_increase_sequence_start_with_node = 1;
                    ret.longest_decrease_sequence_start_with_node = Math.max(ret.longest_decrease_sequence_start_with_node, 1 + right.longest_decrease_sequence_start_with_node);
//                    ret.longest_increase_sequence_end_with_node = Math.max(ret.longest_increase_sequence_end_with_node, 1 + right.longest_increase_sequence_end_with_node);
//                    ret.longest_decrese_sequence_end_with_node = 1;
                } else if (root.val < root.right.val) {
                    ret.longest_increase_sequence_start_with_node = Math.max(ret.longest_increase_sequence_start_with_node, 1 + right.longest_increase_sequence_start_with_node);
//                    ret.longest_decrease_sequence_start_with_node = 1;
//                    ret.longest_increase_sequence_end_with_node = 1;
//                    ret.longest_decrese_sequence_end_with_node = Math.max(ret.longest_decrese_sequence_end_with_node, 1 + right.longest_decrese_sequence_end_with_node);
                } else {
//                    ret.longest_increase_sequence_start_with_node = 1;
//                    ret.longest_decrease_sequence_start_with_node = 1;
//                    ret.longest_increase_sequence_end_with_node = 1;
//                    ret.longest_decrese_sequence_end_with_node = 1;
                }
            }

            ret.optimzal = Math.max(ret.optimzal, ret.longest_increase_sequence_start_with_node + ret.longest_decrease_sequence_start_with_node - 1);
        }

        return ret;
    }

    int longestConsecutive(TreeNode root) {
        return helper(root).optimzal;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
//        n1.left = n2;
//        n1.right = n3;
        n2.left = n1;
        n2.right = n3;
        System.out.println(s.longestConsecutive(n2));
    }
}
