package com.l1337.l1358;


public class Solution {

//    https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/discuss/516977/JavaC%2B%2BPython-Easy-and-Concise
    public int numberOfSubstrings(String s) {
        int ret = 0;
        int tmp [] = new int [3];
        int masks = 0;
        int foundAllMasks = 0x7;
        for (int start = 0, cur = 0; cur < s.length(); ++cur)
        {
            int index = s.charAt(cur) - 'a';
            ++tmp[index];
            masks |= (1 << index);

            if ((masks ^ foundAllMasks) == 0)
            {
                while (tmp[s.charAt(start) - 'a'] > 1)
                {
                    index = s.charAt(start) - 'a';
                    --tmp[index];
//                    if (tmp[index] == 0)
//                        masks &= ~(1 << index);
                    ++start;
                }

                ret += (start + 1);
            }
        }

        return ret;
    }

    //sliding window...
    public int numberOfSubstringsII(String s) {
        int count[] = {0, 0, 0}, res = 0 , i = 0, n = s.length();
        for (int j = 0; j < n; ++j) {
            ++count[s.charAt(j) - 'a'];
            while (count[0] > 0 && count[1] > 0 && count[2] > 0)
                --count[s.charAt(i++) - 'a'];
            res += i;
        }
        return res;
    }

    /*
    Solution II
last will record the position of last occurrence.
If the ending index of substring is i,
the starting position should be on the left of min(last),
in order to have all 3 different letters.
And in this case, the starting index can be in range [0, min(last)],
min(last) + 1 in total.

Time O(N)
Space O(1)
     */
    public int numberOfSubstringsIII(String s) {
        int last[] = {-1, -1, -1}, res = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
            res += 1 + Math.min(last[0], Math.min(last[1], last[2]));
        }
        return res;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        //System.out.println(s.numberOfSubstrings("abcccccccccc"));
        System.out.println(s.numberOfSubstringsII("aaaaaa"));
        //System.out.println(s.numberOfSubstrings("abcabc"));
    }
}
