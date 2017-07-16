package com.l1337.l78;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private void swap(int [] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    private void helper(List<List<Integer>>ret, Integer [] tmp, int startIndex, int [] data, int k) {
        if (k == 0) {
            ret.add(new ArrayList<>(Arrays.asList(tmp)));
            return;
        }

        for (int start = startIndex; start + k< data.length; ++start) {

        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<>());
        for (int i : nums) {
            int length = ret.size();
            for (int j = 0; j < length; ++j) {
                List<Integer> tmp  = new ArrayList<>(ret.get(j));
                tmp.add(i);
                ret.add(tmp);
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
