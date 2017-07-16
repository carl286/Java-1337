package com.googlePrep;

import java.util.HashSet;
import java.util.Set;

public class SecrectWord {
    /*
    给定一个secrect word，
和一个encoding rule如下：对secret中的每个字母做变换，不同的字母不能变化到同一个字母。如banana -> xyzyzy，但banana不可以变成xyyyyy，因为这样就没法decode回来。
现input是一个很长的string，要求判断string中是否存在substring可以由以上的encoding rule变换而来。
题不难，面试官大叔人特别好，一直跟我说这栋楼新开了gym但我还是越来越胖 -。- lz说就只能挨个substring扫一遍，想不到啥更好的方法。他说没啥更好的方法，要我开始写。lz当时有点脑洞可能写的不是最常规的解法，他说interesting。。。然后follow up问如果secret word有很多怎么办，聊了聊天，愉快的结束了
     */

    boolean canTranslate(String secret, String data) {
        if (secret.length() > data.length())
            return false;

        if (secret.length() == 0)
            return false;

        int count [] = new int [26];
        Set<Character> used = new HashSet<>();
        char map [] = new char[26];
        int i = 0, start = 0;
        while (i < data.length()) {

        }



        return false;
    }

    public static void main(String [] args) {
        int a [] = new int [] {1,2};
        int b [] = new int [] {1,2};
        HashSet<int []> set = new HashSet<>();
        set.add(a);
        System.out.println(set.contains(a));
        System.out.println(set.contains(b));
    }
}
