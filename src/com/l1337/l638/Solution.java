package com.l1337.l638;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private boolean isComplete(List<Integer> needs) {
        for (int i = 0; i < needs.size(); ++i)
            if (needs.get(i) != 0)
                return false;
        return true;
    }

    private boolean isPossible(List<Integer> special, List<Integer> needs) {
        for (int i = 0; i < needs.size(); ++i) {
            if (needs.get(i) < special.get(i))
                return false;
        }

        return true;
    }

    private int dfs(List<List<Integer>> special, List<Integer> needs) {
        if (isComplete(needs))
            return 0;

        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < special.size(); ++i) {
            if (isPossible(special.get(i), needs)) {
                for (int k = 0; k < needs.size(); ++k) {
                    needs.set(k, needs.get(k) - special.get(i).get(k));
                }

                int ret2 = dfs(special, needs);
                if (ret2 != Integer.MAX_VALUE) {
                    ret = Math.min(ret, special.get(i).get(needs.size()) + ret2);
                }

                for (int k = 0; k < needs.size(); ++k) {
                    needs.set(k, needs.get(k) + special.get(i).get(k));
                }
            }
        }

        return ret;
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (needs.size() == 0)
            return 0;

        //assums no violations...
        //convert prices into specials
        for (int i = 0; i < price.size(); ++i) {
            List<Integer> newSpecial = new ArrayList<>(price.size() + 1);
            for (int k = 0; k < price.size() + 1; ++k)
                newSpecial.add(0);
            newSpecial.set(price.size(), price.get(i));
            newSpecial.set(i , 1);
            special.add(newSpecial);
        }

        return dfs(special, needs);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        List<Integer> price = Arrays.asList(2,5);
////        List<List<Integer>> special = Arrays.asList(Arrays.asList(3,0,5), Arrays.asList(1,2,10));
//        List<List<Integer>> special = new ArrayList<>();
//        special.add(Arrays.asList(3,0,5));
//        special.add(Arrays.asList(1,2,10));
//        List<Integer> needs = Arrays.asList(3,2);
        List<Integer> price = Arrays.asList(2,3,4);
//        List<List<Integer>> special = Arrays.asList(Arrays.asList(3,0,5), Arrays.asList(1,2,10));
        List<List<Integer>> special = new ArrayList<>();
        special.add(Arrays.asList(1,1,0,4));
        special.add(Arrays.asList(2,2,1,9));
        List<Integer> needs = Arrays.asList(1,2,1);

        System.out.println(s.shoppingOffers(price,
                special,
                needs));
    }
}
