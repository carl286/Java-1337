package com.l1337.l60;


public class Solution {
    public String getPermutation(int n, int k) {
        char tmp [] = new char[n];
        int data [] = new int [n];
        data[0] = 1;
        for (int i = 1; i < data.length; ++i) {
            data[i] = data[i-1] * i;
        }

        boolean map [] = new boolean[n];
        for (int i = 0; i < tmp.length; ++i) {
            int avi = (k - 1) / data[tmp.length - 1 - i] + 1;
            k %= data[tmp.length - 1 - i];
            if (k == 0)
                k = data[tmp.length-1-i];
            for (int j = 0; j  <  tmp.length; ++j) {
                if (!map[j]) {
                    --avi;
                    if (avi == 0) {
                        tmp[i] = (char)('0' + (j + 1));
                        map[j] = true;
                        break;
                    }
                }
            }
        }

        return new String(tmp);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.getPermutation(3, 3));
    }
}
