package com.l1337.l4;


public class Solution {

    //length1 >= 0, length2 >= 0, k >= 1
    public int findK(int [] nums1, int start1, int length1, int [] nums2, int start2, int length2, int k)
    {
        if (length1 == 0)
            return nums2[start2 + k - 1];
        else if (length2 == 0)
            return nums1[start1 + k - 1];

        int midIndex1 = start1 + length1 / 2, leftRemaining1 = length1 / 2, rightRemaining1 = length1 - length1/2 -1;
        int midIndex2 = start2 + length2 / 2, leftRemaining2 = length2 / 2, rightRemaining2 = length2 - length2/2 - 1;

        //we can do some additional check to ensure it's always going through the best strategy..
        if (nums1[midIndex1] < nums2[midIndex2])
        {
            if (k > 1 + leftRemaining1 + leftRemaining2) {
                return findK(nums1, start1 + 1 + leftRemaining1, length1 - 1 - leftRemaining1, nums2, start2, length2, k - 1 - leftRemaining1);
            } else {
                return findK(nums1,  start1,  length1, nums2,  start2, length2 - 1 - rightRemaining2, k);
            }
        }
        else
        {
            if (k > 1 + leftRemaining1 + leftRemaining2) {
                return findK(nums1, start1 , length1, nums2, start2 + 1 + leftRemaining2, length2 - 1 - leftRemaining2, k - 1 - leftRemaining2);
            } else {
                return findK(nums1,  start1,  length1 - 1 - rightRemaining1, nums2,  start2, length2, k);
            }
        }
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;

        int totalLength = length1 + length2;
        if (((length1 + length2) & 0x01) == 0)
        {
            return ((double)findK(nums1, 0, length1, nums2, 0, length2, (totalLength >> 1)) +
                    findK(nums1, 0, length1, nums2, 0, length2, (totalLength >> 1) + 1)) / 2.0;
        }
        else
        {
            //odd number
            return findK(nums1, 0, length1, nums2, 0, length2, (totalLength + 1) >> 1);

        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums1 = {1, 2};
        int [] nums2 = {1, 2, 3};

        System.out.println(s.findMedianSortedArrays(nums1, nums2));
    }
}
