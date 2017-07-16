package com.l1337.l616;


public class Solution {

    /*
    [LeetCode] Add Bold Tag in String 字符串中增添加粗标签


Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:

Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"


Example 2:

Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"


Note:

The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].s
     */


//    http://www.cnblogs.com/grandyang/p/7043394.html
    public String addBoldTag(String s, String[] dict) {
        //s.length() >= 1
        boolean bold [] = new boolean[s.length()];

        int end = 0;

        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < dict.length; ++j) {
                int len = dict[j].length();
//                System.out.println(s.substring(i, i + len) + "\t" + dict[j]);
                if (i + len <= s.length() && s.substring(i, i + len).equals(dict[j])) {
                    end = Math.max(end, i + len);
                }
            }
            bold[i] = end > i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (!bold[i]) {
                sb.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) ++j;
            sb.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }
        return sb.toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.addBoldTag("abcxyz123", new String[]{"abc","123"}));
//        System.out.println(s.addBoldTag("aaabbcc", new String[]{"aaa","aab","bc"}));
    }
}
