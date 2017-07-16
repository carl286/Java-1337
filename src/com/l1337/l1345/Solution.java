package com.l1337.l1345;


import java.util.*;

public class Solution {

    //BFS got too many memory usage....
    public int minJumps(int[] arr) {
        if (arr.length <= 1)
            return 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; ++i)
        {
            map.putIfAbsent(arr[i], new HashSet<>());
            map.get(arr[i]).add(i);
        }

        //build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int i = 0; i < arr.length; ++i)
        {
            // map.put(i, new HashSet<>(map.getOrDefault(arr[i], new HashSet<>())));
            graph.put(i, new HashSet<>(map.get(arr[i])));
            graph.get(i).remove(i);
            if (i-1 >= 0)
                graph.get(i).add(i-1);
            if (i+1 < arr.length)
                graph.get(i).add(i+1);
        }

        //BFS
        int ret = 0;
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> dq = new ArrayDeque<>();
        visited.add(0);
        dq.add(0);

        while (!dq.isEmpty())
        {
            int size = dq.size();
            ++ret;
            while(size-- > 0)
            {
                Integer current = dq.pollFirst();
                for(Integer next : graph.get(current))
                {
                    if (!visited.contains(next))
                    {
                        if (next == arr.length - 1)
                            return ret;

                        visited.add(next);
                        dq.addLast(next);
                    }
                }
            }

        }

        return Integer.MAX_VALUE;
    }

    private int [] shrink(int [] arr)
    {
        List<Integer> ret = new ArrayList<>();
        ret.add(arr[0]);
        for(int i = 1; i < arr.length; ++i)
        {
            if (ret.get(ret.size()-1) == arr[i] && ret.size() >= 2 && ret.get(ret.size()-2) == arr[i])
                continue;
            else
                ret.add(arr[i]);
        }

        return ret.stream().mapToInt(i->i).toArray();
    }

//    https://leetcode.com/submissions/detail/479357134/
    public int minJumpsMemoImproved(int[] arr) {
        if (arr.length <= 1)
            return 0;
        //shrink the array
        arr = shrink(arr);

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; ++i)
        {
            map.putIfAbsent(arr[i], new HashSet<>());
            map.get(arr[i]).add(i);
        }

        //BFS
        int ret = 0;
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> dq = new ArrayDeque<>();
        visited.add(0);
        dq.add(0);

        while (!dq.isEmpty())
        {
            int size = dq.size();
            ++ret;
            while(size-- > 0)
            {
                Integer current = dq.pollFirst();
                //try left and right
                Integer left = current - 1;
                if (left >= 0 && !visited.contains(left))
                {
                    visited.add(left);
                    dq.addLast(left);
                }
                Integer right = current + 1;
                if (right < arr.length && !visited.contains(right))
                {
                    if (right == arr.length - 1)
                        return ret;
                    visited.add(right);
                    dq.addLast(right);
                }

                for(Integer next : map.get(arr[current]))
                {
                    if (!visited.contains(next))
                    {
                        if (next == arr.length - 1)
                            return ret;

                        visited.add(next);
                        dq.addLast(next);
                    }
                }
            }

        }

        return Integer.MAX_VALUE;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        int [] arr = new int [] {100,-23,-23,404,100,23,23,23,3,404};
        int [] arr = new int [] {100,-23,-23,404,100,23,23,23,3,404};
        System.out.println(s.minJumpsMemoImproved(arr));
    }
}
