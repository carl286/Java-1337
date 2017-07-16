package com.l1337.l533;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

//    http://www.cnblogs.com/grandyang/p/6754987.html
    public int findBlackPixel(char[][] picture, int N) {
        int n_rows = picture.length;
        if (n_rows <= 0)
            return 0;
        int n_cols = picture[0].length;
        if (N <= 0 || N > Math.min(n_cols, n_rows))
            return 0;

        Map<String, List<Integer>> map = new HashMap<>();
        int [] cols = new int [n_cols];

        for (int i = 0; i < picture.length; ++i) {
            String key = new String(picture[i]);
            List<Integer> list = map.getOrDefault(key, new ArrayList<>());
            list.add(i);
            map.put(key, list);
            for (int j = 0; j < n_cols; ++j)
                if (picture[i][j] == 'B')
                    ++cols[j];
        }

        int ret = 0;
        for (List<Integer> entry : map.values()) {
            if (entry.size() == N) {
                //pick a row
                int test_row = entry.get(0);
                for (int j = 0; j < n_cols; ++j)
                    if (picture[test_row][j] == 'B' && cols[j] == N)
                        ret += N;
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findBlackPixel(new char[][]{{'W', 'B', 'W', 'B', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'B', 'W'},
                {'W', 'W', 'B', 'W', 'B', 'W'}}, 3 ));
    }
}
