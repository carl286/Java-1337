package com.l1337.l468;


public class Solution {
    static private final String NEITHER = "Neither";
    static private final String IPV4 = "IPv4";
    static private final String IPV6 = "IPv6";

    //[start, end)
    boolean isV4Component(char [] array, int start, int end) {
        int len = end - start;
        if (len < 1 || len > 4)
            return false;

        if (array[start] == '0' && len > 1)
            return false;

        //convert to int
        int i = 0;
        for (int k = start; k < end; ++k) {
            int d = (array[k] - '0');
            if (d < 0 || d > 9)
                return false;
            i = 10 * i + d;
        }

        return i < 256;
    }

    //[start, end)
    boolean isV6Component(char [] array, int start, int end) {
        int len = end - start;
        if (len < 1 || len > 4)
            return false;

        for (int i = start; i < end; ++i)
            if (!((array[i] <= '9' && array[i] >= '0') || (array[i] <= 'f' && array[i] >= 'a')))
                return false;

        return true;
    }

    public String validIPAddress(String IP) {
        int length = IP.length();
        if (length >= 7 && length <= 15 && IP.indexOf('.') >= 0) {
            //IPV4 PATH
            char []array = IP.toCharArray();
            int start = 0, cnt = 0, i;
            for (i = 0; i <= array.length; ++i) {
                if (i == array.length) {
                    if (isV4Component(array, start, i))
                        ++cnt;
                    else
                        return NEITHER;
                } else if (array[i] == '.') {
                    if (isV4Component(array, start, i))
                        ++cnt;
                    else
                        return NEITHER;
                    start = i + 1;
                }
            }

            if (cnt == 4)
                return IPV4;
            else
                return NEITHER;
        } else if (length >= 15 && length <= 39 && IP.indexOf(':') >= 0) {
            //IPV6 PATH
            char []array = IP.toLowerCase().toCharArray();
            int start = 0, cnt = 0, i;
            for (i = 0; i <= array.length; ++i) {
                if (i == array.length) {
                    if (isV6Component(array, start, i))
                        ++cnt;
                    else
                        return NEITHER;
                } else if (array[i] == ':') {
                    if (isV6Component(array, start, i))
                        ++cnt;
                    else
                        return NEITHER;
                    start = i + 1;
                }
            }

            if (cnt == 8)
                return IPV6;
            else
                return NEITHER;
        }

        return NEITHER;
    }

//    https://leetcode.com/problems/validate-ip-address/discuss/95491/Java-Simple-Solution
//    https://leetcode.com/problems/validate-ip-address/discuss/95504/Java-Simple-Solution-with-RegExp
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
    }
}
