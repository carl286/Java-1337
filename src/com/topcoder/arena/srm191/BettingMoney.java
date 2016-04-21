package com.topcoder.arena.srm191;

/**
 * Created by Ke.Liu on 7/27/16.
 */

//https://community.topcoder.com/stat?c=problem_statement&pm=2297&rd=4775
public class BettingMoney {
    private final int SCALE = 100;
    int moneyMade(int[] amounts, int[] centsPerDollar, int finalResult) {
        long total = 0;
        for (int i = 0; i < amounts.length; ++i) {
            total += amounts[i]*SCALE;
        }
        total -= amounts[finalResult]*(SCALE + centsPerDollar[finalResult]);
        return (int)total;
    }
}
