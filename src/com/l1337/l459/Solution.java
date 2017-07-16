package com.l1337.l459;


public class Solution {

//    https://leetcode.com/problems/repeated-substring-pattern/discuss/94334/Easy-python-solution-with-explaination
    public boolean repeatedSubstringPattern(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j && j >= (s.length() + 1)/2) {
            if (s.charAt(i) != s.charAt(j)) {
                --j;
            } else {
                //check if match
                //length from j to s.legnth() -1, length is (s.length() - j)
                int length = s.length() - j;
                if (s.length() % length == 0) {
                    //check substring
                    int chunk = s.length() / length;
                    int k = 1;
                    boolean match = true;
                    while (k < chunk && match) {
                        for (int t = 0; t < length; ++t) {
                            if (s.charAt(t) != s.charAt(length * k + t)) {
                                match = false;
                                break;
                            }
                        }
                        ++k;
                    }
                    if (match)
                        return true;
                }
                --j;
            }
        }

        return false;
    }

    public boolean repeatedSubstringPattern2(String s) {
        //make sure you understand Java syntax
        return (s + s).substring(1, 2 * s.length() - 1).contains(s);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.repeatedSubstringPattern2("bb"));
    }
}
