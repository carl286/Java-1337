package com.l1337.l686;


public class Solution {

//    https://leetcode.com/problems/repeated-string-match/discuss/108084/C++-4-lines-O(m-*-n)-or-O(1)-and-KMP-O(m-+-n)-or-O(n)
//    https://leetcode.com/problems/repeated-string-match/discuss/108118/C++Java-Clean-Code
    public int repeatedStringMatch(String A, String B) {
        //both len(a) and len(b) >= 1
//        if (B.length() <= 2*A.length()-2) {
        if (!B.contains(A)) {
            if (A.indexOf(B) >= 0)
                return 1;

            if (B.length() > 2 *A.length() - 2)
                return -1;

            int index = (A + A).indexOf(B);
            if (index < 0)
                return -1;
            else if (index == 0)
                return 1;
            else
                return 2;
        }


            int first = B.indexOf(A);
            if (first < 0)
                return -1;

            //first must be largr or equal than 0.
            int count = 1;
            if (first > 0) {
                if (A.endsWith(B.substring(0, first)))
                    ++count;
                else
                    return -1;
            }

            first += A.length();
            while (first + A.length() < B.length()) {
                if (B.substring(first, first + A.length()).equals(A)) {
                    first += A.length();
                    ++count;
                } else {
                    return -1;
                }
            }

            if (first != B.length()) {
                if (A.startsWith(B.substring(first)))
                    ++count;
                else
                    return -1;
            }

            return count;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String A = "abcd", B = "abcdb";
        System.out.println(s.repeatedStringMatch(A,B));
    }
}
