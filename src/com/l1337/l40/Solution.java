package com.l1337.l40;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private void combinationSum2Helper(int[] candidates, int index, int target, List<Integer> tmp, List<List<Integer>> ret)
    {
        if (target == 0)
        {
            ret.add(new ArrayList<>(tmp));
            return;
        }

        while (index >= 0)
        {
            if (candidates[index] <= target)
            {
                tmp.add(candidates[index]);
                combinationSum2Helper(candidates, index - 1, target - candidates[index], tmp, ret);
                tmp.remove(tmp.size()-1);
            }

            --index;
            while (index >= 0 && candidates[index] == candidates[index+1])
                --index;
        }

    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        combinationSum2Helper(candidates, candidates.length-1, target, new ArrayList<>(), ret);
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
