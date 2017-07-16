package com.l1337.l384;


import java.util.Random;

public class Solution {

    private int [] org;
    private Random random;
    public Solution(int[] nums) {
        org = nums;
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return org;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (org == null)
            return null;
        int [] ret = new int [org.length];
        System.arraycopy(org, 0, ret, 0, ret.length);

        for (int i = ret.length - 1; i >= 0; --i) {
            int selectedIndex = random.nextInt(1+i);
            int tmp = ret[selectedIndex];
            ret[selectedIndex] = ret[i];
            ret[i] = tmp;
        }
        return ret;
    }

    public static void main(String [] args) {
//        Excel s = new Excel();
//        System.out.println("Hello World");
    }
}
