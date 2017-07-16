package com.l1337.l872;


import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TransferQueue;

public class Solution {
    private void getLeafList(List<Integer> ret, TreeNode root)
    {
        if (root.left == null && root.right == null)
        {
            ret.add(root.val);
        }

        if (root.left != null)
            getLeafList(ret, root.left);
        if (root.right != null)
            getLeafList(ret, root.right);
    }
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        getLeafList(list1, root1);
        getLeafList(list2, root2);
        if (list1.size() != list2.size())
            return false;
        for(int i = 0; i < list1.size(); ++i)
            if (list1.get(i) != list2.get(i))
                return false;
        return true;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
