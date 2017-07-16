package com.l1337.l424;


public class Solution {

//    https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91278/7-lines-C++
//    https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation

    public int characterReplacement(String s, int k) {
        if (s.length() == 0)
            return 0;
        if (k < 0) k = 0;
        int ret = 0;
        for (char c = 'A'; c <= 'Z'; ++c) {
            int begin = 0, cur = 0, diff = 0;
            while (cur < s.length()) {
                if (s.charAt(cur) == c) {
                    ++cur;
                } else {
                    ++diff;
                    ++cur;
                }

                if (diff > k) {
                    if (s.charAt(begin) != c)
                        --diff;
                    ++begin;
                }
                ret = Math.max(cur - begin, ret);
            }
        }

        return ret;
    }























    public int characterReplacementApril6_21(String s, int k) {
        int ret = 0, longest = 0, longestIndex = 0;
        int [] data = new int [26];
        for(int i = 0, start = 0; i < s.length(); ++i)
        {
            int index = s.charAt(i)-'A';
            ++data[index];
            if (longest < data[index])
            {
                longestIndex = index;
                longest = data[index];
            }

            int totalChs = i - start + 1;
            while (totalChs - longest > k)
            {
                index = s.charAt(start) - 'A';
                --data[index];
                ++start;
                --totalChs;
                if (longestIndex == index)
                {
                    //refresh index if necessary
                    for(int k2 = 0; k2 < data.length; ++k2)
                    {
                        if (data[k2] > longest)
                        {
                            longest = data[k2];
                            longestIndex = k2;
                        }
                    }
                }
            }
            ret = Math.max(ret, totalChs);
        }

        return ret;
    }

//    https://leetcode.com/problems/longest-repeating-character-replacement/discuss/278271/JavaC%2B%2BPython-Sliding-Window-O(N)
//    https://leetcode.com/submissions/detail/141988216/

    //read discussion...
    /*
    yes. while loop is not necessary. the explanation is not clear but the idea of sliding window method hits the point exactly.
we just maintain the window with a size that holds the maximum length of substring we have found so far
the key point is tracking the max frequency and compare the size of CURRENT largest window - maxFrequency with k
     */
//    https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        System.out.println(s.characterReplacement("AABABBA", 2));
    }
}
