package com.l1337.l744;


public class Solution {

    public char nextGreatestLetter(char[] letters, char target) {
        int left = -1, right = letters.length;
        while (left + 1 != right) {
            int mid = ((right - left) >> 1) + left;

            if (letters[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return letters[right % letters.length];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.nextGreatestLetter(new char[] {'c','f','j'}, 'a'));
    }
}
