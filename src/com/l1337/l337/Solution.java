package com.l1337.l337;


import com.l1337.common.TreeNode;
import javafx.util.Pair;

public class Solution {

    // https://leetcode.com/submissions/detail/460999425/
    private Pair<Integer, Integer> rob(TreeNode root, boolean isParentBobbed)
    {
        // below got TLE, not efficient...
        if (root == null)
            return new Pair<>(0,0);
        int localMax = 0, globalMax = 0;

        Pair<Integer, Integer> left, right;
        if (!isParentBobbed)
        {
            //got a choice to pick up local root;
            left = rob(root.left, true);
            right = rob(root.right, true);

            //update local
            localMax = root.val + left.getKey() + right.getKey();

            //merge results
            globalMax = Math.max(localMax, Math.max(left.getValue(), right.getValue()));
        }

        left = rob(root.left, false);
        right = rob(root.right, false);

        //update local
        localMax = Math.max(localMax, left.getKey() + right.getKey());
        globalMax = Math.max(globalMax, Math.max(left.getValue(), right.getValue()));
        globalMax = Math.max(globalMax, localMax);

        return new Pair<>(localMax, globalMax);
    }
    public int rob(TreeNode root) {

        return rob(root, false).getValue();
    }

//    https://walkccc.me/LeetCode/problems/1570/
    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        TreeNode n4 = new TreeNode(3);
        n2.right = n4;
        TreeNode n5 = new TreeNode(1);
        n3.left = n5;
        System.out.println(s.rob(n1));
    }
}
