package com.l1337.l1032;


public class StreamChecker {

    public class TreeNode {
        public TreeNode [] data = new TreeNode[26];
        public boolean isEnding = false;
    }

    private TreeNode root;
    private char data [];
    private int tail = 0;
    private int head = 0;
//    https://leetcode.com/problems/stream-of-characters/discuss/713356/Explaining-by-examples-what-constitutes-an-expected-solution-for-future-readers
    public StreamChecker(String[] words) {
        this.root = new TreeNode();
        int max_length = 0;
        for (int i = 0; i < words.length; ++i)
        {
            max_length = Math.max(max_length, words[i].length());
            TreeNode cur = root;
            for (int j = words[i].length() - 1; j >= 0; --j)
            {
                if (cur.data[words[i].charAt(j) - 'a'] == null)
                {
                    cur.data[words[i].charAt(j) - 'a'] = new TreeNode();
                }
                cur = cur.data[words[i].charAt(j) - 'a'];
            }
            cur.isEnding = true;
        }
        this.data = new char[1 + max_length];
    }

    public boolean query(char letter) {
        this.data[head % this.data.length] = letter;
        this.head = (1 + this.head) % this.data.length;
        if (this.head == this.tail)
        {
            this.tail = (this.tail + 1) % this.data.length;
        }

        boolean found = false;
        int len = (this.head - this.tail + this.data.length) % this.data.length;
        int index = this.head;
        TreeNode cur = root;
        while (len-- > 0)
        {
            index = (index-1+this.data.length)% this.data.length;
            cur = cur.data[this.data[index] - 'a'];
            if (cur == null)
                return false;
            if (cur.isEnding)
                return true;
        }

        return false;
    }
    public static void main(String [] args) {
        StreamChecker s = new StreamChecker(new String[] {"cd","f","kl"});
        System.out.println(s.query('a'));
        System.out.println(s.query('b'));
        System.out.println(s.query('c'));
        System.out.println(s.query('d'));
        System.out.println(s.query('e'));
        System.out.println(s.query('f'));
        System.out.println(s.query('g'));
        System.out.println(s.query('h'));
        System.out.println(s.query('i'));
        System.out.println(s.query('j'));
        System.out.println(s.query('k'));
        System.out.println(s.query('l'));
    }
}
