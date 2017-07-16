package com.l1337.l153;


public class Solution {

    public int findMin(int[] nums) {
        if (nums.length < 1)
            return 0;
        int left = 0, right = nums.length - 1;
        while (left < right)
        {
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[right])
            {
                return nums[left];
            }
            else
            {
                if (nums[mid] > nums[right])
                {
                    left = mid + 1;
                }
                else
                {
                    // nums[left] > nums[right] && nums[right] > nums[mid]
                    // => left != mid...
                    if (nums[mid - 1] > nums[mid])
                        return nums[mid];
                    else
                        right = mid - 1;
                }
            }

        }

        return nums[left];
    }

    public int findMinII(int[] nums) {
        if (nums.length < 1)
            return 0;
        int left = 0, right = nums.length - 1;
        while (left < right)
        {
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[right])
            {
                return nums[left];
            }
            else if (nums[left] == nums[right])
            {
                while (left + 1 <= right && nums[left] == nums[right])
                    ++left;
            }
            else
            {
                // nums[left] > nums[right]
                if (nums[mid] > nums[right])
                {
                    left = mid + 1;
                }
                else
                {
                    // nums[left] > nums[right] && nums[right] > nums[mid]
                    // => left != mid...
                    if (nums[mid - 1] > nums[mid])
                        return nums[mid];
                    else
                        right = mid - 1;
                }
            }

        }

        return nums[left];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
