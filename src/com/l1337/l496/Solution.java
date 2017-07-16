package com.l1337.l496;


import com.googlePrep.Pack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;

public class Solution {

//    https://leetcode.com/problems/next-greater-element-i/discuss/97616/Meh-1000-is-small
//    https://leetcode.com/problems/next-greater-element-i/discuss/97595/Java-10-lines-linear-time-complexity-O(n)-with-explanation
//    https://leetcode.com/problems/next-greater-element-i/discuss/158607/6-line-7~12ms-java-solution-with-no-mapstackhashmap
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums2.length == 0)
            return new int [0];

        TreeSet<Integer> tree = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] ret = new int[nums1.length];

        for (int i = 0; i < nums2.length; ++i) {
            Integer lower;
            while ((lower = tree.lower(nums2[i])) != null) {
                tree.remove(lower);
                map.put(lower, nums2[i]);
            }
            tree.add(nums2[i]);
        }

//        for (int i = nums2.length - 1; i >= 0; --i) {
//            Integer higher = tree.higher(nums2[i]);
//            map.put(nums2[i], higher == null ? -1 : higher);
//            tree.add(nums2[i]);
//        }


        for (int i = 0; i < nums1.length; ++i) {
            ret[i] = map.getOrDefault(nums1[i], -1);
        }

        return ret;
    }

    public int[] nextGreaterElementStack(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ret = new int[nums1.length];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < nums2.length; ++i) {
            while (!st.isEmpty() && st.peek() < nums2[i]) {
                map.put(st.pop(), nums2[i]);
            }
            st.push(nums2[i]);
        }

//        for (int i = nums2.length - 1; i >= 0; --i) {
//            Integer higher = tree.higher(nums2[i]);
//            map.put(nums2[i], higher == null ? -1 : higher);
//            tree.add(nums2[i]);
//        }


        for (int i = 0; i < nums1.length; ++i) {
            ret[i] = map.getOrDefault(nums1[i], -1);
        }

        return ret;
    }

//    https://leetcode.com/problems/next-greater-element-i/
    public int[] nextGreaterElementI_Feb11_2021(int[] nums1, int[] nums2) {
        int [] ret = new int [nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < nums2.length; ++i)
        {
            while (!st.isEmpty() && st.peek() < nums2[i])
            {
                map.put(st.pop(), nums2[i]);
            }
            st.push(nums2[i]);
        }

        for (int i = 0; i < ret.length; ++i)
            ret[i] = map.getOrDefault(nums1[i], -1);
        return ret;
    }

//    https://leetcode.com/problems/next-greater-element-ii/
    public int[] nextGreaterElements(int[] nums) {
        int[] ret = new int[nums.length];
        //init
        for(int i = 0; i < ret.length; ++i)
            ret[i] = -1;
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < (nums.length << 1) - 1; ++i)
        {
            while (!st.isEmpty() && nums[st.peek()] < nums[i % nums.length])
            {
                ret[st.pop()] = nums[i % nums.length];
            }

            if (i < nums.length)
                st.push(i);
        }

        return ret;
    }

    private void swap(char p [], int i, int j)
    {
        char c = p[i];
        p[i] = p[j];
        p[j] = c;
    }
    public int nextGreaterElement(int n) {
        String s = Integer.toString(n);
        char [] data = s.toCharArray();
        int i = data.length - 2;
        while (i >= 0 && data[i] >= data[i+1])
            --i;

        //if break, either i < 0 or data[i] < data[i+1]
        if (i < 0)
            return -1;
        //find the index to do swap
        int j = i + 1;
        while (j < data.length && data[j] > data[i])
            ++j;
        swap(data, i, j - 1);
        //reverse, from i + 1 to the last
        int k = s.length() - 1;
        j = i + 1;
        while (j < k)
        {
            swap(data, j++, k--);
        }

        try {
            return Integer.parseInt(new String(data));
        } catch (NumberFormatException e)
        {
            return -1;
        }
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        for(int i : s.nextGreaterElement(new int [] {2,4}, new int [] {1,2,3,4})){
//            System.out.print(i + "\t");
//        }
        int n = 2147483647-1;
        System.out.println(s.nextGreaterElement(n));
    }
}
