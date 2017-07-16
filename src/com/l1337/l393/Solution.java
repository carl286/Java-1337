package com.l1337.l393;


public class Solution {

    private static final int DATA_MASK = 0xFF;

    static class Node {
        int lower;
        int upper;
        Node (int l, int u) {
            this.lower = l;
            this.upper = u;
        }
    }
    static final Node [] LIMITS = {
            new Node(0b0, 0b01111111),
            new Node(0b11000000, 0b11011111),
            new Node(0b11100000, 0b11101111),
            new Node(0b11110000, 0b11110111)
    };

    static final Node DELIMETER = new Node(0b10000000, 0b10111111);

    public boolean validUtf8(int[] data) {
        int needs = 0;
        for (int d : data) {
            d &= DATA_MASK;
            if (needs > 0) {
                if (d < DELIMETER.lower || d > DELIMETER.upper)
                    return false;
                --needs;
            } else {
                int index = 0;
                while (index < LIMITS.length) {
                    if (LIMITS[index].upper >= d && LIMITS[index].lower <= d)
                        break;
                    ++index;
                }
                if (index >= LIMITS.length)
                    return false;
                else
                    needs = index;
            }
        }
        return needs == 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] data = {235, 140, 4};
        System.out.println(s.validUtf8(data));
    }
}
