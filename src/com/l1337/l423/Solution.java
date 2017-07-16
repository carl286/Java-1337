package com.l1337.l423;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    private final String [] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    //    https://leetcode.com/problems/reconstruct-original-digits-from-english/description/
    public String originalDigits(String s) {
        Map<Character, Integer> map= new HashMap<>();
        for(char i = 'a'; i <= 'z'; ++i)
            map.put(i, 0);
        for (int i = 0; i < s.length(); ++i)
            map.put(s.charAt(i), 1 + map.get(s.charAt(i)));

        int mapper [] = new int [10];
        mapper[0] = map.get('z');
        mapper[2] = map.get('w');
        mapper[4] = map.get('u');
        mapper[6] = map.get('x');
        mapper[8] = map.get('g');
        mapper[1] = map.get('o') - mapper[0] - mapper[2] - mapper[4];
        mapper[3] = map.get('h') - mapper[8];
        mapper[5] = map.get('f') - mapper[4];
        mapper[7] = map.get('s') - mapper[6];
        mapper[9] = map.get('i') - mapper[6] - mapper[8] - mapper[5];


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mapper.length; ++i)
            if (mapper[i] > 0)
                sb.append(String.join("", Collections.nCopies(mapper[i], new String(Integer.toString(i)))));
        return sb.toString();
    }

    /**
     * trick。我们仔细观察这些表示数字的单词"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"，
     * 我们可以发现有些的单词的字符是独一无二的，
     * 比如z，只出现在zero中，
     * 还有w，u，x，g这四个单词，分别只出现在two，four，six，eight中，
     * 那么这五个数字的个数就可以被确定了，
     * 由于含有o的单词有zero，two，four，one，
     * 其中前三个都被确定了，那么one的个数也就知道了；
     * 由于含有h的单词有eight，three，其中eight个数已知，那么three的个数就知道了；
     * 由于含有f的单词有four，five，其中four个数已知，那么five的个数就知道了；
     * 由于含有s的单词有six，seven，其中six个数已知，那么seven的个数就知道了；
     * 由于含有i的单词有six，eight，five，nine，其中前三个都被确定了，那么nine的个数就知道了，
     * 知道了这些问题就变的容易多了，我们按这个顺序"zero", "two", "four", "six", "eight", "one", "three", "five", "seven", "nine"就能找出所有的个数了
     * @param args
     */
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.originalDigits("fviefuro"));
    }
}
