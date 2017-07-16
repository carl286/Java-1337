package com.l1337.l26;


import com.l1337.common.ListNode;

public class Solution {

    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 1)
            return length;

        int ret = 0, cur = 1;
        do {
            while (cur < length && nums[ret] == nums[cur]) {
                ++cur;
            }
            if (cur < length)
                nums[++ret] = nums[cur];

        } while (cur < length);

        return ++ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();

        System.out.println("Hello World");
    }
}
