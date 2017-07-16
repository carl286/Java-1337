package com.l1337.l863;

import com.l1337.common.TreeNode;

import java.util.*;

public class Solution {


    private void buildGraph(TreeNode cur, TreeNode pre, Map<TreeNode, ArrayList<TreeNode>> map)
    {
        //assert cur != null
        map.putIfAbsent(cur, new ArrayList<>());
        if (pre != null)
            map.get(cur).add(pre);
        if (cur.left != null)
        {
            map.get(cur).add(cur.left);
            buildGraph(cur.left, cur, map);
        }

        if (cur.right != null)
        {
            map.get(cur).add(cur.right);
            buildGraph(cur.right, cur, map);
        }
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ret = new ArrayList<>();
        if (root == null)
            return ret;

        Map<TreeNode, ArrayList<TreeNode>> map = new HashMap<>();

        buildGraph(root, null, map);

        ArrayList<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);

        //K >= 0
        while (K-- > 0)
        {
            ArrayList<TreeNode> nextLevel = new ArrayList<>();
            for(int i = 0; i < curLevel.size(); ++i)
            {
                TreeNode cur = curLevel.get(i);
                ArrayList<TreeNode> neighbors = map.getOrDefault(cur, new ArrayList<>());
                for(int j = 0; j < neighbors.size(); ++j)
                {
                    if (!visited.contains(neighbors.get(j)))
                    {
                        visited.add(neighbors.get(j));
                        nextLevel.add(neighbors.get(j));
                    }
                }

            }

            curLevel = nextLevel;
        }

        for(int i = 0; i < curLevel.size(); ++i)
            ret.add(curLevel.get(i).val);
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();

        System.out.println("Hello World");
    }
}
