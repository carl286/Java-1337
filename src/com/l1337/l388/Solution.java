package com.l1337.l388;


import java.util.Stack;

public class Solution {

    private static final char NEWLINE = '\n';
    private static final char TAB = '\t';
    private static final char DOT = '.';

    static class INode {
        public int level; //number of ident
        public int accLength;//accumulated String length before current node
        public String name;
    }

    public int lengthLongestPath(String input) {
        int ret = 0;
        Stack<INode> parent = new Stack<>();

        int i = 0;
        while (i < input.length()) {
            int cur_level = 0;
            while (i < input.length() && input.charAt(i) == TAB) {
                ++i;
                ++cur_level;
            }

            boolean isFile = false;
            int j = i;
            //check file/folder length
            while (i < input.length() && input.charAt(i) != NEWLINE) {
                if (input.charAt(i) == DOT)
                    isFile = true;
                ++i;
            }

            if (j < i) {
                if (isFile) {
                    while (!parent.isEmpty() && parent.peek().level >= cur_level) {
                        parent.pop();
                    }

                    if (parent.isEmpty()) {
                        ret = i - j;
                    } else {
                        INode top = parent.peek();
                        ret = Math.max(ret, i - j + top.accLength + top.name.length() + top.level + 1);
                    }
                } else {
                    INode node = new INode();
                    node.name = input.substring(j, i);

                    while (!parent.isEmpty() && parent.peek().level >= cur_level) {
                        parent.pop();
                    }

                    if (parent.isEmpty()) {
                        node.level = 0;
                        node.accLength = 0;
                    } else {
                        node.level = parent.peek().level + 1;
                        node.accLength = parent.peek().name.length() + parent.peek().accLength;
                    }
                    parent.push(node);
                }
            }

            while (i < input.length() && input.charAt(i) == NEWLINE) {
                ++i;
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(s.lengthLongestPath(input));
    }
}
