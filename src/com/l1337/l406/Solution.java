package com.l1337.l406;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o2[0] - o1[0];
                else
                    return o1[1] - o2[1];
            }
        });

        List<int []> tmp = new LinkedList<>();

//        for (int i = 0; i < people.length; ++i) {
//            System.out.println(people[i][0] + "\t" + people[i][1]);
//        }
        for (int i = 0; i < people.length; ++i) {
                tmp.add(people[i][1], people[i]);
        }

        int[][] ret = new int[people.length][];
        for (int i = 0; i < tmp.size(); ++i)
            ret[i] = tmp.get(i);
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        for (int [] item: s.reconstructQueue(people)) {
            System.out.println(item[0] + "\t" + item[1]);
        }
    }
}
