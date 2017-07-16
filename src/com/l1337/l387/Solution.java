package com.l1337.l387;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int firstUniqChar(String s) {
        Set<Character> moreThanOnce = new HashSet<>();
        Set<Character> once = new HashSet<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i)
        {
            char c = s.charAt(i);
            if (!moreThanOnce.contains(c))
            {
                if (once.contains(c))
                {
                    once.remove(c);
                    moreThanOnce.add(c);
                }
                else
                {
                    deque.addLast(i);
                    once.add(c);
                }
            }
        }

        while (!deque.isEmpty() && moreThanOnce.contains(s.charAt(deque.peekFirst())))
            deque.pollFirst();
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
