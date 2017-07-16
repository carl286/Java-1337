package com.l1337.l1248;


public class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        // k == 1, count odd numbers


        return numberOfSubarraysAtLeast(nums, k) - numberOfSubarraysAtLeast(nums, k - 1);
    }

    public int numberOfSubarraysAtLeast(int[] nums, int k) {
        if (k < 0)
            return nums.length * (nums.length - 1) / 2;

        //assume k >= 0 here
        int ret = 0;
        for (int start = 0, cur = 0, acc = 0; cur < nums.length; ++cur)
        {
            if ((nums[cur] % 2) == 1)
            {
                ++acc;
            }

            while (acc > k)
            {
                if ((nums[start++] % 2) == 1)
                {
                    --acc;
                }
            }
            ret += (cur -start + 1);
        }

        return ret;
    }

    /*
    Actually it's same as three pointers.
Though we use count to count the number of even numebers.
     */
    public int numberOfSubarrays2(int[] A, int k) {
        int res = 0, i = 0, count = 0, n = A.length;
        for (int j = 0; j < n; j++) {
            if (A[j] % 2 == 1) {
                --k;
                count = 0;
            }
            while (k == 0) {
                k += A[i++] & 1;
                ++count;
            }
            res += count;
        }
        return res;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.numberOfSubarrays(new int [] {2,2,2,1,2,2,1,2,2,2}, 2));
    }
}
