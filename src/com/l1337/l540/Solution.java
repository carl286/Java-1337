package com.l1337.l540;


public class Solution {

//    https://leetcode.com/problems/single-element-in-a-sorted-array/discuss/100754/Java-Binary-Search-short-(7l)-O(log(n))-w-explanations
    //There is some trick you can use...
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] == nums[mid+1]) {
                if ((mid - l) % 2 == 0)
                    l = mid + 2;
                else
                    r = mid - 1;
            } else if (nums[mid] != nums[mid-1]){
                return nums[mid];
            } else {
                if ((r - mid) % 2 == 0)
                    r = mid - 2;
                else
                    l = mid + 1;
            }

        }

        return nums[l];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.singleNonDuplicate(new int [] {1,1,2,3,3,4,4,8,8}));
    }
}
