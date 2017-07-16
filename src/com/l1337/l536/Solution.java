package com.l1337.l536;


import com.l1337.common.TreeNode;

public class Solution {

    /*
    https://www.lintcode.com/problem/construct-binary-tree-from-string/
    Construct Binary Tree from String

    Description
    You need to construct a binary tree from a string consisting of parenthesis and integers.

    The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
    The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

    You always start to construct the left child node of the parent first if it exists.

    There will only be '(', ')', '-' and '0' ~ '9' in the input string.
    An empty tree is represented by "" instead of "()".
    Example
    Example 1:

    Input: "-4(2(3)(1))(6(5))"
    Output: {-4,2,6,3,1,5}
    Explanation:
    The output is look like this:
          -4
         /  \
        2    6
       / \   /
      3   1 5
    Example 2:

    Input: "1(-1)"
    Output: {1,-1}
    Explanation:
    The output is look like this:
         1
        /
      -1

     */

    //[start, end)
    public TreeNode str2tree(String s, int start, int end) {
        if (start >= end)
            return null;
        if (s.charAt(start) == '(')
        {
            ++start;
            --end;
        }
        //ideally, this check is not needed.
//        if (start >= end)
//            return null;
        int i = start;
        while (i < end && s.charAt(i) != '(')
            ++i;
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(start, i)));
        if (i < end)
        {
            //means we found at least one parenthesis
            //i point to '('
            int j = i;
            int left = 1;
//            while (j < end && s.charAt(j) != ')')
            do {
                ++j;
                if (s.charAt(j) == '(')
                    ++left;
                else if (s.charAt(j) == ')')
                    --left;
            } while (left != 0);

            root.left = str2tree(s, i + 1, j);
            root.right = str2tree(s, j + 1, end);
        }

        return root;
    }
    public TreeNode str2tree(String s) {
        // write your code here
        return str2tree(s, 0, s.length());
    }

//    https://eugenejw.github.io/2017/08/leetcode-536.html
//    https://yangwg.gitbooks.io/leetcode-young/content/tree/536-construct-binary-tree-from-string.html
    public static void main(String [] args) {
        Solution s = new Solution();
        String input = "-4(2(3)(1))(6(5))";
//        String input = "2(2(3)(1))";
        TreeNode root = s.str2tree(input);
        System.out.println(root.val);
    }
}
