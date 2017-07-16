package com.l1337.l639;


public class Solution {

    private final static int mod = 1000000007;
//    https://leetcode.com/problems/decode-ways-ii/discuss/105275/Java-DP-O(n)-time-and-O(1)-space
    public int numDecodings(String s) {
        if (s.length() <= 0)
            return 0;
        if (s.charAt(0) == '0')
            return 0;

        char pre = s.charAt(0);
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                if (pre == '0' || (pre >= '3' && pre <= '9')) {
                    return 0;
                }
            }

            pre = s.charAt(i);
        }

        int length = s.length();
        long [] start_alone = new long [length + 1];
        long [] start_with_others = new long [length + 1];

        start_with_others[length] = 1;
        start_alone[length] = 0;

        switch (s.charAt(length-1)) {
            case '0':
                start_alone[length-1] = 0;
                break;
            case '*':
                start_alone[length-1] = 9;
                break;
            default:
                start_alone[length-1] = 1;
                break;
        }

        for (int i = length - 2; i >= 0; --i) {
            switch (s.charAt(i)) {
                case '0':
                    start_alone[i] = 0;
                    start_with_others[i] = 0;
                    break;

                case '1':
                    start_alone[i] = (start_alone[i+1] + start_with_others[i+1]) % mod;

                    if (s.charAt(i+1) != '*')
                        start_with_others[i] = (start_alone[i+2] + start_with_others[i+2]) % mod;
                    else
                        start_with_others[i] = (9*((start_alone[i+2] + start_with_others[i+2])% mod) % mod);

                    break;
                case '2':
                    start_alone[i] = (start_alone[i+1] + start_with_others[i+1]) % mod;

                    if (s.charAt(i+1) >= '7' && s.charAt(i+1) <= '9')
                        start_with_others[i] = 0;
                    else if (s.charAt(i+1) >= '0' && s.charAt(i+1) <= '6')
                        start_with_others[i] = (start_alone[i+2] + start_with_others[i+2]) % mod;
                    else if (s.charAt(i+1) == '*')
                        start_with_others[i] = (6*((start_alone[i+2] + start_with_others[i+2]) % mod)% mod);
                    break;

                case '*':
                    start_alone[i] = (9*((start_alone[i+1] + start_with_others[i+1]) % mod)) %mod;

                    if (s.charAt(i+1) >= '7' && s.charAt(i+1) <= '9') {
                        start_with_others[i] = (start_alone[i+2] + start_with_others[i+2]) % mod;
                    } else if (s.charAt(i+1) >= '0' && s.charAt(i+1) <= '6') {
                        start_with_others[i] = (2*((start_alone[i+2] + start_with_others[i+2])% mod)) % mod;
                    } else { //must be '*'
                        start_with_others[i] = (15*((start_alone[i+2] + start_with_others[i+2])% mod)) % mod;
                    }
                    break;

                default:
                    //3-9
                    start_alone[i] = (start_alone[i+1] + start_with_others[i+1]) % mod;
                    start_with_others[i] = 0;

                    break;
            }

        }

        return (int) ((start_alone[0] + start_with_others[0]) % mod);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.numDecodings("********"));
    }
}
