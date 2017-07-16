package com.l1337.l670;


public class Solution {

    public int maximumSwap(int num) {
        if (num <= 10)
            return num;

        String tmp = Integer.toString(num);
        char [] data = tmp.toCharArray();
        int [] max_right = new int [data.length];

        max_right[max_right.length - 1] = max_right.length - 1;
        int i = max_right.length - 2;
        while (i >= 0) {
            if (data[i+1] > data[max_right[i+1]])
                max_right[i] = i + 1;
            else
                max_right[i] = max_right[i+1];
            --i;
        }

        i = 0;
        while (i < data.length - 1) {
            if (data[i] < data[max_right[i]])
                break;
            ++i;
        }

        if (i < data.length - 1) {
            char c = data[max_right[i]];
            data[max_right[i]] = data[i];
            data[i] = c;
        }

        return Integer.parseInt(new String(data));
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.maximumSwap(1100));
    }
}
