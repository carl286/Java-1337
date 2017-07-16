package com.l1337.l905;


public class Solution {
    private void swap(int [] A, int i, int j)
    {
        if (i != j)
        {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }
    public int[] sortArrayByParity(int[] A) {
        int l = 0, r = A.length - 1, cur = 0;
        while (cur < r)
        {
            if ((A[cur] % 2) == 0)
            {
                A[l++] = A[cur++];
            }
            else
            {
                swap(A, cur, r);
                --r;
            }
        }

        return A;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
