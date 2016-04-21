package com.l1337.l386;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public List<Integer> lexicalOrder8(int n) {
        //Below just a JAVA 8 practice...
        return IntStream.rangeClosed(1, n).
                mapToObj(Integer::toString).sorted((l, r) -> {
                    int i;
                    int length = Math.min(l.length(), r.length());
                    for (i = 0; i < length; ++i)
                        if (l.charAt(i) < r.charAt(i))
                            return -1;
                        else if (l.charAt(i) > r.charAt(i))
                            return +1;
                    if (i < l.length())
                        return +1;
                    else if (i < r.length())
                        return -1;
                    else
                        return 0;
                }).
                map(e -> Integer.valueOf(e)).
                collect(Collectors.toList());
    }

    private List<Integer> result;
    private int n;
    public List<Integer> lexicalOrderRecursive(int n) {
        this.result = new ArrayList<Integer>();
        this.n = n;
        solve(1);
        return result;
    }
//    优先将数字乘10；如果数字末位＜9，考虑将数字加1
//            递归式类似于二叉树的先根遍历
    private void solve(int m) {
        result.add(m);
        if (m * 10 <= n) solve(m * 10);
        if (m < n && m % 10 < 9) solve(m + 1);
    }

//    http://bookshadow.com/weblog/2016/08/21/leetcode-lexicographical-numbers/
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<>();
        if (n <= 0)
            return ret;

        int i = 1;
        Stack<Integer> st = new Stack<>();
        st.push(i);
        while (!st.isEmpty()) {
            int top = st.pop();
            ret.add(top);
            int p = 10*top;
            if (top < n && top % 10 < 9)
                st.push(top + 1);
            if (p <= n)
                st.push(p);
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int n = 20;
//        for (Integer i : s.lexicalOrder8(n))
        for (Integer i : s.lexicalOrder(n))
            System.out.println(i);
    }
}
