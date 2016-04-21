package com.l1337.l330;


public class Solution {

//    330. Patching Array
//    https://leetcode.com/problems/patching-array/
//    https://leetcode.com/submissions/detail/57388710/

//    然后参考了discuss的思路：https://leetcode.com/discuss/82822/solution-explanation
//    就是用known_sum表示已知的连续和为[1,known_sum)，有了这个表示那就简单了：
//    nums[i] <= known_sum，更新已知范围为：[1,known_sum + nums[i] )
//    nums[i] >  known_sum,  添加known_sum进数组才能达到最大的范围，所以已知范围更新为：[1,known_sum *2  )


//    我们定义一个变量miss，用来表示[0,n]之间最小的不能表示的值，那么初始化为1，为啥不为0呢，因为n=0没啥意义，直接返回0了
// 那么此时我们能表示的范围是[0, miss)，表示此时我们能表示0到miss-1的数
// 如果此时的num <= miss，那么我们可以把我们能表示数的范围扩大到[0, miss+num)
// 如果num>miss，那么此时我们需要添加一个数，为了能最大限度的增加表示数范围
// 我们加上miss它本身，以此类推直至遍历完整个数组，我们可以得到结果。下面我们来举个例子说明：
//    给定nums = [1, 2, 4, 11, 30], n = 50，我们需要让[0, 50]之间所有的数字都能被nums中的数字之和表示出来。
//    首先使用1, 2, 4可能表示出0到7之间的所有数，表示范围为[0, 8)，但我们不能表示8，因为下一个数字11太大了，所以我们要在数组里加上一个8，此时能表示的范围是[0, 16)，那么我们需要插入16吗，答案是不需要，因为我们数组有1和4，可以组成5，而下一个数字11，加一起能组成16，所以有了数组中的11，我们此时能表示的范围扩大到[0, 27)，但我们没法表示27，因为30太大了，所以此时我们给数组中加入一个27，那么现在能表示的范围是[0, 54)，已经满足要求了，我们总共添加了两个数8和27，所以返回2即可。
public int minPatches(int[] nums, int n) {
    long miss = 1;
    int add = 0;
    int i = 0;
    while (miss <= n) {
        if (i < nums.length && nums[i] <= miss){
            miss += nums[i++];
        } else {
            miss += miss;
            add += 1;
        }
    }

    return add;
}

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
