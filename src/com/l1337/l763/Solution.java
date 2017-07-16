package com.l1337.l763;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    private boolean isOverlap(int[]a, int []b)
    {
        return !(a[1] < b[0] || b[1] < a[0]);
    }

    public List<Integer> partitionLabels(String S) {
        int start [] = new int [26];
        int end [] = new int [26];
        Arrays.fill(start, -1);
        Arrays.fill(end, -1);
        for(int i = 0; i < S.length(); ++i)
        {
            int index = S.charAt(i) - 'a';
            if (start[index] < 0)
            {
                start[index] = i;
                end[index] = i;
            }
            else
            {
                end[index] = i;
            }
        }

        List<int []> list = new ArrayList<>();
        for(int i = 0; i < start.length; ++i)
        {
            if (start[i] >= 0)
                list.add(new int []{start[i], end[i]});
        }
        //merge intervals
        Collections.sort(list, (a, b)->(a[0]-b[0]));
        List<int[]> last = new ArrayList<>();
        last.add(list.get(0));

        for(int i = 1; i < list.size(); ++i)
        {
            if (isOverlap(last.get(last.size()-1), list.get(i)))
            {
                last.get(last.size()-1)[1] = Math.max(list.get(i)[1], last.get(last.size()-1)[1]);
            }
            else
            {
                last.add(list.get(i));
            }
        }

        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < last.size(); ++i)
            ret.add(last.get(i)[1]-last.get(i)[0]+1);
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
