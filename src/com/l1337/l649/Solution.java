package com.l1337.l649;


import java.util.LinkedList;
import java.util.Queue;

public class Solution {

//    https://leetcode.com/problems/dota2-senate/discuss/105858/JavaC++-Very-simple-greedy-solution-with-explanation
    /*
    Each senate R must ban its next closest senate D who is from another party, or else D will ban its next senate from R's party.
     */

//    https://leetcode.com/problems/dota2-senate/discuss/105879/Python-Straightforward-with-Explanation
//    https://leetcode.com/problems/dota2-senate/discuss/105860/C++-O(n)-solution
    //you don't need a general formula, you need a way to simulate the situations....
    public String predictPartyVictory(String senate) {
        Queue<Integer> q1 = new LinkedList<Integer>(), q2 = new LinkedList<Integer>();
        int n = senate.length();
        for(int i = 0; i<n; i++){
            if(senate.charAt(i) == 'R')q1.add(i);
            else q2.add(i);
        }
        while(!q1.isEmpty() && !q2.isEmpty()){
            int r_index = q1.poll(), d_index = q2.poll();
            if(r_index < d_index)q1.add(r_index + n);
            else q2.add(d_index + n);
        }
        return (q1.size() > q2.size())? "Radiant" : "Dire";
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
