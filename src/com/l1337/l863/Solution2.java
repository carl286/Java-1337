package com.l1337.l863;

import com.l1337.common.TreeNode;
import javafx.util.Pair;

import java.util.*;

public class Solution2 {
//    https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/1ms-beat-100-simple-Java-dfs-with(without)-hashmap-including-explanation
    //Below solution is crazily wrong....
    //return value, met target or not...
    public boolean distanceK(TreeNode root, TreeNode target, int K, int depth, boolean metTarget, int [] targetDepth, Map<Pair<Boolean, Boolean>, Map<Integer, List<Integer>>> map) {
        if (root == null)
            return false;

        if (root.equals(target))
        {
            targetDepth[0] = depth;
            distanceK(root.left, target, K, 1 + depth, true, targetDepth, map);
            distanceK(root.right, target, K, 1 + depth, true, targetDepth, map);

            map.get(new Pair<Boolean, Boolean>(true, true)).put(depth, new ArrayList<>());
            map.get(new Pair<Boolean, Boolean>(true, true)).get(depth).add(root.val);
            return true;
        }
        else
        {
            boolean left = distanceK(root.left, target, K, 1 + depth, metTarget, targetDepth, map);
            boolean right = distanceK(root.right, target, K, 1 + depth, metTarget, targetDepth, map);

            if (left || right || metTarget)
            {
                //target is in its subtree
                if (metTarget)
                {
                    //saw it already, it's in the subtree
                    map.get(new Pair<>(true, true)).putIfAbsent(depth, new ArrayList<>());
                    map.get(new Pair<>(true, true)).get(depth).add(root.val);
                }
                else
                {
                    map.get(new Pair<>(true,false)).putIfAbsent(depth, new ArrayList<>());
                    map.get(new Pair<>(true,false)).get(depth).add(root.val);
                }
                return true;
            }
            else
            {
                map.get(new Pair<>(false,false)).putIfAbsent(depth, new ArrayList<>());
                map.get(new Pair<>(false,false)).get(depth).add(root.val);
                return false;
            }
        }
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ret = new ArrayList<>();

        Map<Pair<Boolean, Boolean>, Map<Integer, List<Integer>>> map = new HashMap<>();
        //depth counted from the root
        //first flag, on the same side of target under the root or not
        //second flag, met the target so far or not....
        map.put(new Pair<>(true, true), new HashMap<>());
        map.put(new Pair<>(true, false), new HashMap<>());
        map.put(new Pair<>(false, false), new HashMap<>());

        int [] targetDepth = new int [1];

        distanceK(root, target, K, 0, false, targetDepth, map);

        ret.addAll(map.get(new Pair<>(true, true)).getOrDefault(targetDepth[0] + K, new ArrayList<>()));
        ret.addAll(map.get(new Pair<>(true, false)).getOrDefault(targetDepth[0] - K,  new ArrayList<>()));
        ret.addAll(map.get(new Pair<>(false, false)).getOrDefault(K - targetDepth[0],  new ArrayList<>()));

        return ret;
    }

    private void buildGraph(Map<TreeNode, List<TreeNode>> map, TreeNode root, TreeNode parent)
    {
        map.put(root, new ArrayList<>());
        if (parent != null)
            map.get(root).add(parent);
        if (root.left != null)
        {
            map.get(root).add(root.left);
            buildGraph(map, root.left, root);
        }
        if (root.right != null)
        {
            map.get(root).add(root.right);
            buildGraph(map, root.right, root);
        }
    }

//    https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/solution/
//    https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/1ms-beat-100-simple-Java-dfs-with(without)-hashmap-including-explanation
    //child information is recorded by the tree itself, that's why you just need the parent...
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        buildGraph(map, root, null);

        Set<TreeNode> visited = new HashSet<>();
        Deque<TreeNode> dq = new ArrayDeque<>();
        visited.add(target);
        dq.addLast(target);

        while (K-- > 0)
        {
            int size = dq.size();
            for(int i = 0; i < size; ++i)
            {
                TreeNode current = dq.pollFirst();
                List<TreeNode> list = map.getOrDefault(current, new ArrayList<>());
                for(int j = 0; j < list.size(); ++j)
                {
                    if (!visited.contains(list.get(j)))
                    {
                        visited.add(list.get(j));
                        dq.addLast(list.get(j));
                    }
                }
            }
        }

        List<Integer> ret = new ArrayList<>();
        while (!dq.isEmpty())
        {
            ret.add(dq.pollFirst().val);
        }
        return ret;
    }
    public static void main(String [] args) {
        Solution2 s = new Solution2();

        System.out.println("Hello World");
    }
}
