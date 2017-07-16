package com.l1337.l987;

import com.l1337.common.TreeNode;

import java.util.*;

public class Solution {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null)
            return ret;

        ArrayList<TreeNode> queue = new ArrayList<>();
        ArrayList<Integer> position = new ArrayList<>();
        queue.add(root);
        position.add(0);

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        map.putIfAbsent(0, new ArrayList<>());
        map.get(0).add(root.val);

        while (!queue.isEmpty())
        {
            ArrayList<TreeNode> nextQueue = new ArrayList<>();
            ArrayList<Integer> nextPosition = new ArrayList<>();
            //TreeMap<Integer, ArrayList<Integer>> local = new TreeMap<>();
            Map<Integer, ArrayList<Integer>> local = new HashMap<>();

            for(int i = 0; i < queue.size(); ++i)
            {
                TreeNode cur = queue.get(i);
                int curPos = position.get(i);
                if (cur.left != null)
                {
                    nextQueue.add(cur.left);
                    nextPosition.add(curPos-1);
                    local.putIfAbsent(curPos-1, new ArrayList<>());
                    local.get(curPos-1).add(cur.left.val);
                }

                if (cur.right != null)
                {
                    nextQueue.add(cur.right);
                    nextPosition.add(curPos+1);
                    local.putIfAbsent(curPos+1, new ArrayList<>());
                    local.get(curPos+1).add(cur.right.val);
                }
            }

            for(Map.Entry<Integer, ArrayList<Integer>> entry: local.entrySet())
            {
                Collections.sort(entry.getValue());
                map.putIfAbsent(entry.getKey(), new ArrayList<>());
                map.get(entry.getKey()).addAll(entry.getValue());
            }

            queue = nextQueue;
            position = nextPosition;
        }

        for (Map.Entry<Integer, ArrayList<Integer>> entry: map.entrySet())
        {
            ret.add(entry.getValue());
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
