package com.l1337.l426;


import com.l1337.common.TreeNode;

public class Solution {
    /*
    Convert Binary Search Tree to Sorted Doubly Linked List
    Description
Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

bstdlloriginalbst

We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.

bstdllreturndll

Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

bstdllreturnbst

Example
Example 1:

Input: {4,2,5,1,3}
        4
       /  \
      2   5
     / \
    1   3
Output: "left:1->5->4->3->2  right:1->2->3->4->5"
Explanation:
Left: reverse output
Right: positive sequence output
Example 2:

Input: {2,1,3}
        2
       /  \
      1   3
Output: "left:1->3->2  right:1->2->3"
     */

    //Please note this is doubly linked list.

    //h1 != null && h2 != null;
    private  TreeNode mergeDoublyList(TreeNode h1, TreeNode h2)
    {
        TreeNode t1 = h1.left, t2 = h2.left;

        t1.right = h2;
        h2.left = t1;
        t2.right = h1;
        h1.left = t2;
        return h1;
    }
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null)
            return null;

        TreeNode left = treeToDoublyList(root.left);
        TreeNode right = treeToDoublyList(root.right);

        TreeNode head = root;
        head.left = head;
        head.right = head;

        if (left != null)
        {
            head = mergeDoublyList(left, head);
        }

        if (right != null)
        {
            head = mergeDoublyList(head, right);
        }
        return head;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n4 = new TreeNode(4);
        TreeNode n2 = new TreeNode(2);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);

        n4.left = n2;
        n4.right = n5;
        n2.left = n1;
        n2.right = n3;

        TreeNode root = s.treeToDoublyList(n4);

        System.out.println(root.val);
    }
}
