package com.l1337.l66;

public class Solution {

    public int[] plusOne(int[] digits) {
        int carryOn = 1;
        for (int i = digits.length; i > 0; --i) {
            digits[i-1] += carryOn;
            if (digits[i-1] >= 10) {
                digits[i-1] -= 10;
                carryOn = 1;
            } else {
                carryOn = 0;
            }
        }
        if (carryOn > 0) {
            int [] ret = new int [1 + digits.length];
            ret[0] = carryOn;
            for (int i = 1; i < ret.length; ++i) {
                ret[i] = digits[i-1];
            }
            return ret;
        } else {
            return digits;
        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
