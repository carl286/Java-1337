package com.l1337.l199;


import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

//    https://leetcode.com/problems/binary-tree-right-side-view/discuss/56064/5-9-Lines-Python-48%2B-ms
//    https://leetcode.com/problems/binary-tree-right-side-view/discuss/56012/My-simple-accepted-solution(JAVA)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        List<TreeNode> level = new ArrayList<>();
        if (root != null)
            level.add(root);

        while (level.size() > 0)
        {
            List<TreeNode> nextLevel = new ArrayList<>();
            ret.add(level.get(level.size() - 1).val);
            for (int i = 0; i < level.size(); ++i)
            {
                if (level.get(i).left != null)
                    nextLevel.add(level.get(i).left);
                if (level.get(i).right != null)
                    nextLevel.add(level.get(i).right);
            }
            level = nextLevel;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
