package com.l1337.l1346;


import java.util.HashSet;
import java.util.Set;

public class Solution {

    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int zeros = 0;
        for(int i: arr){
            set.add(i);
            if (i == 0)
                zeros++;
        }
        if (zeros >= 2)
            return true;
        for(int i : arr)
            if (i != 0 && set.contains(2*i))
                return true;

        return false;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
