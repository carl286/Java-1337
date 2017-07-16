package com.l1337.l93;


import com.l1337.common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    boolean isValid(String s)
    {
        if (s.length() <= 0 || s.length() > 3)
            return false;
        int val = Integer.parseInt(s);
        switch (s.length())
        {
            case 1:
                return true;
            case 2:
                return val >= 10;
            case 3:
                return val >= 100 && val < 256;
            default:
                return false;
        }
    }
    void helper(List<String> ret, int [] index, String s, int level)
    {
        if (level == index.length - 1)
        {
            String tmp = s.substring(index[level-1], index[level]);
            if (isValid(tmp))
            {
                //add result
                StringBuilder tooAdd = new StringBuilder();
                for (int i = 1; i < index.length; ++i)
                {
                    if (i == 1)
                    {
                        tooAdd.append(s.substring(index[i-1], index[i]));
                    }
                    else
                    {
                        tooAdd.append('.');
                        tooAdd.append(s.substring(index[i-1], index[i]));
                    }

                    s.substring(index[level-1], index[level]);
                }
                ret.add(tooAdd.toString());
            }
        }
        else
        {
           //expand
            for (int k = index[level-1] + 1; k < s.length() && (k - index[level-1] <= 3) && (s.length() - index[level-1]) >= 5 - level; ++k)
            {
                index[level] = k;
                //check validity to see if proceed
                String tmp = s.substring(index[level-1], index[level]);
                if (isValid(tmp))
                {
                    helper(ret, index, s, level + 1);
                }
            }
        }
    }
    public List<String> restoreIpAddresses(String s) {
        int [] index = new int [5];
        index[0] = 0;
        index[4] = s.length();

        List<String> ret = new ArrayList<>();

        helper(ret, index, s,1);
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String testInput = "25525511135";
        for (String ss : s.restoreIpAddresses(testInput))
        {
            System.out.println(ss);
        }
    }
}
