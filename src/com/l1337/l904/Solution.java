package com.l1337.l904;


import java.util.HashMap;
import java.util.Map;

public class Solution {

//    https://leetcode.com/problems/fruit-into-baskets/discuss/170740/Sliding-Window-for-K-Elements
//    https://leetcode.com/problems/fruit-into-baskets/
    public int totalFruit(int[] tree) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int start = 0, cur = 0; cur < tree.length; ++cur)
        {
            if (!map.containsKey(tree[cur]) && map.size() == 2)
            {
                //reduce
                while (start < cur && map.size() > 1)
                {
                    map.put(tree[start], map.getOrDefault(tree[start], 0) - 1);
                    if (map.get(tree[start]) == 0)
                        map.remove(tree[start]);
                    ++start;
                }
            }


            map.put(tree[cur], map.getOrDefault(tree[cur], 0) + 1);

            ret = Math.max(ret, cur - start + 1);
        }

        return ret;
    }

    public int totalFruit3(int[] tree) {
        /*
        [i,j] is the sliding window, and it's the current longest window.
Suppose we found current longest window [i, j], then this length will be kept, because,
as j++, if the sliding window contains more than 2, then also we will do i++, so that is to say, the current longest length will be kept the same,
in another word, when [i,j] reached a maximum, it will not become smaller
on the other side, when j++, if the current window contains less than two, then i will not increase(because the if statement), in this way, the longest length of sliding window will get updated
         */
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int res = 0, i = 0;
        for (int j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            while (count.size() > 2) {
                count.put(tree[i], count.get(tree[i]) - 1);
                if (count.get(tree[i]) == 0) count.remove(tree[i]);
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    //this one is super tricky here....
    public int totalFruit2(int[] tree) {
        Map<Integer, Integer> count = new HashMap<>();
        int i = 0, j;
        for (j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            if (count.size() > 2) {
                count.put(tree[i], count.get(tree[i]) - 1);
                count.remove(tree[i++], 0);
            }
        }
        return j - i;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.totalFruit2(new int []{1,1,1,1,1,1,2,3}));
    }
}
