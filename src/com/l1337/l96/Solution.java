package com.l1337.l96;


import com.l1337.common.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

//    https://zhuanlan.zhihu.com/p/31457805
    public int numTrees(int n) {
        if (n <= 1)
            return 1;

        int [] tmp = new int [n+1];
        tmp[0] = 1;
        for (int k  = 1; k <= n; ++k)
        {
            for (int i = 0; i < k; ++i)
                tmp[k] += tmp[i] * tmp[k-1-i];
        }

        return tmp[n];
    }

    List<TreeNode> helper(Map<Pair<Integer, Integer>, List<TreeNode>> map, int left, int right)
    {
        Pair<Integer, Integer> inputKey = new Pair<>(left, right);

        if (map.containsKey(inputKey))return map.get(inputKey);

        List<TreeNode> ret = new ArrayList<>();
        if (left > right) {
            ret.add(null);
            return ret;
        }

        for (int k = left; k <= right; ++k)
        {
            List<TreeNode> leftList = helper(map, left, k - 1);
            List<TreeNode> rightList = helper(map, k + 1, right);

            for (int i = 0; i < leftList.size(); ++i)
                for (int j = 0; j < rightList.size(); ++j)
                {
                    TreeNode root = new TreeNode(k);
                    root.left = leftList.get(i);
                    root.right = rightList.get(j);
                    ret.add(root);
                }
        }

        map.put(inputKey, ret);

        return ret;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n <= 0)
            return new ArrayList<>();
        Map<Pair<Integer, Integer>, List<TreeNode>> map = new HashMap();
        helper(map, 1,  n);

        return map.get(new Pair<Integer, Integer>(1,  n));
    }

//    http://web.archive.org/web/20150308080843/http://noalgo.info/832.html
    public static void main(String [] args) {
        
        Solution s = new Solution();
        int n = 2;

        System.out.println(s.generateTrees(n));
    }
}
