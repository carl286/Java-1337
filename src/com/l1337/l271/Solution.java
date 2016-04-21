package com.l1337.l271;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Problem Description:

Design an algorithm to encode a list of strings to a string.
The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:

vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.
Note:
The string may contain any possible characters out of 256 valid ascii characters.
Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 */
public class Solution {

    private static char DELIMITER = ',';

    String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(strs.size()));
        sb.append(DELIMITER);
        for (int i = 0; i < strs.size(); ++i) {
            sb.append(Integer.toString(strs.get(i).length()));
            sb.append(DELIMITER);
            sb.append(strs.get(i));
        }
        return sb.toString();
    }

    List<String> decode(String s) {
        List<String> ret = new ArrayList<>();
        int i = 0;
        //Maybe the total length is not necessary....
        int f = s.indexOf(DELIMITER);
        //assume this number can be hold by int...
        int size = Integer.parseInt(s.substring(0, f));
        ++f;
        while (i < size) {
            //parse each String
            int nextf = s.indexOf(DELIMITER, f);
            int localSize = Integer.parseInt(s.substring(f, nextf));
            if (localSize == 0)
                ret.add(new String());
            else
                ret.add(new String(s.substring(nextf+1, nextf+localSize+1)));
            f = nextf+localSize+1;
            ++i;
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        String [] strs = {"#$%", "", "12"};
        String encoded = s.encode(Arrays.asList(strs));
        System.out.println(encoded);

        for (String ss : s.decode(encoded)) {
            System.out.println(ss);
        }
    }
}


//How about like this
// #(No. of String), #(len(s1)), #(len(s2),,,,,... Just String...
//不难，但是要考虑好如何handle ""。
//因为平时都把“” 当做Null对待，这里就犯浑了。
//这题，要把Null特别mark一下为‘NULL’，而特别处理 “” empty string.