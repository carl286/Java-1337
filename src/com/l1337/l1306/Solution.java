package com.l1337.l1306;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    private int [] directions = new int [] {+1, -1};
    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0)
            return true;
        Deque<Integer> dq = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        dq.addLast(start);
        set.add(start);

        while (!dq.isEmpty())
        {
            Integer current = dq.pollFirst();
            for(int k = 0; k < directions.length; ++k)
            {
                int next = current + directions[k] * arr[current];
                if (next >= 0 && next < arr.length && !set.contains(next))
                {
                    if (arr[next] == 0)
                        return true;
                    set.add(next);
                    dq.addLast(next);
                }
            }
        }
        return false;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
