package com.l1337.l437;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/path-sum-iii/discuss/91878/17-ms-on-java-prefix-sum-method
//    https://leetcode.com/problems/path-sum-iii/discuss/91878/17-ms-O(n)-java-Prefix-sum-method
    private int pathSum(TreeNode root, List<Integer> l, int sum, int depth) {
        if (root == null)
            return 0;
        int ret = 0;
        for (int i = 0; i < depth; ++i) {
            l.set(i, l.get(i) + root.val);
            if (l.get(i) == sum)
                ++ret;
        }

        if (depth >= l.size())
            l.add(0);
        else
            l.set(depth, 0);
        ret += pathSum(root.left, l, sum, 1 + depth) + pathSum(root.right, l, sum, 1 + depth);
        l.remove(l.size() - 1);
        for (int i = 0; i < depth; ++i) {
            l.set(i, l.get(i) - root.val);
        }
        return ret;
    }

    public int pathSum(TreeNode root, int sum) {
        return 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
