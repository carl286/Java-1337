package com.l1337.l557;


public class Solution {

    private void swap(char [] array, int start, int end) {
        while (start < end) {
            char tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
            ++start;
            --end;
        }
    }

    public String reverseWords(String s) {
        char [] array = s.toCharArray();
        int i = 0;
        while (i < array.length) {
            while (i < array.length && Character.isSpaceChar(s.charAt(i))) {
                ++i;
            }
            int j = i;
            while (i < array.length && !Character.isSpaceChar(s.charAt(i))) {
                ++i;
            }
//            if (j < s.length()) {
                swap(array, j, i - 1);
//            }
        }

        return new String(array);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.reverseWords(new String("Let's take LeetCode contest")));
    }
}
