package com.l1337.l464;


public class Solution {

//    http://www.cnblogs.com/grandyang/p/6103525.html
//    https://leetcode.com/problems/can-i-win/discuss/95277/java-solution-using-hashmap-with-detailed-explanation
//    https://leetcode.com/problems/can-i-win/discuss/95283/brute-force-and-memoization
    /*
    我们首先来看如果给定的数字范围大于等于目标值的话，直接返回true。如果给定的数字总和小于目标值的话，说明谁也没法赢，返回false。然后我们进入递归函数，首先我们查找当前情况是否在哈希表中存在，有的话直接返回即可。我们使用一个整型数按位来记录数组中的某个数字是否使用过，我们遍历所有数字，将该数字对应的mask算出来，如果其和used相与为0的话，说明该数字没有使用过，我们看如果此时的目标值小于等于当前数字，说明已经赢了，或者我们调用递归函数，如果返回false，说明也是第一个人赢了。为啥呢，因为当前我们已经选过数字了，此时就该对第二个人调用递归函数，只有他的结果是false，我们才能赢，所以此时我们标记true，返回true。如果遍历完所有数字，我们标记false，返回false
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal)
            return true;
        int maxSum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (maxSum < desiredTotal)
            return false;

        //otherwise

        return true;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
