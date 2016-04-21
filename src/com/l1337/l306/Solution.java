package com.l1337.l306;


import java.util.Arrays;

//https://leetcode.com/problems/additive-number/
//306. Additive Number
public class Solution {

    boolean hasLeadingZeros(String s)
    {
        return s.length() > 1 && s.charAt(0) == '0';
    }
    String stringAdd(String a, String b) {
//        System.out.println(a+"\t"+b);
        //a,b of length at least 1
        int l1 = a.length(), l2 = b.length();
        int l = Math.max(l1,l2) + 1;
        char [] tmp = new char[l];
        int carryOn = 0;
        int i = l1-1,j=l2-1;
        while(i >=0 || j >=0 || carryOn>=1) {
            int sum = 0;
            if (i >= 0)
                sum += (a.charAt(i--) - '0');
            if (j >= 0)
                sum += (b.charAt(j--) - '0');
            sum += carryOn;
            if (sum >= 10) {
                sum -= 10;
                carryOn = 1;
            } else {
                carryOn = 0;
            }
            tmp[--l] = (char)(sum + '0');
        }
        if (l == 0)
            return new String(tmp);
        else
            return new String(Arrays.copyOfRange(tmp, 1, tmp.length));
    }
    boolean isAdditiveNumberHelper(String a,String b, String num) {
        if (num.length() <= 0)
            return true;
//        String c = Integer.toString(Integer.parseInt(a)+Integer.parseInt(b));
        String c = stringAdd(a,b);
        if (!num.startsWith(c))
            return false;
        return isAdditiveNumberHelper(b,c,num.substring(c.length()));
    }
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() <= 2)
            return false;

        //select a,b, then start
        for (int i = 0; i < num.length(); ++i)
            for (int j = i + 1; j + 1< num.length(); ++j) {
                String a = num.substring(0, i+1);
                String b = num.substring(i+1, j + 1);
                if (!hasLeadingZeros(a) && !hasLeadingZeros(b)) {
                    if (isAdditiveNumberHelper(a,b,num.substring(j+1)))
                        return true;
                }
            }
        return false;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        String num = "112358";
        String num = "199100199";
        System.out.println(s.isAdditiveNumber(num));
    }
}
