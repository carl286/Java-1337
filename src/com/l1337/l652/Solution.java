package com.l1337.l652;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.l1337.common.TreeNode;

public class Solution {

//    https://leetcode.com/problems/find-duplicate-subtrees/discuss/106011/Java-Concise-Postorder-Traversal-Solution
//    https://leetcode.com/problems/find-duplicate-subtrees/discuss/106021/Verbose-Java-solution-tree-traversal
//    https://leetcode.com/problems/find-duplicate-subtrees/discuss/106016/O(n)-time-and-space-lots-of-analysis
//    https://leetcode.com/problems/find-duplicate-subtrees/discuss/106013/Preorder-traversal-using-string-and-hashmap
//    https://leetcode.com/problems/find-duplicate-subtrees/discuss/160015/Python-+-DFS-tm
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        List<TreeNode> ret = new ArrayList<>();
        Map<String, TreeNode> map = new HashMap<>();
        getString(root, map, ret);
        return ret;
    }

    private String getString(TreeNode root, Map<String, TreeNode> map, List<TreeNode> ret) {
        if (root == null)
            return "#";

        String retS = root.val + "," + getString(root.left, map, ret) + "," + getString(root.right, map, ret);
        if (!map.containsKey(retS)) {
            map.put(retS, null);
        } else if (map.get(retS) == null) {
            ret.add(root);
            map.put(retS, root);
        }

        return retS;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
