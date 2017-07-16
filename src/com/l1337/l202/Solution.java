package com.l1337.l202;


import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int calculate(int n)
    {
        int sum = 0;
        while (n != 0)
        {
            sum += (n%10) * (n%10);
            n = n/10;
        }

        return sum;
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        do {
            set.add(n);
            n = calculate(n);
        } while (n != 1 && !set.contains(n));
        return n == 1;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
