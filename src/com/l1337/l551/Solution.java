package com.l1337.l551;


public class Solution {

    public boolean checkRecord(String s) {
//        char pre = 'P';
        boolean containsA = false;
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case 'A':
                    if (containsA)
                        return false;
                    else
                        containsA = true;
//                    pre = 'A';
                    count = 0;
                    break;
                case 'L':
                    ++count;
                    if (count > 2)
                        return false;
//                    pre = 'L';
                    break;
                default:
//                    pre = 'P';
                    count = 0;
            }
        }

        return true;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
