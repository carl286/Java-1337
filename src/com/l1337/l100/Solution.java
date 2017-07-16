package com.l1337.l100;


import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        List<TreeNode> cur = new ArrayList<>();
        if (root != null)
            cur.add(root);
        while (cur.size() > 0)
        {
            List<TreeNode> next = new ArrayList<>();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < cur.size(); ++i)
            {
                tmp.add(cur.get(i).val);
                if (cur.get(i).left != null)
                    next.add(cur.get(i).left);
                if (cur.get(i).right != null)
                    next.add(cur.get(i).right);
            }
            ret.add(tmp);
            cur = next;
        }

        return ret;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == sum;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    void helper(List<List<Integer>> ret, List<Integer> tmp, TreeNode root, int sum)
    {
        tmp.add(root.val);
        int delta = sum - root.val;
        if (root.left != null)
        {
            helper(ret, tmp, root.left, delta);
        }
        if (root.right != null)
        {
            helper(ret, tmp, root.right, delta);
        }
        if (root.left == null && root.right == null && delta == 0)
        {
            ret.add(new ArrayList<>(tmp));
        }

        tmp.remove(tmp.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> tmp = new LinkedList<>();
        if (root != null)
        {
            helper(ret, tmp, root, sum);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(8);
        n1.left = n2;
        n1.right = n3;
        TreeNode n4 = new TreeNode(11);
        n2.left = n4;
        TreeNode n5 = new TreeNode(13);
        TreeNode n6 = new TreeNode(4);
        n3.left = n5;
        n3.right = n6;
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(2);
        n4.left = n7;
        n4.right = n8;
        TreeNode n9 = new TreeNode(5);
        TreeNode n10 = new TreeNode(1);
        n6.left = n9;
        n6.right = n10;

        int sum = 22;

        List<List<Integer>> ret = s.pathSum(n1, sum);
        for (int i = 0; i < ret.size(); ++i)
        {
            for (int j = 0; j < ret.get(i).size(); ++j)
                System.out.print(ret.get(i).get(j) + "\t");
            System.out.println("*****");
        }

    }
}
