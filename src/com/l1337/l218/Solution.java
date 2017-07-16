package com.l1337.l218;


import java.util.*;

public class Solution {

//    https://leetcode.com/problems/the-skyline-problem/discuss/61193/Short-Java-solution
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ret = new ArrayList<>();
        //0->pos, 1->hight
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        for (int i = 0; i < buildings.length; ++i)
        {
            pq.add(new int []{buildings[i][0], buildings[i][2]});
            pq.add(new int []{buildings[i][1], -buildings[i][2]});
        }

        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        while (!pq.isEmpty())
        {
            int [] n = pq.poll();
            int abs;
            if(n[1] < 0)
            {
                abs = -n[1];
                treemap.put(abs, treemap.get(abs)- 1);
                if (treemap.get(abs) == 0)
                {
                    treemap.remove(abs);
                    if (ret.get(ret.size()-1).get(1) == abs)
                    {
//                        if (ret.get(ret.size()-1).get(0) == n[0])
//                        {
//                            ret.set(ret.size() - 1, Arrays.asList(n[0], treemap.isEmpty() ? 0 : treemap.lastKey()));
//                        }
//                        else
                        {
                            ret.add(Arrays.asList(n[0], treemap.isEmpty() ? 0 : treemap.lastKey()));
                        }
                    }
                }
            }
            else
            {
                abs = n[1];
                treemap.put(abs, treemap.getOrDefault(abs, 0) + 1);
                if (ret.isEmpty())
                {
                    ret.add(Arrays.asList(n[0], n[1]));
                }
                else if (ret.get(ret.size()-1).get(1) < n[1])
                {
                    if (ret.get(ret.size()-1).get(0) < n[0])
                    {
                        //a new pos
                        ret.add(Arrays.asList(n[0], n[1]));
                    }
//                    else
//                    {
//                        ret.set(ret.size()-1, Arrays.asList(n[0], n[1]));
//                    }
                }
            }
        }



        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();

//        List<List<Integer>> ret = s.getSkyline(new int [][]{{0,2,3}, {2,5,3}});
        List<List<Integer>> ret = s.getSkyline(new int [][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}});
        for (int i = 0; i < ret.size(); ++i)
        {
            for (int j = 0; j < ret.get(i).size(); ++j)
            {
                System.out.print(ret.get(i).get(j) + "\t");
            }
            System.out.println();
        }
        System.out.println("Hello World");
    }
}
