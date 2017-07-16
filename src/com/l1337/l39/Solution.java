package com.l1337.l39;


import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int length, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if (length <= 0)
            return ret;

        List<List<Integer>> sub1 = null, sub2 = null;
        if (length > 0) {
            //don't take it.
           sub1 = combinationSum(candidates, length - 1, target);
           ret.addAll(sub1);

           if (target == candidates[length - 1]) {
               ret.add(new ArrayList<>(Arrays.asList(target)));
           } else if (target > candidates[length-1]) {
               sub2 = combinationSum(candidates, length, target - candidates[length-1]);
               for (List<Integer> l: sub2) {
                   l.add(candidates[length-1]);
                   ret.add(l);
               }
           }
        }

        return ret;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSum(candidates, candidates.length, target);
    }

    public List<List<Integer>> combinationSum_March16_21_helper_memo(int[] candidates, int startIndex, int target, Map<Integer, List<List<Integer>>> map) {
        if (map.containsKey(target))
            return map.get(target);

        List<List<Integer>> ret = new ArrayList<>();
        for (int i = startIndex; i >= 0; --i)
        {
            if (candidates[i] > target)
            {
                continue;
            }
            else
            {
                //candidate[i] <= target
                // tmp.add(candidates[i]);
                if (target == candidates[i])
                {
                    List<Integer> tmp2 = new ArrayList<>();
                    tmp2.add(candidates[i]);
                    ret.add(tmp2);
                }
                else
                {
                    List<List<Integer>> subs = combinationSum_March16_21_helper_memo(candidates, i, target - candidates[i], map);
                    for(int k = 0; k < subs.size(); ++k)
                    {
                        List<Integer> tmp2 = new ArrayList<>(subs.get(k));
                        tmp2.add(candidates[i]);
                        ret.add(tmp2);
                    }
                }

                // tmp.remove(tmp.size()-1);
            }

        }
        map.put(target, ret);
        return ret;
    }

    public void combinationSum_March16_21_helper(int[] candidates, int startIndex, int target, List<Integer> tmp, List<List<Integer>> ret) {
        if (target == 0)
        {
            List<Integer> tmp2 = new ArrayList<>(tmp);
            ret.add(tmp2);
            return;
        }

        for (int i = startIndex; i >= 0; --i)
        {
            if (candidates[i] > target)
            {
                continue;
            }
            else
            {
                //candidate[i] <= target
                tmp.add(candidates[i]);
                combinationSum_March16_21_helper(candidates, i, target - candidates[i], tmp, ret);
                tmp.remove(tmp.size()-1);
            }

        }
//        map.put(target, ret);
    }

    public List<List<Integer>> combinationSum_March16_21(int[] candidates, int target) {
        Arrays.sort(candidates);
        Map<Integer, List<List<Integer>>> map = new HashMap<>();

//        List<List<Integer>> ret = new ArrayList<>();
//        combinationSum_March16_21_helper(candidates, candidates.length - 1, target, new ArrayList<>(), ret);
//        return ret;

        /*
        think about why the memo way would break here...
        3	5
2	3	3
3	2	3
2	2	2	2
         */
        combinationSum_March16_21_helper_memo(candidates, candidates.length - 1, target, map);


        return map.getOrDefault(target, new ArrayList<>());

    }

//    https://leetcode.com/problems/combination-sum/discuss/16509/Iterative-Java-DP-solution
    public static void main(String [] args) {
        Solution s = new Solution();
        int nums [] = {2,3,5};
        int target = 8;
        // int target = 5;
//        for (List<Integer> l : s.combinationSum(nums, target)) {
        for (List<Integer> l : s.combinationSum_March16_21(nums, target)) {
            for (Integer i : l) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
