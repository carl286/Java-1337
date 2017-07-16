package com.l1337.l640;


public class Solution {

     int [] parse(String s) {
        int [] ret = new int [2];
        //ret[0] a
        //ret[1] b
        //ax + b

        boolean isPlus = true;
        String splits [] = s.split("(?=[-+])");

        for (int i = 0; i < splits.length; ++i) {
            if (splits[i].charAt(splits[i].length()-1) == 'x') {
                if (splits[i].length() == 1)
                    ret[0] += 1;
                else if (splits[i].charAt(0) == '+') {
                    if (splits[i].length() == 2)
                        ret[0] += 1;
                    else
                        ret[0] += Integer.parseInt(splits[i].substring(0, splits[i].length()-1));
                } else if (splits[i].charAt(0) == '-') {
                    if (splits[i].length() == 2)
                        ret[0] -= 1;
                    else
                        ret[0] += Integer.parseInt(splits[i].substring(0, splits[i].length()-1));
                } else {
                    ret[0] += Integer.parseInt(splits[i].substring(0, splits[i].length()-1));
                }
            } else {
                ret[1] += Integer.parseInt(splits[i]);
            }
        }


        return ret;
    }
    public String solveEquation(String equation) {
        String splits [] = equation.split("=");
        //assume no extra space here...
        int [] left = parse(splits[0]);
        int [] right = parse(splits[1]);

        if (left[0] == right[0]) {
            if (left[1] == right[1])
                return "Infinite solutions";
            else
                return "No solution";
        }

        return "x=" + Integer.toString( (right[1] - left[1]) / (left[0] - right[0]));
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.solveEquation("x+5-3+x=6+x-2"));
//        int [] test = s.parse(new String("x+5-3-x"));
//        for (int i = 0; i < test.length; ++i)
//            System.out.print(test[i] + "\t");
////        System.out.println(Integer.parseInt("+1+x"));
    }
}
