package com.l1337.l412;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    //how to avoid division..
    //https://leetcode.com/problems/fizz-buzz/discuss/89931/
    public List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<>();
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                int j = i + 1;
                String tmp = Integer.toString(j);
                boolean f1 = false, f2 = false;
                if (j % 3 == 0) {
                    f1 = true;
                    tmp = "Fizz";
                }

                if (j % 5 == 0) {
                    f2 = true;
                    tmp = "Buzz";
                }

                if (f1 && f2)
                    tmp = "FizzBuzz";
                ret.add(tmp);
            }
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        for (String l : s.fizzBuzz(20)) {
            System.out.println(l);
        }
    }
}
