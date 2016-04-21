package com.leetcode;

//	Shortest Word Distance II, 244
//	This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?
// Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
//	For example,
//	Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//	Given word1 = "coding”, word2 = "practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.
//Note:
//        You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//I could not find a good way to store the word1 + word2 as the key so that we can compute the distance for once..
//因为会多次调用，我们不能每次调用的时候再把这两个单词的下标找出来。我们可以用一个哈希表，在传入字符串数组时，就把每个单词的下标找出存入表中。这样当调用最短距离的方法时，我们只要遍历两个单词的下标列表就行了。具体的比较方法，则类似merge two list，每次比较两个list最小的两个值，得到一个差值。然后把较小的那个给去掉。因为我们遍历输入数组时是从前往后的，所以下标列表也是有序的。
public class WordDistance {
    HashMap<String, List<Integer>> map = new HashMap<>();
    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], new ArrayList<Integer>());
            }
            map.get(words[i]).add(i);
        }
    }

    //You don't have to go through everyone as things might go worse
    //https://leetcode.com/discuss/50185/java-solution-using-minimum-difference-between-sorted-arrays
    public int shortest(String word1, String word2) {
        List<Integer> id1 = map.get(word1);
        List<Integer> id2 = map.get(word2);
        int i = 0, j = 0;
        int l = id1.get(0);
        int r = id2.get(0);
        int ret = Math.abs(l - r);
        while (true) {
            if ((l < r || j + 1 == id2.size()) && i + 1 != id1.size())
                l = id1.get(++i);
            else if (j + 1 != id2.size())
                r = id2.get(++j);
            else
                break;
            ret = Math.min(Math.abs(l - r), ret);
        }

        return ret;
    }

    public static void main(String [] args) {
        String []  words = {"practice", "makes", "perfect", "coding", "makes"};
        WordDistance wordDistance = new WordDistance(words);
        System.out.println(wordDistance.shortest("coding", "practice"));
        System.out.println(wordDistance.shortest("makes", "coding"));
    }
}
