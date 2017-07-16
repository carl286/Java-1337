package com.l1337.l166;


import java.util.HashMap;
import java.util.Map;

public class Solution {

//    https://leetcode.com/submissions/detail/62432161/
    public String fractionToDecimal(int numerator, int denominator) {
        boolean isPositive = true;
        long absdenominator = denominator;
        long absnumerator = numerator;
        if (numerator > 0 && denominator < 0)
        {
            isPositive = false;
            absdenominator = 0l - denominator;
        }
        if (numerator < 0 && denominator > 0) {
            isPositive = false;
            absnumerator = 0l - absnumerator;
        }

        long beforeZero = absnumerator / absdenominator;
        if (absnumerator % absdenominator == 0)
            return isPositive ? Long.toString(beforeZero) : ("-" + Long.toString(beforeZero));

        absnumerator = 10 * (absnumerator % absdenominator);
        //has remaining part...
        Map<Long, Integer> reminder = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        while (absnumerator != 0 && !reminder.containsKey(absnumerator))
        {
            long val = absnumerator / absdenominator;
            sb.append(val);
            reminder.put(absnumerator, sb.length());
            //overflow??
            absnumerator = 10 * (absnumerator % absdenominator);
        }

        if (absnumerator == 0)
            return isPositive ? (beforeZero + "." + sb) : ("-" + beforeZero + "." + sb) ;
        else
        {
            sb.insert(reminder.get(absnumerator) - 1, "(");
            sb.insert(sb.length(), ")");
            return isPositive ? (beforeZero + "." + sb) : ("-" + beforeZero + "." + sb);
        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.fractionToDecimal(1,2));
    }
}
