package com.l1337.l916;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> ret = new ArrayList<>();
        int base [] = new int [26];
        for(int i = 0; i < B.length; ++i)
        {
            int local [] = new int [26];
            for(int j = 0; j < B[i].length(); ++j)
            {
                int index = B[i].charAt(j)-'a';
                ++local[index];
                if (base[index] < local[index])
                    base[index] = local[index];
            }
        }

        for(int i = 0; i < A.length; ++i)
        {
            int [] anotherBase = getBase(A[i]);
            if (isCompatable(anotherBase, base))
                ret.add(A[i]);
        }
        return ret;
    }
    private int [] getBase(String a)
    {
        int [] ret = new int[26];
        for(int i = 0; i < a.length(); ++i)
            ++ret[a.charAt(i)-'a'];
        return ret;
    }
    private boolean isCompatable(int []a, int []b)
    {
        for(int i = 0; i < a.length; ++i)
            if (a[i] < b[i])
                return false;
        return true;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
