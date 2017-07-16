package com.l1337.l204;


public class Solution {


//    https://leetcode.com/problems/count-primes/discuss/57636/My-JAVA-Solution
    public int countPrimes(int n) {
        /*
        if (n <= 2)
			return 0;

		boolean[] isPrime = new boolean[n];
   for (int i = 2; i < n; i++) {
      isPrime[i] = true;
   }
   // Loop's ending condition is i * i < n instead of i < sqrt(n)
   // to avoid repeatedly calling an expensive function sqrt().
   for (int i = 2; i * i < n; i++) {
      if (!isPrime[i]) continue;
      for (int j = i * i; j < n; j += i) {
         isPrime[j] = false;
      }
   }
   int count = 0;
   for (int i = 2; i < n; i++) {
      if (isPrime[i]) count++;
   }
   return count;
         */
        if (n <= 1)
            return 0;
        int tmp [] = new int [n+1];
        int mid = (int) Math.sqrt(n);

        tmp[0] = 1;
        tmp[1] = 1;
        int i = 2;
        while (i <= mid) {
            if (tmp[i] == 0) {
                //start from whom
                int k = i;
                while (true) {
                    int prod = k++ * i;
                    if (prod <= n)
                        tmp[prod] = 1;
                    else
                        break;
                }
            }
            ++i;
        }
        int total = 0;
        i = 0;
        while (i < tmp.length) {
            if (tmp[i] == 0)
                ++total;
            ++i;
        }

        return total;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int n = 4;
        System.out.println(s.countPrimes(n));
    }
}
