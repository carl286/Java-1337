package com.l1337.l545;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.l1337.common.TreeNode;

public class Solution {

    /*
    Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1

Input:

  1
   \
    2
   / \
  3   4
Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].

Example 2

Input:

    ____1_____
   /          \
  2            3
 / \          /
4   5        6
   / \      / \
  7   8    9  10

Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
     */

    List<Integer> leftBound(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        //assume root is not null
        while (root != null) {
            ret.add(root.val);
            root = root.left;
        }

        return ret;
    }

    List<Integer> rightBound(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        //assume root is not null
        while (root != null) {
            ret.add(root.val);

            root = root.right;
        }

        return ret;
    }

    void dfs(TreeNode root, List<Integer> ret) {
        if (root == null)
            return;

        dfs(root.left, ret);
        if (root.left == null && root.right == null)
            ret.add(root.val);
        dfs(root.right, ret);
    }

//    http://www.cnblogs.com/grandyang/p/6833459.html
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null)
            return null;

        Set<Integer> visited = new HashSet<>();
        List<Integer> ret = new ArrayList<>();

        List<Integer> left =  leftBound(root);

        List<Integer> mid = new ArrayList<>();
        dfs(root, mid);

        List<Integer> right =  rightBound(root);

        //merge
        for (int i = 0; i < left.size(); ++i) {
            ret.add(left.get(i));
            visited.add(left.get(i));
        }

        for (int i = 0; i < mid.size(); ++i) {
            if (!visited.contains(mid.get(i))) {
                ret.add(mid.get(i));
                visited.add(mid.get(i));
            }
        }

        for (int i = right.size() - 1; i >= 0; --i) {
            if (!visited.contains(right.get(i))) {
                ret.add(right.get(i));
            }
        }

        return ret;
    }

    //the problem description is bull shit.
//    https://www.cnblogs.com/dongling/p/6622312.html
    public static void main(String [] args) {
        Solution s = new Solution();
//        TreeNode n1 = new TreeNode(1);
//        TreeNode n2 = new TreeNode(2);
//        TreeNode n3 = new TreeNode(3);
//        TreeNode n4 = new TreeNode(4);
//        n1.right = n2;
//        n2.left = n3;
//        n2.right = n4;

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(9);


        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;
        n5.right = n8;
        n3.left = n6;
        n6.left = n9;
        n6.right = n10;

        List<Integer> ret = s.boundaryOfBinaryTree(n1);

        for (int i : ret)
            System.out.print(i + "\t");
    }
}
