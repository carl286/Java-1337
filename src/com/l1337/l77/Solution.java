package com.l1337.l77;

import java.util.*;

public class Solution {
    private void helper(List<List<Integer>>ret, int [] data, int startIndex, Integer [] tmp, int tmpIndex) {
        if (tmpIndex == tmp.length) {
            ret.add(new ArrayList<>(Arrays.asList(tmp)));
            return;
        }

        for (int i = startIndex; data.length - i >= tmp.length - tmpIndex; ++i) {
            //optimize it later
            tmp[tmpIndex] = data[i];
            helper(ret, data, i + 1, tmp, tmpIndex + 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        if (k > n || k <= 0 || n <= 0)
            return new ArrayList<>();

        int [] data = new int [n];
        for (int i = 0; i < n; ++i)
            data[i] = i + 1;

        Integer [] tmp = new Integer[k];
        List<List<Integer>> ret = new ArrayList<>();

        helper(ret, data, 0, tmp, 0);
        return null;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
