package com.l1337.l491;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

//    https://leetcode.com/problems/increasing-subsequences/description/
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();

        Set<String> set =new HashSet<>();
        //let's do a permutation

        ret.add(new ArrayList<>());
        int index = 0;
        while (index < nums.length) {
            //try to add nums[index]
            int oldSize = ret.size();
            for (int i = 0; i < oldSize; ++i) {
                List<Integer> pre = ret.get(i);
                if (pre.size() == 0 || pre.get(pre.size() - 1) <= nums[index]) {
                    List<Integer> newList = new ArrayList<>(pre);
                    newList.add(nums[index]);
                    StringBuilder key = new StringBuilder("");
                    for (int j : newList)
                        key.append(j).append("#");
                    String keyString = key.toString();
                    if (!set.contains(keyString)) {
                        ret.add(newList);
                        set.add(keyString);
                    }
                }
            }

            ++index;
        }


        //filtere out results
        //if it's too bad, we can create a new one
//        int i = 0;
//        while (i < ret.size()) {
//            if (ret.get(i).size() <= 1) {
//                ret.remove(i);
//            } else {
//                ++i;
//            }
//        }
//        return ret;

        List<List<Integer>> ret2 = new ArrayList<>();
        for (List<Integer> l : ret) {
            if (l.size() >= 2) {
                ret2.add(l);
            }
        }
        return ret2;
    }

    public List<List<Integer>> findSubsequences2(int[] nums) {

        //Below code is wrong...
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        //remove set usages
        //let's do a permutation

        ret.add(new ArrayList<>());
        int index = 0;
        int new_added_length = 0;
        while (index < nums.length) {
            //try to add nums[index]
            int oldSize = ret.size();
            int cur_added_length = 0;

            int cur_start  = 0;
            if (index > 0 && nums[index] == nums[index - 1]) {
                cur_start = oldSize - new_added_length;
            }
            for (int i = cur_start; i < oldSize; ++i) {
                List<Integer> pre = ret.get(i);
                if (pre.size() == 0 || pre.get(pre.size() - 1) <= nums[index]) {
                    List<Integer> newList = new ArrayList<>(pre);
                    newList.add(nums[index]);
                    ret.add(newList);
                    ++cur_added_length;
                }
            }

            new_added_length = cur_added_length;
            ++index;
        }


        List<List<Integer>> ret2 = new ArrayList<>();
        for (List<Integer> l : ret) {
            if (l.size() >= 2) {
                ret2.add(l);
            }
        }
        return ret2;
    }

//    https://leetcode.com/problems/increasing-subsequences/discuss/97127/Simple-Python
    //hints from this solution, you can have a result as a set first, then worry about deduplication.
    //do you have similar syntax in java to operate on list direclty??
//    https://leetcode.com/problems/increasing-subsequences/discuss/97130/Java-20-lines-backtracking-solution-using-set-beats-100.
//    https://leetcode.com/problems/increasing-subsequences/discuss/157356/Java-Iterative-Solution

    public List<List<Integer>> findSubsequences3(int[] nums) {
        //below set is not working
        Set<List<Integer>> ret = new HashSet<>();

        ret.add(new ArrayList<>());
        int index = 0;
        while (index < nums.length) {
            //try to add nums[index]

            Set<List<Integer>> newly_added_set = new HashSet<>();

            for (List<Integer> l : ret) {
                if (l.size() <= 0 || l.get(l.size() - 1) <= nums[index]) {
                    List<Integer> newList = new ArrayList<>(l);
                    newList.add(nums[index]);
                    newly_added_set.add(newList);
                }
            }

            ret.addAll(newly_added_set);

            ++index;
        }

        List<List<Integer>> ret2 = new ArrayList<>();
        for (List<Integer> l : ret) {
            if (l.size() >= 2) {
                ret2.add(l);
            }
        }
        return ret2;
    }

    public List<List<Integer>> findSubsequences4(int[] nums) {
        Set<List<Integer>> res= new HashSet<List<Integer>>();
        List<Integer> holder = new ArrayList<Integer>();
        findSequence(res, holder, 0, nums);
        List result = new ArrayList(res);
        return result;
    }

    public void findSequence(Set<List<Integer>> res, List<Integer> holder, int index, int[] nums) {
        //why this set here is working while above one is not...
        if (holder.size() >= 2) {
            res.add(new ArrayList(holder));
        }
        for (int i = index; i < nums.length; i++) {
            if(holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) {
                holder.add(nums[i]);
                findSequence(res, holder, i + 1, nums);
                holder.remove(holder.size() - 1);
            }
        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        int nums [] = {7,7,7};

        for (List<Integer> l : s.findSubsequences3(nums)) {
            for (int i : l) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
