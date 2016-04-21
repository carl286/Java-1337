package com.uva.threeNPlusOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Ke.Liu on 7/18/16.
 */
public class Main {

    int cycle;
//    public long f(long n) {
    public int f(int n) {
        ++cycle;
//        System.out.println(n);
        if (n == 1)
            return n;
        if ((n & 0x01) != 0)
            return f(3*n+1);
        else
            return f(n/2);
    }

    public int getF(int n) {
        cycle = 0;
        while (n != 1) {
            n = f(n);
        }
        return cycle;
    }


    public int getMaxHelper(HashMap<Integer, Integer> map, int i) {
        if (map.containsKey(i))
            return map.get(i);

        int ret;
        if (i == 1)
            ret = 1;
        else if ((i & 0x01) != 0)
            ret = 1 + getMaxHelper(map, 3*i+1);
        else
            ret = 1 + getMaxHelper(map, i/2);
        map.put(i, ret);
        return ret;
    }
    public int getMaxForRange(int i, int j) {
//        assume all inputs are valid.
        HashMap<Integer, Integer> map = new HashMap<>();
        int ret = 1;
        for (int k = i; k <= j; ++k)
            ret = Math.max(ret, getMaxHelper(map, k));
        return ret;
    }
    public static void main(String [] args) {
        Main t = new Main();
//        int max = 1;
//        for (int i = 1; i <= 10; ++i)
//            max = Math.max(max, t.getF(i));
//        System.out.println(max);

//        System.out.println(t.getMaxForRange(1,10));

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input;
            while((input=br.readLine())!=null && input.length() != 0){
                String [] inputs = input.split("\\s+");
                int i = Integer.valueOf(inputs[0]);
                int j = Integer.valueOf(inputs[0]);
                if (i < j)
                    System.out.println(i + " " + j + " "+t.getMaxForRange(i,j));
                else
                    System.out.println(j + " " + i +" "+t.getMaxForRange(j,i));
            }

        }catch(IOException io){
            io.printStackTrace();
        }
    }
}
