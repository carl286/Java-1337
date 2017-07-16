package com.l1337.l710;


import java.util.Arrays;
import java.util.Random;

public class Solution {

    private int [] data;
    private Random randomGenerator;

    private void swap(int i, int j) {
        if (i == j)
            return;

        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
    public Solution(int N, int[] blacklist) {
        randomGenerator = new Random();
//        data = new int [N];
//        for (int i = 0; i < data.length; ++i) {
//            data[i] = i;
//        }
//        size = data.length;

        data = new int [N - blacklist.length];
        int index = 0, i = 0, val = 0;
        Arrays.sort(blacklist);
        while (index < data.length) {
            while (i < blacklist.length && val == blacklist[i]) {
                ++val;
                ++i;
            }
            data[index++] = val++;
        }

//        for (int i = blacklist.length - 1; i >= 0; --i) {
//            if (blacklist[i] < N) {
//                if (data[size-1] != blacklist[i]) {
//                    swap(size-1, blacklist[i]);
//                }
//                --size;
//            }
//        }
    }

    public int pick() {
        return data[randomGenerator.nextInt(data.length)];
    }

//    https://leetcode.com/problems/random-pick-with-blacklist/discuss/146545/Simple-Java-solution-with-Binary-Search
//    https://leetcode.com/problems/random-pick-with-blacklist/discuss/144624/Java-O(B)-O(1)-HashMap
    public static void main(String [] args) {
//        RangeModule s = new RangeModule();
        System.out.println("Hello World");
    }
}
