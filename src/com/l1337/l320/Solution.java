package com.l1337.l320;


import java.util.ArrayList;
import java.util.List;

public class Solution {

//    Write a function to generate the generalized abbreviations of a word.
//            Example:
//    Given word = "word", return the following list (order does not matter):
//            ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

    //any abbreviation is generated by character count?, min is 1???
    // can't have numbers consective??? like 22??? Because we can switch 22 to 4....
//    https://fxrcode.gitbooks.io/leetcodenotebook/content/Leetcode_Medium/generalized_abbreviation.html

//    https://segmentfault.com/a/1190000004187690
//    https://leetcode.com/discuss/75443/straight-forward-methods-with-backtracking-divide-conquer




    //use to set mask????
    //Think about your function API...
    List<String> generateAbbreviations(String word, int acc) {
        List<String> ret = new ArrayList<>();
//        if (word.length() == 0)
//            return ret;

        if (word.length() == 0) {
            if (acc == 0)
                ret.add("");
            else
                ret.add(Integer.toString(acc));
            return ret;
        }

        /*
        if (word.length() == 1) {
            if (acc == 0)
                ret.add(word);
            else
                ret.add(Integer.toString(acc) + word);
            ret.add(Integer.toString(acc+1));
            return ret;
        }
        */


        String tmp = acc == 0 ? word.substring(0,1) : Integer.toString(acc) + word.substring(0,1);
        //Take current pos as ch
        List<String> sub = generateAbbreviations(word.substring(1), 0);
        int size = sub.size();
        for (int k = 0; k < size; ++k)
            sub.set(k, new String(tmp+sub.get(k)));
        ret.addAll(sub);

        //Take current pos as integer
        sub = generateAbbreviations(word.substring(1), acc+1);
        ret.addAll(sub);

        return ret;
    }
    public List<String> generateAbbreviations(String word) {
        return generateAbbreviations(word,0);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        for (String ss : s.generateAbbreviations("dictionary"))
            System.out.println(ss);
    }
}
