package com.l1337.l316;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

//https://leetcode.com/problems/remove-duplicate-letters/
//316. Remove Duplicate Letters
public class Solution {

//    https://www.hrwhisper.me/leetcode-remove-duplicate-letters/
//    https://leetcode.com/submissions/detail/58083929/
    public String removeDuplicateLetters(String s) {




        if (s == null || s.length() <= 1)
            return s;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i)
            map.put(s.charAt(i), i);

        Stack<Integer> st = new Stack<>();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            if (st.isEmpty()) {
                st.push(i);
                set.add(s.charAt(i));
            } else {
                //it's critical here to have equals...
                //here is why "abacb" will fail, because a is already in its position.....so you need below if...
                if (!set.contains(s.charAt(i))) {
                    while (!st.isEmpty() && s.charAt(st.peek()) >= s.charAt(i) && map.get(s.charAt(st.peek())) > i) {
                        set.remove(s.charAt(st.pop()));
                    }
                    st.push(i);
                    set.add(s.charAt(i));
                }
            }
        }
        StringBuilder sb = new StringBuilder(st.size());
        while (!st.isEmpty()) {
            sb.append(s.charAt(st.pop()));
        }
        sb.reverse();
        return sb.toString();
    }
    public static void main(String [] args) {
        Solution s = new Solution();

//        System.out.println(s.removeDuplicateLetters("bcabc"));
//        System.out.println(s.removeDuplicateLetters("cbacdcbc"));
        System.out.println(s.removeDuplicateLetters("bbcaac"));
//        System.out.println(s.removeDuplicateLetters("abacb"));

    }
}
