package com.l1337.l364;

import com.leetcode.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/*
Nested List Weight Sum II

Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */

//https://discuss.leetcode.com/topic/49049/java-standard-bfs-rewriting-stefanpochmann-s-with-extra-interpretation
//http://www.cnblogs.com/grandyang/p/5615583.html
//https://discuss.leetcode.com/topic/49049/java-standard-bfs-rewriting-stefanpochmann-s-with-extra-interpretation
//https://discuss.leetcode.com/topic/49041/no-depth-variable-no-multiplication
public class Solution {



    //Unfornately,,,,, BELOW is WRONG.... SO WRONG.....
    int depthSumInverse(List<NestedInteger> nestedList) {
        List<Integer> depthSum = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
            depthSumInverseHelper(ni, 0, depthSum);
        }
        int total = 0;
        for (int i = 0; i < depthSum.size(); ++i) {
            total += (depthSum.size() - i) * depthSum.get(i);
        }
        return total;
    }

    void depthSumInverseHelper(NestedInteger ni, int depth, List<Integer> depthSum) {
        if (depth >= depthSum.size()) {
            //in this way, we can save the trouble of resize....
            depthSum.add(0);
        }

        if (ni.isInteger()) {
            depthSum.set(depth, depthSum.get(depth) + ni.getInteger());
        } else {
            for (NestedInteger nni : ni.getList()) {
                depthSumInverseHelper(nni, depth+1, depthSum);
            }
        }
    }
    /*
    //Below is wrong
    return depthSumInverseHelper(nestedList)[0];
    int [] depthSumInverseHelper(List<NestedInteger> nestedInteger) {
        int [] ret = new int [2];//ret[0] is the total, ret[1] is the depth
        ret[1] = 1;
        for (NestedInteger ni : nestedInteger) {
            if (!ni.isInteger()) {
                int [] subRet = depthSumInverseHelper(ni.getList());
                ret[0] += subRet[0];
                ret[1] = Math.max(ret[1], 1 + subRet[1]);
            }
        }
        for (NestedInteger ni : nestedInteger) {
            if (ni.isInteger()) {
                ret[0] += ret[1] * ni.getInteger();
            }
        }
        return ret;
    }
    */
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
