package com.l1337.l989;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

//    https://leetcode.com/problems/add-to-array-form-of-integer/discuss/234488/JavaC%2B%2BPython-Take-K-itself-as-a-Carry
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ret = new ArrayList<>();
        String ks = Integer.toString(K);
        int i = A.length, j = ks.length();
        int carryOn = 0;
        while (i > 0 && j > 0)
        {
            int sum = A[i-1] + (ks.charAt(j-1) - '0') + carryOn;
            if (sum >= 10)
            {
                sum -= 10;
                carryOn = 1;
            }
            else
            {
                carryOn = 0;
            }
            ret.add(sum);
            --i;--j;
        }
        while (i > 0)
        {
            int sum = A[i-1] + carryOn;
            if (sum >= 10)
            {
                sum -= 10;
                carryOn = 1;
            }
            else
            {
                carryOn = 0;
            }
            ret.add(sum);
            --i;
        }
        while (j > 0)
        {
            int sum = (ks.charAt(j-1) - '0') + carryOn;
            if (sum >= 10)
            {
                sum -= 10;
                carryOn = 1;
            }
            else
            {
                carryOn = 0;
            }
            ret.add(sum);
            --j;
        }
        if (carryOn > 0)
        {
            ret.add(carryOn);
        }

        Collections.reverse(ret);
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
