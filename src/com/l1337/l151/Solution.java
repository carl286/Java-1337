package com.l1337.l151;


public class Solution {

    private void reverseCharArray(char [] array, int start, int end)
    {
        while (start < end)
        {
            char tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
            ++start;
            --end;
        }
    }
    public String reverseWords(String s) {
        s = s.trim();
        char [] tmp = s.toCharArray();
        reverseCharArray(tmp, 0, tmp.length - 1);
        int finalLength = 0, i = 0;
        while (i < tmp.length)
        {
            int j = i;
            while (j < tmp.length && !Character.isSpaceChar(tmp[j]))
            {
                ++j;
            }
            reverseCharArray(tmp, i, j - 1);
            while (i < j)
                tmp[finalLength++] = tmp[i++];

            if (j != tmp.length)
                tmp[finalLength++] = tmp[j];

            while (j < tmp.length && Character.isSpaceChar(tmp[j]))
                ++j;
            i = j;
        }


        return new String(tmp, 0, finalLength);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.reverseWords("a good   example"));
    }
}
