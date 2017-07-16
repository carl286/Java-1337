package com.l1337.l88;


public class Solution {

    // https://www.programcreek.com/2012/12/leetcode-merge-sorted-array-java/
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int l = m + n;
        while (l > 0 && m > 0 && n > 0) {
            if (nums2[n-1] > nums1[m-1])
            {
                nums1[--l] = nums2[--n];
            }
            else
            {
                nums1[--l] = nums1[--m];
            }
        }

        //this part is dummy...
        while (l > 0 && m > 0) {
            {
                nums1[--l] = nums1[--m];
            }
        }
        while (l > 0 && n > 0) {
                nums1[--l] = nums2[--n];
        }
    }
    
    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
