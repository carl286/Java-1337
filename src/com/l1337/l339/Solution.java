package com.l1337.l339;


import com.l1337.l341.NestedInteger;

import java.util.List;

//339, Nested List Weight Sum
//Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
//        Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//        Example 1:
//        Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
//        Example 2:
//        Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
public class Solution {

    private int helper(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (int i = 0; i < nestedList.size(); ++i)
            if (nestedList.get(i).isInteger())
                sum += depth * nestedList.get(i).getInteger();
            else
                sum += helper(nestedList.get(i).getList(), 1 + depth);
        return sum;
    }
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
