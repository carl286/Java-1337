package com.l1337.l1305;


import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> inOrderVisit(TreeNode root)
    {
        List<Integer> ret = new ArrayList<>();
        while (root != null)
        {
            if (root.left == null)
            {
                ret.add(root.val);
                root = root.right;
            }
            else
            {
                TreeNode pre;
                for(pre = root.left; pre.right != null && pre.right != root; pre = pre.right);
                if (pre.right == null)
                {
                    pre.right = root;
                    root = root.left;
                }
                else
                {
                    pre.right = null;
                    ret.add(root.val);
                    root = root.right;
                }
            }
        }

        return ret;
    }

    public List<Integer> mergeOrderedList(List<Integer> l1, List<Integer> l2)
    {
        List<Integer> ret = new ArrayList<>();
        int i = 0, j = 0;
        for(; i < l1.size() && j < l2.size();)
        {
            if (l1.get(i) < l2.get(j))
            {
                ret.add(l1.get(i));
                ++i;
            } else if (l1.get(i) > l2.get(j))
            {
                ret.add(l2.get(j));
                ++j;
            }
            else
            {
                ret.add(l1.get(i));
                ret.add(l2.get(j));
                ++i;
                ++j;
            }
        }
        if (i < l1.size())
        ret.addAll(l1.subList(i, l1.size()));
        if (j < l2.size())
        ret.addAll(l2.subList(j, l2.size()));
        return ret;
    }
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = inOrderVisit(root1);
        List<Integer> l2 = inOrderVisit(root2);

        return mergeOrderedList(l1, l2);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n12 = new TreeNode(2);
        TreeNode n11 = new TreeNode(1);
        TreeNode n14 = new TreeNode(4);
        n12.left = n11;
        n12.right = n14;

        TreeNode n21 = new TreeNode(1);
        TreeNode n20 = new TreeNode(0);
        TreeNode n23 = new TreeNode(3);
        n21.left = n20;
        n21.right = n23;
        s.getAllElements(n11, n21);
        System.out.println("Hello World");
    }
}
