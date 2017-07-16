package com.l1337.l592;


public class Solution {

    private long gcd(long a, long b) {
        if (a == 0 || b == 0)
            return a == 0 ? b : a;

        //assume a <= b
        if (a > b) {
            //swap a, b
            long c = a;
            a = b;
            b = c;
        }

        //what if a or b is 0...., let's return 1 here...

        //a <= b now...
        while (a != 0) {
            long c = b % a;
            b = a;
            a = c;
        }

        return b;
    }


//    https://leetcode.com/problems/fraction-addition-and-subtraction/discuss/103384/Small-simple-C++JavaPython
    public String fractionAddition(String expression) {
        boolean toAdd= true;

        long numerator = 0, denominator = 0;
        int i = 0;
        //assume len(expression) >= 1
        if (expression.charAt(0) == '-') {
//            expression = expression.substring(1);
            toAdd = false;
            ++i;
        }

        while (i < expression.length()) {
            int pos1 = expression.indexOf('+', i);
            int pos2 = expression.indexOf('-', i);
            boolean end = false;
            boolean next_plus = true;
            int pos;

            if (pos1 < 0 && pos2 < 0) {
                pos = -1;
                end =true;
            } else if (pos1 > 0 && pos2 > 0) {
                pos = Math.min(pos1, pos2);
            } else {
                pos = pos1 > 0 ? pos1 : pos2;
            }

            if (pos == pos2)
                next_plus = false;

//            System.out.println("from: " + i);
            int index_of_splash = expression.indexOf('/', i);
//            System.out.println("/: " + index_of_splash);

            int numerator2 = Integer.parseInt(expression.substring(i, index_of_splash));
            int denominator2 = Integer.parseInt(expression.substring(index_of_splash+1, end ? expression.length() : pos));


            //to add
            if (numerator == 0) {
                numerator = toAdd ? numerator2 : 0 - numerator2;
                denominator = denominator2;
            } else if (numerator2 != 0) {
                if (toAdd) {
                    numerator = numerator * denominator2 + numerator2 * denominator;
                } else {
                    numerator = numerator * denominator2 - numerator2 * denominator;
                }

                denominator *= denominator2;
                long gcd = gcd(Math.abs(numerator), Math.abs(denominator));
                if (gcd > 1) {
                    numerator /= gcd;
                    denominator /= gcd;
                }
            }

            toAdd = next_plus;
            if (pos < 0)
                i = expression.length();
            else
                i = pos + 1;
        }

        return numerator + "/" + denominator;
//        return null;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.fractionAddition(new String("1/3-1/2")));
        for (String ss : new String("-4/7-3/4+2/3").split("(?=[-+])")) {
            System.out.println(ss);
        }

//        System.out.println(new String("-1/2+1/2").indexOf(0, '/'));
    }
}
