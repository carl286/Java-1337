package com.l1337.l537;


public class Solution {
    private int [] parseComplexNumber(String x) {
        int [] ret = new int[2];
        int i = x.length() - 1;

        if (x.charAt(i) == 'i') {
            //parse virtual part

            --i;
            int j = i;
            while (x.charAt(j) != '+')
                --j;

            ret[1] = Integer.valueOf(x.substring(j + 1, x.length() - 1));
            i = j;
        }

        ret[0] = Integer.valueOf(x.substring(0, i));

        return ret;
    }
    public String complexNumberMultiply(String a, String b) {
        int x[] = parseComplexNumber(a);
        int y[] = parseComplexNumber(b);
        int a1 = x[0] * y[0] - x[1] * y[1];
        int b1 = x[0] * y[1] + x[1] * y[0];

        return a1 + "+" + b1 + "i";
    }

//    https://leetcode.com/problems/complex-number-multiplication/discuss/168682/Java-5-lines-easy-to-understand
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.complexNumberMultiply("1+-1i", "0+0i"));
    }
}
