package com.l1337.l1357;


import java.util.*;

public class Solution {

    private int getsteps(int x)
    {
        int steps = 0;
        while (x != 1)
        {
            if ((x & 0x01) == 0)
            {
                //even
                x = x >> 1;
            }
            else
            {
                x = (x << 1) + x  + 1;
            }
            ++steps;
        }
        return steps;
    }
    public int getKth(int lo, int hi, int k) {
        int [][] data = new int [hi - lo + 1][2];
        for (int i = lo; i <= hi; ++i)
        {
            int index = i - lo;
            data[index][0] = getsteps(i);
            data[index][1] = i;
        }

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return a[0] - b[0];
                else
                    return a[1] - b[1];
            }
        });

        return data[k-1][1];
    }

    private int getsteps(int x, Map<Integer, Integer> map)
    {
        int xx=x;
        int steps = 0;
        while (x != 1 && !map.containsKey(x))
        {
            if ((x & 0x01) == 0)
            {
                //even
                x = x >> 1;
            }
            else
            {
                x = (x << 1) + x  + 1;
            }
            ++steps;
        }
        map.put(xx, steps + map.get(x));
        return map.get(xx);
    }
    public int getKthII(int lo, int hi, int k) {
        int [][] data = new int [hi - lo + 1][2];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,0);
        for (int i = lo; i <= hi; ++i)
        {
            int index = i - lo;
            data[index][0] = getsteps(i, map);
            data[index][1] = i;
        }

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return a[0] - b[0];
                else
                    return a[1] - b[1];
            }
        });

        return data[k-1][1];
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.getKthII(7,11,4));
    }
}
