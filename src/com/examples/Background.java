package com.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Ke.Liu on 7/9/16.
 */
public class Background {
    boolean isPa(int x) {
        long y = 0;
        int mask = 1;
        while ((x & ~(mask - 1)) != 0) {
            y <<= 1;

            y |= (x & mask);

            System.out.println(x & mask);

            mask <<= 1;


            System.out.println(Long.toHexString(y));
        }
        return x == (int)y;
    }

    boolean hasBalancedBrackets(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        map.put('<', '>');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else if (map.containsValue(c)) {
                if (stack.isEmpty() || !map.get(stack.pop()).equals(c))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String [] args) {
        Background b = new Background();
//        int x = 9;
//        System.out.println(b.isPa(x));
        String test = "(){}";
        System.out.println(b.hasBalancedBrackets(test));
    }
}
