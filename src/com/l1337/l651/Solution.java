package com.l1337.l651;


public class Solution {

//    http://www.cnblogs.com/grandyang/p/7448390.html
//    http://www.1point3acres.com/bbs/thread-289736-1-1.html
    public int maxA(int N) {
        int[] best = new int[N+1];
        for (int k = 1; k <= N; ++k) {
            best[k] = best[k-1] + 1;
            for (int x = 0; x < k-1; ++x)
                best[k] = Math.max(best[k], best[x] * (k-x-1));
        }
        return best[N];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        for (int k = 0; k < 10; ++k)
            System.out.println(s.maxA(7));
    }
}
