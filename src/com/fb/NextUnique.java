package com.fb;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class NextUnique {

    Set<Character> once = new HashSet<>();
    Set<Character> moreThanOnce = new HashSet<>();
    Deque<Character> queue = new ArrayDeque<>();

    public char next(char c)
    {
        if (moreThanOnce.contains(c))
        {
            ;
        }
        else if (once.contains(c))
        {
            once.remove(c);
            moreThanOnce.add(c);
        }
        else
        {
            once.add(c);
            queue.addLast(c);

        }

        while (!queue.isEmpty() && moreThanOnce.contains(queue.peekFirst()))
        {
            queue.pollFirst();
        }
        return queue.isEmpty() ? '-' : queue.peekFirst();
    }

    public static void main(String [] args) {
        // String input = new String("AABCCDEFD");
        String input = new String("AACCDEFD");
        NextUnique nq = new NextUnique();
        for(int i = 0; i < input.length(); ++i)
            System.out.println(nq.next(input.charAt(i)));
    }
}
