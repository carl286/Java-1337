package com.l1337.l453;


public class Solution {

    //TIME OUT
    public int minMoves(int[] nums) {
        if (nums.length == 1)
            return 0;
        if (nums.length == 2)
            return Math.abs(nums[0] - nums[1]);
       int counter = 0;
        while (true) {
            boolean isAllEqual = true;
            int maxVal = ++nums[0];
            int maxIndex = 0;
            for (int i = 1; i < nums.length; ++i) {
                if (maxVal < ++nums[i]) {
                    maxVal = nums[i];
                    maxIndex = i;
                }
                if (nums[i] != nums[i-1])
                    isAllEqual = false;
            }

            if (isAllEqual) {
                break;
            } else {
                --nums[maxIndex];
                counter++;
            }
        }
        return counter;
    }

    public int minMoves2(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            if (i < min)
                min = i;
        }
        int ret = 0;
        for (int i : nums)
            ret += (i - min);
        return ret;
    }
//    正确的解法相当的巧妙，需要换一个角度来看问题，其实给n-1个数字加1，效果等同于给那个未被选中的数字减1，比如数组[1，2，3], 给除去最大值的其他数字加1，变为[2，3，3]，我们全体减1，并不影响数字间相对差异，变为[1，2，2]，这个结果其实就是原始数组的最大值3自减1，那么问题也可能转化为，将所有数字都减小到最小值，这样难度就大大降低了，我们只要先找到最小值，然后累加每个数跟最小值之间的差值即可，参见代码如下：
//    我们也可以求出数组的数字之和sum，然后用sum减去最小值和数组长度的乘积，也能得到答案：
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.minMoves(new int[]{1,2,3,4}));
    }
}
