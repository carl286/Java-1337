package com.examples.Test;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

final class Pair<X,Y> {
    private X fst;
    private Y snd;
    public Pair(X fst, Y snd) {this.fst=fst; this.snd=snd;}
    public X getFirst() { return fst; }
    public Y getSecond() { return snd; }
}

public class Solution {

    public static void test() {

        int n = 4;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j)
                System.out.format("%8d\t", i*j);
            System.out.println();
        }
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.printf("%tc\n", new Date());
//        System.out.printf("%1$s %2$tB %2$te, %2$tY", "Date:", new Date());
        int [] array = {4,53,2,5,4};
        System.out.println(Double[].class.getName());
        Object [] test = new Object[3];
//        Double [] tt = (Double []) test;
        test[2] = new Double(3.0);
    }
}
