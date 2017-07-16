package com.l1337.l118;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        if (numRows > 0)
        {
            //init
            ret.add(new ArrayList<>());
            ret.get(0).add(1);

            while (--numRows > 0)
            {
                ret.add(new ArrayList<>(ret.get(ret.size() - 1)));
                List<Integer> l = ret.get(ret.size() - 1);

                for (int i = l.size() - 1; i > 0; --i)
                    l.set(i, l.get(i) + l.get(i-1));
                l.add(1);
            }
        }


        return ret;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new ArrayList<>();
        while (rowIndex-- >= 0)
        {
            for (int i = ret.size() - 1; i > 0; --i)
                ret.set(i, ret.get(i) + ret.get(i-1));
            ret.add(1);
        }


        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
