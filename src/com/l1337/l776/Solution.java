package com.l1337.l776;


import com.l1337.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees
where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value.
It's not necessarily the case that the tree contains a node with valueV.
Additionally, most of the structure of the original tree should remain.
Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.
You should output the root TreeNode of both subtrees after splitting, in any order.
Example 1:
Input:
 root = [4,2,6,1,3,5,7], V = 2

Output:
 [[2,1],[4,3,6,null,null,5,7]]

Explanation:

Note that root, output[0], and output[1] are TreeNode objects, not arrays.

The given tree [4,2,6,1,3,5,7] is represented by the following diagram:

          4
        /   \
      2      6
     / \    / \
    1   3  5   7

while the diagrams for the outputs are:

          4
        /   \
      3      6      and    2
            / \           /
           5   7         1
Note:
The size of the BST will not exceed50
.
The BST is always valid and each node's value is different.
 */
public class Solution {

    public void splitBST(TreeNode minRoot, TreeNode maxRoot, TreeNode root, int V) {


        if (root == null)
            return;

        if (root.val <= V)
        {
            if (root.val < minRoot.val)
            {
                minRoot.left = root;
//                minRoot.right = null;
            }
            else
            {
                minRoot.right = root;
//                minRoot.left = null;
            }
            splitBST(root, maxRoot, root.right, V);
            if (maxRoot.right == root.right)
            {
                root.right = null;
            }
        }
        else
        {
            if (root.val < maxRoot.val)
            {
                maxRoot.left = root;
//                maxRoot.right = null;
            }
            else
            {
                maxRoot.right = root;
//                maxRoot.left = null;
            }
            splitBST(minRoot, root, root.left, V);
            if (minRoot.right == root.left)
            {
                root.left = null;
            }
        }
    }
    public TreeNode[] splitBST(TreeNode root, int V) {
//        if (root == null)
//            return null;
//        //what if there is only 1 node??, this case should be counted towards to the general cases...
//
//        TreeNode dummyMin = new TreeNode(Integer.MAX_VALUE);
//        TreeNode dummyMax = new TreeNode(Integer.MIN_VALUE);
//        splitBST(dummyMin, dummyMax, root, V);
//        return new TreeNode[] {dummyMin.left, dummyMax.right};

        TreeNode[] res = new TreeNode[2];
        if (root == null) return res;

        if (root.val > V) {
            res[1] = root;
            TreeNode[] leftSide = splitBST(root.left, V);
            root.left = leftSide[1];
            res[0] = leftSide[0];
        }
        else { // root.val <= V
            res[0] = root;
            TreeNode[] rightSide = splitBST(root.right, V);
            root.right = rightSide[0];
            res[1] = rightSide[1];
        }
        return res;
    }

    public void printTree(TreeNode root)
    {
        if (root == null) {
            System.out.println("Empty Tree");
            return;
        }

        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.addLast(root);
        while (!dq.isEmpty())
        {
            int size = dq.size();
            for(int i = 0; i < size; ++i)
            {
                TreeNode current = dq.pollFirst();
                {
                    System.out.print(current.val + "\t");

                    if(current.left != null) dq.addLast(current.left);
                    if(current.right != null) dq.addLast(current.right);
                }

            }
            System.out.println();
        }
        System.out.println("*******");
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        TreeNode n1 = new TreeNode(1);
//        TreeNode n4 = new TreeNode(4);
//        TreeNode n5 = new TreeNode(5);
//        TreeNode n6 = new TreeNode(6);
//        int V = -60;
//        n4.left = n1; n4.right = n6;
//        n6.left = n5;
//
//        s.printTree(n4);
//        TreeNode [] ret = s.splitBST(n4, V);
//        s.printTree(ret[0]);
//        s.printTree(ret[1]);

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n4.left = n2;
        n4.right = n6;
        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;
        int V = 2;
        TreeNode [] ret = s.splitBST(n4, V);
        s.printTree(ret[0]);
        s.printTree(ret[1]);
    }
}
