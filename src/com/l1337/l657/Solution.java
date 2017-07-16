package com.l1337.l657;


public class Solution {

    public boolean judgeCircle(String moves) {
        int l = 0, u = 0;

        for (int i = 0; i < moves.length(); ++i) {
            switch (moves.charAt(i)) {
                case 'L':
                    ++l;
                    break;
                case 'R':
                    --l;
                    break;
                case 'U':
                    ++u;
                    break;
                case 'D':
                    --u;
                    break;
            }

        }

        return l == 0 && u == 0;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
