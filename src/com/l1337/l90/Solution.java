package com.l1337.l90;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);

        //add init
        ret.add(new ArrayList<>());
        int lastSize = ret.size();

        for (int i = 0; i < nums.length; ++i) {
            int start;
            if (i != 0 && nums[i] == nums[i-1])
            {
                start = lastSize;
            }
            else
            {
                start = 0;
            }
            int end = lastSize = ret.size();
            for (int j = start; j < end; ++j) {
                List<Integer> tmp = new ArrayList<>(ret.get(j));
                tmp.add(nums[i]);
                ret.add(tmp);
            }
        }


        return ret;
    }
    
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
