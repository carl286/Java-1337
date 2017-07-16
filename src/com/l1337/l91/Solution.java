package com.l1337.l91;


public class Solution {

    public int numDecodings(String s) {
        //validation
        for (int i = 0; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i)))
            {
                if (s.charAt(i) == '0' && (i == 0 || (s.charAt(i-1) != '1' && s.charAt(i-1) != '2')))
                    return 0;
            }
            else
            {
                return 0;
            }
        }

//        if (s.length() == 1)
//            return 1;

        int a0 = 1, a1 = 1;

        for (int i = 1; i < s.length(); ++i) {
            int tmp = a1;
            switch (s.charAt(i-1))
            {
                case '1':
                    if (s.charAt(i) == '0') {
                        a1 = a0;
                        a0 = tmp;
                    }
                    else
                    {
                        a1 += a0;
                        a0 = tmp;
                    }
                    break;
                case '2':
                    if (s.charAt(i) > '6') {
                        a0 = tmp;
                    } else if (s.charAt(i) == '0') {
                        a1 = a0;
                        a0 = tmp;
                    } else {
                        a1 += a0;
                        a0 = tmp;
                    }
                    break;
                default:
                    //a1 is unchanged
                    a0 = tmp;
            }

        }
        return a1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        //System.out.println(s.numDecodings("226"));
        System.out.println(s.numDecodings("20"));
    }
}
