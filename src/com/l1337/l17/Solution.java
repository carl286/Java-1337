package com.l1337.l17;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    char [][] dict= {
        {'a','b','c'},
        {'d','e','f'},
        {'g','h','i'},
        {'j','k','l'},
        {'m','n','o'},
        {'p','q','r', 's'},
        {'t','u','v'},
        {'w','x','y', 'z'},
    };

    public void letterCombinationsRecursive(String digits, int index, StringBuilder sb, List<String> ret) {
        if (index == digits.length()) {
            ret.add(sb.toString());
            return;
        }

        for (int i = 0; i < dict[digits.charAt(index) - '2'].length; ++i){
            sb.setCharAt(index, dict[digits.charAt(index) - '2'][i]);
            letterCombinationsRecursive(digits, index + 1, sb, ret);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<>();
        if (digits.length() <= 0)
            return ret;
        StringBuilder sb = new StringBuilder(digits);
        letterCombinationsRecursive(digits, 0, sb, ret);

        return ret;
    }

    public List<String> letterCombinationsNonRecursive(String digits) {
        List<String> ret = new ArrayList<>();
        if (digits.length() <= 0)
            return ret;
        char [] tmp = new char [digits.length()];
        //prepare
        for (int i = 0; i < digits.length(); ++i) {
            tmp[i] = dict[digits.charAt(i) - '2'][0];
        }

        ret.add(new String(tmp));

        while (true) {
            //find next starting point.
            int i = digits.length();
            while (i > 0 && tmp[i-1] == dict[digits.charAt(i-1)-'2'][dict[digits.charAt(i-1)-'2'].length - 1]) {
                tmp[i-1] = dict[digits.charAt(i-1)-'2'][0];
                --i;
            }
            if (i == 0)
                break;
            ++tmp[i-1];
            ret.add(new String(tmp));
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
