package com.l1337.l89;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        ret.add(0);
        int mask = 1;
        while ( n-- > 0)
        {
            for (int i = ret.size(); i > 0; --i) {
                ret.add(ret.get(i-1) | mask);
            }
            mask <<= 1;
        }

        return ret;
    }
    
    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
