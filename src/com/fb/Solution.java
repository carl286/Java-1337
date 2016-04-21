package com.fb;


import com.l1337.common.Interval;
import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.SynchronousQueue;

class DeepestCommonAncestoerRet {
    int depth;
    TreeNode commonAncestor;
}
public class Solution {

//    http://www.geeksforgeeks.org/find-subarray-with-given-sum/
//Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.
    boolean subArraySum(int arr[], int sum) {
        if (arr == null || sum < 0)
            return false;
//        if (sum == 0)//???
//            return true;
//        if (arr.length <= 0)
//            return false;
        int acc = 0;
        int start = 0, index = 0;
        while (index < arr.length) {
            acc += arr[index];

            while (acc > sum && start <= index) {
                acc -= arr[start++];
            }

            if (start <= index && acc == sum)
                return true;

            ++index;
        }

        return false;
    }

    //    http://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
//Given an unsorted array of integers (might contain negative numbers), find a continous subarray which adds to a given number.
    //Use your brains....
    boolean subArraySumII(int arr[], int sum) {
        if (arr == null || sum < 0)
            return false;
        HashSet<Integer> set = new HashSet<>();
        int acc = 0;
        for (int i = 0; i < arr.length; ++i) {
            acc += arr[i];
            if (acc == sum)
                return true;
            if (set.contains(acc -sum))
                return true;
            set.add(acc);
        }

        return false;
    }


    DeepestCommonAncestoerRet deepestCommonAncestoerHelper(TreeNode root) {
        DeepestCommonAncestoerRet ret = new DeepestCommonAncestoerRet();
        if (root == null) {
            ret.depth = 0;
            ret.commonAncestor = null;
        } else {
            DeepestCommonAncestoerRet left = deepestCommonAncestoerHelper(root.left);
            DeepestCommonAncestoerRet right = deepestCommonAncestoerHelper(root.right);
            if (left.depth == right.depth) {
                ret.commonAncestor = root;
            } else {
                ret.commonAncestor = left.depth > right.depth ? left.commonAncestor : right.commonAncestor;
            }
            ret.depth = Math.max(left.depth, right.depth) + 1;
        }

        return ret;
    }
    TreeNode deepestCommonAncestoer(TreeNode root) {
        return deepestCommonAncestoerHelper(root).commonAncestor;
    }


//    给你一个int N，然后给你一个int[]，数组里每个元素只能用一次，要求通过加法得到从1到N的所有数字，返回最少需要添加元素的个数
//    比如，我给你N=6，那么我们需要通过加法得到1，2，3，4，5，6
//    假设我们的array是[1,3]
//            1已经有了，
//            2没有，需要添加
//    3可以用过1+2得到
//    4可以通过1+3得到
//    5可以通过2+3得到
//    6可以通过1+2+3得到，所以我们只需要添加一个2便可以满足条件，那么返回1，因为只需要添加一个元素。
//    比较难的case就是这种，假设N=10，数组是{1,2,3,7,9,10}；
    int minimumRequired(int [] array, int n) {
        if (array == null)
            return 0;
        Arrays.sort(array);
        int ret = 0;
        int maxCovered = 1;
        int i = 0;
        //hopefully, it won't overflow...
        while (maxCovered <= n) {
            if (i < array.length && maxCovered >= array[i])
                maxCovered += array[i++];
            else {
                ++ret;
                maxCovered <<= 1;
            }
        }
        return ret;
    }

//    给一个正数n，打印出所有相加的组合
//            例如10可以打印出
//    1+1+1+...1
//            1+2+1+...1
//    9+1
//            10

    void print(ArrayList<Integer> acc) {
        for (Integer i : acc)
            System.out.print(i+"\t");
        if (acc.size() > 0)
            System.out.println();
    }
    void printSumHelper(ArrayList<Integer> acc) {
        //n should be at least 1 when calling
        print(acc);
        int n = acc.remove(acc.size()-1);
        int leftMin = 1;
        if (acc.size() > 0)
            leftMin = acc.get(acc.size()-1);
        int cutOff = n / 2;
        for (int i = leftMin; i <= cutOff; ++i) {
            acc.add(i);
            acc.add(n - i);
            printSumHelper(acc);
            acc.remove(acc.size()-1);
//            acc.remove(acc.size()-1);
        }
//        acc.add(n);
    }
    void printSum(int n) {
        if (n <= 0)
            return;
        ArrayList<Integer> acc = new ArrayList<>();
        acc.add(n);
        printSumHelper(acc);
    }


//    第二轮，老外面试官，给一个String, 如AABACCDCD, 插入'_'使同一个字母间隔为k: 如果k=3: A___AB__AC___CD__CD, 一开始理解有误，认为是要先shuffle字母顺序然后插入'_'，花了不少时间，然后面试官提示字母顺序不变，写出来，然后直接run出来有bug，在coderpad上调了一会才通过。。。

    String insertPadding(String s, char pad, int k) {
        if (s == null || s.length() <= 1 || k <= 0)
            return s;
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), sb.length());
                sb.append(s.charAt(i));
            } else if (sb.length() - map.get(s.charAt(i)) > k) {
                map.put(s.charAt(i), sb.length());
                sb.append(s.charAt(i));
            } else {
                int delta = k - (sb.length() - 1 - map.get(s.charAt(i)));
                char[] repeat = new char[delta];
                Arrays.fill(repeat, pad);
                sb.append(repeat);
                map.put(s.charAt(i), sb.length());
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        int arr[] = {1, 4, 20, 3, 10, 5}, sum = 33  ;
//        for (int i = 0; i < arr.length; ++i)
//            System.out.println(s.subArraySumII(arr, sum));
//
//        int n = 10,  array [] = {1,2,3,7,9,10};
//        System.out.println(s.minimumRequired(array, n));

//        int n = 10;
//        s.printSum(n);

        String input = "AABACCDCD";
        int k = 3;
        char pad = '_';
        System.out.println(s.insertPadding(input, pad, k));
    }
}
