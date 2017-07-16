package com.l1337.l853;


import java.util.*;

public class Solution {

    private boolean canMeet(int target, int[] position, int[] speed, int i, int j)
    {
        if (position[i] == position[j])
            return true;
        if ((position[i] < position[j] && speed[i] > speed[j]) || (position[i] > position[j] && speed[i] < speed[j]))
        {
            //time to meet
            return (position[i] - position[j]) / (double) (speed[j] - speed[i]) <= Math.min((target - position[i]) / (double) speed[i], (target - position[j]) / (double) speed[j]);
        }
        else
        {
            return false;
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {
        //this is not how problem interpreted
        int n = position.length;
        if (n <= 1)
            return n;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> unvisited = new HashSet<>();
        for (int i = 0; i < position.length; ++i)
        {
            unvisited.add(i);
            for (int j = i + 1; j < position.length; ++j)
            {
                if(canMeet(target, position, speed, i, j))
                {
                    Set<Integer> next = map.getOrDefault(i, new HashSet<>());
                    next.add(j);
                    map.put(i, next);

                    next = map.getOrDefault(j, new HashSet<>());
                    next.add(i);
                    map.put(j, next);
                }
            }
        }

        int ret = 0;
        while (!unvisited.isEmpty())
        {
            Integer cur = unvisited.iterator().next();
            unvisited.remove(cur);
            ret++;

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.add(cur);
            while (!queue.isEmpty())
            {
                cur = queue.pollFirst();
                for(Integer next: map.getOrDefault(cur, new HashSet<>()))
                {
                    if (unvisited.contains(next))
                    {
                        unvisited.remove(next);
                        queue.addLast(next);
                    }

                }
            }

        }

        return ret;
    }

//    https://leetcode.com/problems/car-fleet/discuss/139850/C%2B%2BJavaPython-Straight-Forward


    public static void main(String [] args) {
        Solution s = new Solution();
        int target = 12;
        int [] position = new int [] {4,0,5,3,1,2};
        int [] speed = new int [] {6,10,9,6,7,2};
        System.out.println(s.carFleet(target, position, speed));
    }
}
