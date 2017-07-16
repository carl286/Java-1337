package com.googlePrep;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//https://leetcode.com/discuss/interview-question/619524/Google-or-Onsite-or-Random-Generator
public class RandomGenerator {

    private Random rand;
    private int n;
    private Map<Integer, Integer> map;
    public RandomGenerator(int n) {
        rand = new Random();
        map = new HashMap<>();
        this.n = n;
    }

    public int generate() {
        if (this.n <= 0)
            return -1;

        int random = rand.nextInt(n--);

        int ret = map.getOrDefault(random, random);

        //overwrite value
        map.put(random, map.getOrDefault(n, n));

        return ret;
    }

    public static void main(String [] args)
    {
        /*
        var rg = new RandomGenerator(3);
rg.generate(); // 0
rg.generate(); // 2
rg.generate(); // 1
rg.generate(); // -1
         */
        int n = 10;
        var rg = new RandomGenerator(n);
        for(int i = 0; i < n; ++i)
            System.out.println(rg.generate());
        System.out.println(rg.generate());
        System.out.println(rg.generate());
        System.out.println(rg.generate());
    }
}
