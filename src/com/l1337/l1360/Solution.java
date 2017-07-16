package com.l1337.l1360;


public class Solution {

    private static int [] dates = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int daysBetweenDates(String date1, String date2) {
        String [] lower = date1.split("-");
        String [] higher = date2.split("-");

        int [] l = new int [lower.length];
        int [] h = new int [higher.length];

        for (int i = 0; i < l.length; ++i)
        {
            l[i] = Integer.valueOf(lower[i]);
            h[i] = Integer.valueOf(higher[i]);
        }
        int delta1 = 0, delta2 = 0;

        for (int i = 0; i < l[1]; ++i)
        {
            delta1 += dates[i];
        }
        if (l[1] > 2 && isPrime(l[0]))
            ++delta1;
        delta1 += l[2];

        for (int i = 0; i < h[1]; ++i)
        {
            delta2 += dates[i];
        }
        if (h[1] > 2 && isPrime(h[0]))
            ++delta2;
        delta2 += h[2];

        int start = l[0];
        if (l[0] > h[0])
            start = h[0];

        for(int i = start; i < l[0]; ++i)
            if (isPrime(i))
                delta1 += 366;
            else
                delta1 += 365;
        for(int i = start; i < h[0]; ++i)
            if (isPrime(i))
                delta2 += 366;
            else
                delta2 += 365;

        return Math.abs(delta1 - delta2);
    }

    private boolean isPrime(int year)
    {

        if ((year % 100) == 0)
            return (year % 400 == 0);
        else
            return (year % 4 == 0);
    }

//    https://leetcode.com/problems/number-of-days-between-two-dates/discuss/517593/Calling-API-or-Not-That-is-the-Question
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.daysBetweenDates("2019-06-29", "2019-06-30"));
    }
}
