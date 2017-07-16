package com.l1337.l934;


import javafx.util.Pair;

import java.util.*;

public class Solution {

    public int shortestBridge(int[][] A) {
        //assume it guarantee has 2 islands
        Set<Pair<Integer, Integer>> expand1 = new HashSet<>();
        Set<Pair<Integer, Integer>> expand2 = new HashSet<>();

        for(int i = 0; i < A.length; ++i)
        {
            for(int j = 0; j < A[0].length; ++j)
            {
                if (A[i][j] != 0)
                {
                    if (expand1.isEmpty())
                    {
                        expandGraph(i,j,A,expand1);
                    }
                    else
                    {
                        expandGraph(i,j,A,expand2);
                        break;
                    }
                }
            }
        }

        List<Pair<Integer, Integer>> queue1 = new ArrayList<>(expand1);
        List<Pair<Integer, Integer>> queue2 = new ArrayList<>(expand2);

        int ret = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty())
        {
            ++ret;
            //expand1
            List<Pair<Integer, Integer>> next1 = new ArrayList<>();
            for(int i = 0; i < queue1.size(); ++i)
            {
                for(int k = 0; k < directions.length; ++k)
                {
                    int next_i = queue1.get(i).getKey() + directions[k][0];
                    int next_j = queue1.get(i).getValue() + directions[k][1];
                    Pair<Integer, Integer> nextPair = new Pair<>(next_i, next_j);

                    if (isValid(next_i, next_j, A) && !expand1.contains(nextPair))
                    {
                        if (expand2.contains(nextPair))
                            return ret - 1;
                        else
                        {
                            expand1.add(nextPair);
                            next1.add(nextPair);
                        }
                    }
                }
            }
            queue1 = next1;

            ++ret;
            List<Pair<Integer, Integer>> next2 = new ArrayList<>();
            for(int i = 0; i < queue2.size(); ++i)
            {
                for(int k = 0; k < directions.length; ++k)
                {
                    int next_i = queue2.get(i).getKey() + directions[k][0];
                    int next_j = queue2.get(i).getValue() + directions[k][1];
                    Pair<Integer, Integer> nextPair = new Pair<>(next_i, next_j);

                    if (isValid(next_i, next_j, A) && !expand2.contains(nextPair))
                    {
                        if (expand1.contains(nextPair))
                            return ret - 1;
                        else
                        {
                            expand2.add(nextPair);
                            next2.add(nextPair);
                        }
                    }
                }

            }
            queue2 = next2;
        }
        return Integer.MAX_VALUE;
    }

    //below is wrong
    public int shortestBridge_v2(int[][] A) {
        //assume it guarantee has 2 islands
        Set<Pair<Integer, Integer>> expand = new HashSet<>();
        List<Pair<Integer, Integer>> toExpand = new ArrayList<>();


        for(int i = 0; i < A.length && expand.isEmpty(); ++i)
        {
            for(int j = 0; j < A[0].length; ++j)
            {
                if (A[i][j] != 0)
                {
                    Pair<Integer, Integer> cur = new Pair<>(i,j);
                    List<Pair<Integer, Integer>> queue = new ArrayList<>();
                    expand.add(cur);
                    queue.add(cur);
                    // A[i][j] = 0;

                    while (!queue.isEmpty())
                    {
                        List<Pair<Integer, Integer>> next = new ArrayList<>();
                        for(int k = 0; k < queue.size(); ++k)
                        {
                            cur = queue.get(k);
                            for(int l = 0; l < directions.length; ++l)
                            {
                                int next_i = cur.getKey() + directions[l][0];
                                int next_j = cur.getValue() + directions[l][1];
                                Pair<Integer, Integer> nextPair = new Pair<>(next_i, next_j);
                                if (isValid(next_i, next_j, A) && !expand.contains(nextPair))
                                {
                                    expand.add(nextPair);
                                    if (A[next_i][next_j] == 0)
                                    {
                                        toExpand.add(nextPair);
                                    }
                                    else
                                    {
                                        next.add(nextPair);
                                    }
                                }
                            }
                        }

                        queue = next;
                    }

                    break;
                }
            }
        }

        int ret = 0;
        while (!toExpand.isEmpty())
        {
            ++ret;
            //expand1
            List<Pair<Integer, Integer>> next = new ArrayList<>();
            for(int i = 0; i < toExpand.size(); ++i)
            {
                for(int k = 0; k < directions.length; ++k)
                {
                    int next_i = toExpand.get(i).getKey() + directions[k][0];
                    int next_j = toExpand.get(i).getValue() + directions[k][1];
                    Pair<Integer, Integer> nextPair = new Pair<>(next_i, next_j);

                    if (isValid(next_i, next_j, A) && !expand.contains(nextPair))
                    {
                        expand.add(nextPair);
                        if (A[next_i][next_j] == 1)
                            return ret;

                        next.add(nextPair);
                    }
                }
            }
            toExpand = next;
        }
        return Integer.MAX_VALUE;
    }

    private int [][] directions = new int [][]{{-1,0},{+1,0},{0,-1},{0,+1}};
    private boolean isValid(int i, int j, int A [][])
    {
        return i >= 0 && i < A.length && j >= 0 && j < A[0].length;
    }
    private void expandGraph(int i, int j, int A [][], Set<Pair<Integer, Integer>> expand)
    {
        Pair<Integer, Integer> cur = new Pair<>(i,j);
        List<Pair<Integer, Integer>> queue = new ArrayList<>();
        expand.add(cur);
        queue.add(cur);
        A[i][j] = 0;

        while (!queue.isEmpty())
        {
            List<Pair<Integer, Integer>> next = new ArrayList<>();
            for(int k = 0; k < queue.size(); ++k)
            {
                cur = queue.get(k);
                for(int l = 0; l < directions.length; ++l)
                {
                    int next_i = cur.getKey() + directions[l][0];
                    int next_j = cur.getValue() + directions[l][1];
                    if (isValid(next_i, next_j, A) && A[next_i][next_j] != 0)
                    {
                        A[next_i][next_j] = 0;
                        Pair<Integer, Integer> nextPair = new Pair<>(next_i, next_j);
                        expand.add(nextPair);
                        next.add(nextPair);
                    }
                }
            }

            queue = next;
        }
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        // int [][] A = new int [][]{{0,1}, {1,0}};
        int [][] A = new int [][]{{0,1,0}, {0,0,0}, {0,0,1}};
        System.out.println(s.shortestBridge_v2(A));
    }
}
