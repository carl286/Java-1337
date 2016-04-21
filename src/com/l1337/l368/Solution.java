package com.l1337.l368;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<Integer> largestDivisibleSubset(int[] nums) {

        /*
        //Too Slow Below...
        if (nums.length == 0)
            return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> tmp = new ArrayList<>();
        List<Integer> ret = new ArrayList<>();
        for (int i : nums) {
            //compare from the end
            int minSize = 0;
            for (int j = tmp.size() - 1; j >= 0 && (minSize == 0 || minSize <= 1 + tmp.get(j).size()); --j) {
                if (i % tmp.get(j).get(tmp.get(j).size()-1) == 0) {
                    List<Integer> local = new ArrayList<>(tmp.get(j));
                    local.add(i);
                    minSize = local.size();
                    if (minSize > ret.size())
                        ret = local;
                    tmp.add(local);
                }
            }
            if (minSize == 0) {
                List<Integer> local = new ArrayList<>();
                local.add(i);
                minSize = local.size();
                if (minSize > ret.size())
                    ret = local;
                tmp.add(local);
            }
        }
        return ret;
        */

//        思路：其实和求最大上升子序列LIS差不多，只不过这题要求输出序列而已。

        /*
        //Still too slow, you don't have to store the lists, you just need your prev....
        if (nums.length == 0)
            return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> tmp = new ArrayList<>();
        int globalMaxSize = 0;
        int globalIndex = -1;
        for (int i : nums) {
            int maxSize = 0;
            int index = -1;
            for (int j = 0; j < tmp.size(); ++j) {
                if (i % tmp.get(j).get(tmp.get(j).size() - 1) == 0 && maxSize < tmp.get(j).size()) {
                    maxSize = tmp.get(j).size();
                    index = j;
                }
            }
            if (maxSize == 0) {
                tmp.add(new ArrayList<>());
            } else {
                tmp.add(new ArrayList<>(tmp.get(index)));
            }
            tmp.get(tmp.size()-1).add(i);
            if ( tmp.get(tmp.size()-1).size() > globalMaxSize) {
                globalMaxSize = tmp.get(tmp.size()-1).size();
                globalIndex = tmp.size()-1;
            }
        }
        return tmp.get(globalIndex);
        */
        if (nums.length == 0)
            return new ArrayList<>();
        Arrays.sort(nums);
        List<int []> cache = new ArrayList<>();
        int globalMaxSize = 1;
        int globalIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            int [] tmp = new int [2];
            tmp[0] = -1;//pre
            tmp[1] = 1;//size
            cache.add(tmp);
            int localMax = 0;
            for (int j = 0; j < i; ++j) {
                if (nums[i] % nums[j] == 0 && localMax < cache.get(j)[1]) {
                    localMax = cache.get(j)[1];
                    tmp[0] = j;
                }
            }
            tmp[1] += localMax;
            if (tmp[1] > globalMaxSize) {
                globalIndex = i;
                globalMaxSize = tmp[1];
            }
        }

        List<Integer> ret = new ArrayList<>();
        while (globalIndex != -1) {
            ret.add(nums[globalIndex]);
            globalIndex = cache.get(globalIndex)[0];
        }
        return ret;
    }


//    https://www.hrwhisper.me/leetcode-largest-divisible-subset/
//    http://www.cnblogs.com/grandyang/p/5625209.html


    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        int [] nums = {1,2,4,8,9,72};
        System.out.println(s.largestDivisibleSubset(nums));
    }
}
