package com.l1337.l179;


import java.util.*;

public class Solution {

    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i)
            list.add(Integer.toString(nums[i]));

        Collections.sort(list, new Comparator<String>(){
            @Override
            public int compare (String a, String b)
            {
                String c = a + b;
                String d = b + a;
                for (int i = 0; i < c.length(); ++i)
                    if (c.charAt(i) < d.charAt(i))
                        return 1;
                    else if (c.charAt(i) > d.charAt(i))
                        return -1;
                return 0;
            }
        });

        String tmp = String.join("", list);
        int zeros = 0;
        for (int i = 0; i < tmp.length(); ++i)
            if (tmp.charAt(i) == '0')
                ++zeros;
            else
                break;
        return zeros > 0 ? "0": tmp;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.largestNumber(new int [] {3,30,34,5,9}));
    }
}
