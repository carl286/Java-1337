package com.l1337.l667;


public class Solution {

    private void swap(int [] array, int i, int j) {
        if (i != j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public int[] constructArray(int n, int k) {
        if (k > n - 1)
            return null;

        //if n == 1, k can equal to 0
        int[] ret = new int[n];
        int small = 1, large = n, index = 0;
        ret[index++] = small++;
        boolean isLastSmall = true;

        while (k > 1) {
            if (!isLastSmall) {
                ret[index++] = small++;
                isLastSmall = true;
            } else {
                ret[index++] = large--;
                isLastSmall = false;
            }
            --k;
        }

        while (index < n) {
            if (isLastSmall)
                ret[index++] = small++;
            else
                ret[index++] = large--;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] ret = s.constructArray(3,2);
        for (int i = 0; i < ret.length; ++i)
            System.out.print(ret[i] + "\t");
    }
}
