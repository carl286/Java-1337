package com.l1337.l655;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.l1337.common.TreeNode;

public class Solution {

    private int getLevel(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(getLevel(root.right), getLevel(root.left));
    }


    private void fillTree(List<List<String>> ret, TreeNode root, int level, int left, int right) {
        if (root == null)
            return;

        List<String> list = ret.get(level);
        int mid = ((right - left) >> 1) + left;
        list.set(mid, Integer.toString(root.val));
        fillTree(ret, root.left, level + 1, left, mid);
        fillTree(ret, root.right, level + 1, mid + 1, right);
    }

    public List<List<String>> printTree(TreeNode root) {
        //root != null
        int m = getLevel(root);
        int n = 1, l = 1;
        while (l < m) {
            n = 2 * n + 1;
            ++l;
        }

        List<List<String>> ret = new ArrayList<>();
        while (ret.size() < m) {
            String tmp [] = new String[n];
            Arrays.fill(tmp, "");
            ret.add(new ArrayList<String>(Arrays.asList(tmp)));
        }

        fillTree(ret, root, 0, 0, n);

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.left = n2;

        List<List<String>> l = s.printTree(n1);
        for (int i = 0; i < l.size(); ++i) {
            for (int j = 0; j < l.get(i).size(); ++j) {
                System.out.print(l.get(i).get(j) + "\t");
            }
            System.out.println();
        }
    }
}
