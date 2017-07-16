package com.l1337.l555;


public class Solution {

    /*
    split concatenated strings

    Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.

Specifically, to find the lexicographically biggest string, you need to experience two phases:

Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.


And your job is to find the lexicographically biggest one among all the possible regular strings.

Example:

Input: "abc", "xyz"
Output: "zyxcba"
Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-",
where '-' represents the looped status.
The answer string came from the fourth looped one,
where you could cut from the middle character 'a' and get "zyxcba".


Note:

The input strings will only contain lowercase letters.
The total length of all the strings will not over 1,000.
     */


    private String getMaxString(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            ++i;
            --j;
        }

        if (i < j) {
            return s.charAt(i) < s.charAt(j) ? new StringBuilder(s).reverse().toString(): s;
        } else {
            return s;
        }
    }

//    http://www.cnblogs.com/grandyang/p/6887140.html
//    https://www.jianshu.com/p/fb1f7b70fa43
    public String splitLoopedString(String[] strs) {
        String ref = "";
        int acc = 0;
        int [] accLength = new int [strs.length];
        for (int i = 0; i < strs.length; ++i) {
            ref += getMaxString(strs[i]);
            acc += strs[i].length();
            accLength[i] = acc;
        }

        String ret = ref;

        for (int i = 0; i < strs.length; ++i) {
            for (int j = 0; j < strs[i].length(); ++j) {
                if (strs[i].charAt(j) > ret.charAt(0)) {

                    String potential1 = strs[i].substring(j) + ref.substring(accLength[i]) +
                            (i == 0 ? "" : ref.substring(0, accLength[i-1])) + strs[i].substring(0,j);
                    String potential2 = new StringBuilder(strs[i].substring(0,j+1)).reverse().toString()
                            + ref.substring(accLength[i]) + (i == 0 ? "" : ref.substring(0, accLength[i-1])) +
                            new StringBuilder(strs[i].substring(j+1)).reverse().toString();
                    if (potential1.compareTo(ret) > 0) {
                        ret = potential1;
                    }
                    if (potential2.compareTo(ret) > 0) {
                        ret = potential2;
                    }
                }
            }
        }

        return ret;
    }


        public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.splitLoopedString(new String[] {"ecxba"}));
    }
}
