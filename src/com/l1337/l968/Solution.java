package com.l1337.l968;


import com.l1337.common.TreeNode;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    //min vals that covers the root, min val that not covers the root but covers the remaining subtree
    private int minCameraCoverHelperMemo(TreeNode root, boolean selfCovered, boolean needsCoverParent, Map<Pair<Boolean, Boolean>, Map<TreeNode, Integer>> map)
    {
        if (root == null)
            return 0;
        Pair<Boolean, Boolean> pair = new Pair<>(selfCovered, needsCoverParent);
        Integer min = map.get(pair).get(root);
        if (min != null)
            return min;

        int ret = Integer.MAX_VALUE;

        //no matter which case, we can always choose root to put a camera on ourselves
        int local = 1;
        local += minCameraCoverHelperMemo(root.left, true, false, map);
        local += minCameraCoverHelperMemo(root.right, true, false, map);
        ret = Math.min(ret, local);

        //other cases only OK if your parent is covered by itself.
        if (!needsCoverParent)
        {
            //self may or not may be corvered
            if (selfCovered)
            {
                //no requirement for the child.., and we don't have to put a camera here
                local = 0;
                local += minCameraCoverHelperMemo(root.left, false, false, map);
                local += minCameraCoverHelperMemo(root.right, false, false, map);
                ret = Math.min(ret, local);


                //more cases to go
                local = 0;
                local += minCameraCoverHelperMemo(root.left, false, true, map);
                local += minCameraCoverHelperMemo(root.right, false, true, map);
                ret = Math.min(ret, local);

                local = 0;
                local += minCameraCoverHelperMemo(root.left, false, true, map);
                local += minCameraCoverHelperMemo(root.right, false, false, map);
                ret = Math.min(ret, local);

                local = 0;
                local += minCameraCoverHelperMemo(root.left, false, false, map);
                local += minCameraCoverHelperMemo(root.right, false, true, map);
                ret = Math.min(ret, local);
            }
            else
            {
                //self is not covered, so we need at least one of the child to cover it. This only works if at least one of the child is not null.
                if (root.left != null || root.right != null)
                {
                    //always ok to let both child to cover it.
                    local = 0;
                    local += minCameraCoverHelperMemo(root.left, false, true, map);
                    local += minCameraCoverHelperMemo(root.right, false, true, map);
                    ret = Math.min(ret, local);

                    if (root.left != null)
                    {
                        //let one left child cover it
                        local = 0;
                        local += minCameraCoverHelperMemo(root.left, false, true, map);
                        local += minCameraCoverHelperMemo(root.right, false, false, map);
                        ret = Math.min(ret, local);
                    }

                    if (root.right != null)
                    {
                        //let one right child cover it
                        local = 0;
                        local += minCameraCoverHelperMemo(root.left, false, false, map);
                        local += minCameraCoverHelperMemo(root.right, false, true, map);
                        ret = Math.min(ret, local);
                    }
                }
            }
        }

        map.get(pair).put(root, ret);
        return ret;
    }
//
//    private int minCameraCoverHelper(TreeNode root, boolean selfCovered, boolean needsCoverParent)
//    {
//        if (root == null)
//            return 0;
//
//        int ret = Integer.MAX_VALUE;
//
//        //no matter which case, we can always choose root to put a camera on ourselves
//        int local = 1;
//        local += minCameraCoverHelper(root.left, true, false);
//        local += minCameraCoverHelper(root.right, true, false);
//        ret = Math.min(ret, local);
//
//        //other cases only OK if your parent is covered by itself.
//        if (!needsCoverParent)
//        {
//            //self may or not may be corvered
//            if (selfCovered)
//            {
//                //no requirement for the child.., and we don't have to put a camera here
//                local = 0;
//                local += minCameraCoverHelper(root.left, false, false);
//                local += minCameraCoverHelper(root.right, false, false);
//                ret = Math.min(ret, local);
//
//
//                //more cases to go
//                local = 0;
//                local += minCameraCoverHelper(root.left, false, true);
//                local += minCameraCoverHelper(root.right, false, true);
//                ret = Math.min(ret, local);
//
//                local = 0;
//                local += minCameraCoverHelper(root.left, false, true);
//                local += minCameraCoverHelper(root.right, false, false);
//                ret = Math.min(ret, local);
//
//                local = 0;
//                local += minCameraCoverHelper(root.left, false, false);
//                local += minCameraCoverHelper(root.right, false, true);
//                ret = Math.min(ret, local);
//            }
//            else
//            {
//                //self is not covered, so we need at least one of the child to cover it. This only works if at least one of the child is not null.
//                if (root.left != null || root.right != null)
//                {
//                    //always ok to let both child to cover it.
//                    local = 0;
//                    local += minCameraCoverHelper(root.left, false, true);
//                    local += minCameraCoverHelper(root.right, false, true);
//                    ret = Math.min(ret, local);
//
//                    if (root.left != null)
//                    {
//                        //let one left child cover it
//                        local = 0;
//                        local += minCameraCoverHelper(root.left, false, true);
//                        local += minCameraCoverHelper(root.right, false, false);
//                        ret = Math.min(ret, local);
//                    }
//
//                    if (root.right != null)
//                    {
//                        //let one right child cover it
//                        local = 0;
//                        local += minCameraCoverHelper(root.left, false, false);
//                        local += minCameraCoverHelper(root.right, false, true);
//                        ret = Math.min(ret, local);
//                    }
//                }
//            }
//        }
//
//        return ret;
//    }
    public int minCameraCover(TreeNode root) {
        Map<Pair<Boolean, Boolean>, Map<TreeNode, Integer>> map = new HashMap<>();
        map.put(new Pair<>(false, false), new HashMap<>());
        map.put(new Pair<>(true, false), new HashMap<>());
        map.put(new Pair<>(false, true), new HashMap<>());
        map.put(new Pair<>(true, true), new HashMap<>());
        //init four directions
        return minCameraCoverHelperMemo(root, false, false, map);
    }

//    https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS
//    https://leetcode.com/problems/binary-tree-cameras/discuss/211412/C%2B%2B-DFS-simplest
//    https://leetcode.com/problems/binary-tree-cameras/discuss/211223/C%2B%2B-Naive-DFS-%2B-Memo

//    https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
