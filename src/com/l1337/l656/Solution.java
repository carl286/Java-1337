package com.l1337.l656;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

//    http://www.cnblogs.com/grandyang/p/8183477.html
//    https://blog.csdn.net/magicbean2/article/details/79175717
    /*
    Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer Bdenotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.

Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.

If there are multiple paths with the same cost, return the lexicographically smallest such path.

If it's not possible to reach the place indexed N then you need to return an empty array.

Example 1:

Input: [1,2,4,-1,2], 2
Output: [1,3,5]


Example 2:

Input: [1,2,4,-1,2], 1
Output: []


Note:

Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
Length of A is in the range of [1, 1000].
B is in the range of [1, 100].

     */
    public List<Integer> cheapestJump(int[] A, int B) {
        List<Integer> ret = new ArrayList<>();
        if (B <= 0 || A.length <= 1 || A[A.length-1] == -1)
            return ret;
        //also think about the case when B is crazy big .enough
        int [] parent = new int[A.length];
        int [] cost = new int[A.length];
        int [] len = new int[A.length];

        cost[0] = 0;
        parent[0] = -1; //means it has no parent....
        for (int i = 1; i < A.length; ++i) {
            //fill parent, cost
            cost[i] = -1;//means unreachable....
            parent[i] = -1;
            if (A[i] >= 0) {
                for (int j = i - 1; j >= 0 && i - j <= B; --j) {
                    if (cost[j] >= 0 && (cost[j] + A[j] < cost[i] || cost[i] == -1 || (cost[j] + A[j] == cost[i] && len[i] < len[j] + 1))) {
                        cost[i] = cost[j] + A[j];
                        parent[i] = j;
                        len[i] = len[j] + 1;
                    }
                }
            }
        }

        if (cost[A.length-1] >= 0) {
            //it's reachable....
            int k = A.length - 1;
            while (k >= 0) {
                ret.add(k + 1);
                k = parent[k];
            }
            Collections.reverse(ret);
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        int [] A = new int [] {1,2,4,-1,2};
//        int B = 2;
        int [] A = new int [] {0,0,0};
        int B = 2;

        /*
        A = [0, 0, 0], B = 2
        上面这个例子得到的结果是[1, 2, 3]，是字母顺序最小的路径，而相同的cost路径[1, 3]，就不是字母顺序最小的路径，
         */

        /*
        下面这种方法是正向遍历的解法，正向跳的话就需要另一个数组len，len[i]表示从开头到达位置i的路径的长度，如果两个路径的cost相同，那么一定是路径长度大的字母顺序小
         */
        //This problem is too tricky...

        List<Integer> list = s.cheapestJump(A, B);
        for (int i = 0; i < list.size(); ++i)
            System.out.println(list.get(i));
    }
}
