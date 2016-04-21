package com.l1337.flipGame;


import java.util.ArrayList;
import java.util.List;

public class Solution {


    //	Flip Game, 293
//	Problem Description:
//	You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
// you and your friend take turns to flip two consecutive "++" into "--".
// The game ends when a person can no longer make a move and therefore the other person will be the winner.
//	Write a function to compute all possible states of the string after one valid move.
//	For example, given s = "++++", after one move, it may become one of the following states:
//			[
//			"--++",
//			"+--+",
//			"++--"
//			]
//	If there is no valid move, return an empty list [].

    //Naive level...
    public List<String> generatePossibleNextMoves(String s) {
        List<String> ret = new ArrayList<>();

        int i = 0;
        while (i + 1< s.length()) {
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                ret.add(s.substring(0,i)+"--"+s.substring(i+2));
            }
            ++i;
            while (i + 1< s.length() && s.charAt(i) == '-')
                ++i;
        }
        return ret;
    }


    //	Flip Game II, 294
//	You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
// The game ends when a person can no longer make a move and therefore the other person will be the winner.
//	Write a function to determine if the starting player can guarantee a win.
//	For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
//	Follow up:
//	Derive your algorithm's runtime complexity.
    public boolean canWin(String s) {
        for (int i = 0; i + 1< s.length(); ++i) {
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                if (!canWin(s.substring(0,i)+"-"+s.substring(i+2)))
                    return true;
            }
        }
        return false;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        for (String ss : s.generatePossibleNextMoves("--++")) {
//            System.out.println(ss);
//        }

        System.out.println(s.canWin("+++++"));
    }
}

//	https://leetcode.com/discuss/64350/short-java-%26-ruby?show=64350#q64350
//The idea is very straightforward: if you can make s non-winnable by a move, then you will win.
//	https://leetcode.com/discuss/64344/theory-matters-from-backtracking-128ms-to-dp-0ms
//You can use HashMap to sotre historical results...
