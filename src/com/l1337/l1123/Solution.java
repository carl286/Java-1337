package com.l1337.l1123;


import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

//    https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/334577/JavaC%2B%2BPython-Two-Recursive-Solution
//    https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/334716/No-new-code!-Reusing-codes-from-level-Order-Traversal-and-lowest-common-ancestor-in-binary-tree!
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null)
            return null;
        Map<TreeNode, TreeNode> childToParent = new HashMap<>();

        List<TreeNode> pre = new ArrayList<>();
        List<TreeNode> cur = new ArrayList<>();
        childToParent.put(root, null);
        cur.add(root);

        while (!cur.isEmpty())
        {
            List<TreeNode> next = new ArrayList<>();
            for (int i = 0; i < cur.size(); ++i)
            {
                if (cur.get(i).left != null)
                {
                    childToParent.put(cur.get(i).left, cur.get(i));
                    next.add(cur.get(i).left);
                }

                if (cur.get(i).right != null)
                {
                    childToParent.put(cur.get(i).right, cur.get(i));
                    next.add(cur.get(i).right);
                }
            }

            pre = cur;
            cur = next;
        }

        TreeNode first = pre.get(0), last = pre.get(pre.size()-1);
        while (first != last)
        {
            first = childToParent.get(first);
            last = childToParent.get(last);
        }
        //always check pre....
        return first;
    }

//    https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/334627/Recursive-Solution-with-Explanation









    class Node
    {
        int depth;
        TreeNode node;
        Node(TreeNode n, int dep)
        {
            depth = dep;
            node = n;
        }
    }
    public Node lcaDeepestLeavesMarch10_21_helper(TreeNode root, int depth) {
        if (root.left == null && root.right == null)
            return new Node(root, depth);
        if (root.left == null)
            return lcaDeepestLeavesMarch10_21_helper(root.right, 1 +depth);
        if (root.right == null)
            return lcaDeepestLeavesMarch10_21_helper(root.left, 1+depth);

        Node left = lcaDeepestLeavesMarch10_21_helper(root.left, 1+depth);
        Node right = lcaDeepestLeavesMarch10_21_helper(root.right, 1 +depth);

        if (left.depth != right.depth)
        {
            return left.depth < right.depth ? right : left;
        }

        return new Node(root, left.depth);
    }

    public TreeNode lcaDeepestLeavesMarch10_21(TreeNode root) {
        if (root == null) return null;
        return lcaDeepestLeavesMarch10_21_helper(root, 0).node;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
