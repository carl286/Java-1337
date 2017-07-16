package com.l1337.l418;


public class Solution {

    /**
     * leetCode 418. Sentence Screen Fitting
     * https://leetcode.com/problems/sentence-screen-fitting
     * Given a rows x cols screen and a sentence represented by a list of words, find how many times the given sentence can be fitted on the screen.

     Note:

     A word cannot be split into two lines.
     The order of words in the sentence must remain unchanged.
     Two consecutive words in a line must be separated by a single space.
     Total words in the sentence won't exceed 100.
     Length of each word won't exceed 10.
     1 ≤ rows, cols ≤ 20,000.


     Example 1:

     Input:
     rows = 2, cols = 8, sentence = ["hello", "world"]

     Output:
     1

     Explanation:
     hello---
     world---

     The character '-' signifies an empty space on the screen.


     Example 2:

     Input:
     rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

     Output:
     2

     Explanation:
     a-bcd-
     e-a---
     bcd-e-

     The character '-' signifies an empty space on the screen.


     Example 3:

     Input:
     rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

     Output:
     1

     Explanation:
     I-had
     apple
     pie-I
     had--

     The character '-' signifies an empty space on the screen.
     */

//    http://www.cnblogs.com/grandyang/p/5975426.html

    public int wordsTyping(String[] sentence, int rows, int cols) {
        if (rows <= 0 || cols <= 0)
            return 0;
        int count = 0;

        int row_index = 0;
        while (row_index < rows) {
            int i = 0;
            int cur_row_spaces = cols;
            while (i < sentence.length && row_index < rows) {
                if (cur_row_spaces >= sentence[i].length()) {
                    cur_row_spaces -= sentence[i].length();
                    ++i;
                    --cur_row_spaces;//allocate at least one space
                } else {
                    ++row_index;
                    cur_row_spaces = cols;
                }
            }

            if (i == sentence.length)
                ++count;
        }
        return count;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int rows = 3, cols = 6;
        String sentence [] = {"a", "bcd", "e"};
        System.out.println(s.wordsTyping(sentence, rows, cols));
    }
}
