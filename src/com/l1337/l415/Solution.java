package com.l1337.l415;


public class Solution {

//    https://leetcode.com/problems/add-strings/
    //make your code concise
    //https://leetcode.com/problems/add-strings/discuss/90522/3ms-5-lines-Concise-C++-Solution-without-extra-space.-The-loop-should-stop-early!
//    https://leetcode.com/problems/add-strings/discuss/90436/Straightforward-Java-8-main-lines-25ms
//    https://leetcode.com/problems/add-strings/description/
    public String addStrings(String num1, String num2) {
        int carryOn = 0;
        String shortStr,longStr;
        if (num1.length() > num2.length()) {
            shortStr = num2;
            longStr = num1;
        } else {
            shortStr = num1;
            longStr = num2;
        }

        StringBuilder sb = new StringBuilder();
        int diff = longStr.length() - shortStr.length();
        for (int i = shortStr.length() - 1; i >= 0; --i) {
            int sum = carryOn + (shortStr.charAt(i) - '0') + (longStr.charAt(diff + i) - '0');
            if (sum >= 10) {
                sum -= 10;
                carryOn = 1;
            } else {
                carryOn = 0;
            }
            sb.append(sum);
        }

        for (int i = diff - 1; i >= 0; --i) {
            int sum = carryOn + (longStr.charAt(i) - '0');
            if (sum >= 10) {
                sum -= 10;
                carryOn = 1;
            } else {
                carryOn = 0;
            }
            sb.append(sum);
        }
        if (carryOn > 0)
            sb.append(carryOn);
        sb.reverse();
        return sb.toString();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        String num1 = "0";
        String num2 = "0";
        System.out.println(s.addStrings(num1, num2));
    }
}
