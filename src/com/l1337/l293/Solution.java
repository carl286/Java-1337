package com.l1337.l293;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    /*
    https://www.lintcode.com/problem/flip-game/

    Description
You are playing the following Flip Game with your friend: Given a string that contains only two characters: + and -, you can flip two consecutive "++" into "--", you can only flip one time. Please find all strings that can be obtained after one flip.

Write a program to find all possible states of the string after one valid move.

Example
Example1

Input:  s = "++++"
Output:
[
  "--++",
  "+--+",
  "++--"
]
Example2

Input: s = "---+++-+++-+"
Output:
[
	"---+++-+---+",
	"---+++---+-+",
	"---+---+++-+",
	"-----+-+++-+"
]
     */


    public List<String> generatePossibleNextMoves(String s) {
        // write your code here
        List<String> ret = new ArrayList<>();
        char [] data = s.toCharArray();
        for(int i = 1; i < s.length(); ++i)
        {
            if (s.charAt(i) == '+' && s.charAt(i-1) == '+')
            {
                data[i] = '-';
                data[i-1] = '-';
                ret.add(new String(data));
                data[i] = '+';
                data[i-1] = '+';
            }
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        String input = "---+++-+++-+";
        List<String> ret = s.generatePossibleNextMoves(input);
        for(int i = 0; i < ret.size(); ++i)
            System.out.println(ret.get(i));
    }
}
