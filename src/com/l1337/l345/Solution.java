package com.l1337.l345;

//https://leetcode.com/problems/reverse-vowels-of-a-string/
//345. Reverse Vowels of a String

public class Solution {
    private boolean isVowel(char c) {
        switch (c) {
            case 'a':
            case 'A':
            case 'e':
            case 'E':
            case 'i':
            case 'I':
            case 'o':
            case 'O':
            case 'u':
            case 'U':
                return true;
        }
        return false;
    }
    private void swap(char [] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    public String reverseVowels(String s) {
        char [] array = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isVowel(s.charAt(i)))
                ++i;
            while (i < j && !isVowel(s.charAt(j)))
                --j;
            if (i < j) {
                swap(array,i++,j--);
            }
        }

        return new String(array);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.reverseVowels("Hello World"));
        System.out.println(s.reverseVowels("aio"));
    }
}
