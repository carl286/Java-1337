package com.l1337.l331;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

//题意：给定二叉树的前序遍历，要你在不构造树的情况下，判断树是否合法。
//        其实是一道google电面的题
public class Solution {

//    private int cur;
//    private TreeNode createNode(String s) {
//        if (s.equals("#"))
//            return null;
//        else
//            return new TreeNode(Integer.parseInt(s));
//    }
//
//    public boolean isValidSerialization(String[] array, TreeNode root) {
//        if (cur >= array.length)
//            return true;
//        TreeNode newRoot = createNode(array[cur++]);
//        if (newRoot != null)
//
//    }

    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() <= 0)
            return false;
        String[] array = preorder.split(",");
        if (array.length < 1)
            return false;
//        cur = 0;


        //0 denotes no child, 1 denotes left child is set, 2 denotes right child is set.
        Stack<Integer> st = new Stack<>();
        int cur = 0;
        while (cur < array.length) {
//            if (st.isEmpty())
//                return false;
//
            //when you come here, top node value is at most 1....
            if (array[cur++].equals("#")) {
                while (!st.isEmpty() && st.peek() == 1)
                    st.pop();
                if (st.isEmpty())
                    return cur == array.length;
                st.push(st.pop()+1);
            } else {
                //push a new node
                st.push(0);
            }
            //You should never empty your stack during the run...
        }
        return st.isEmpty();
    }


    //Method 1
//    这个方法简单的说就是不断的砍掉叶子节点。最后看看能不能全部砍掉。
//    已例子一为例，：”9,3,4,#,#,1,#,#,2,#,6,#,#” 遇到x # #的时候，就把它变为 #
    //This idea is similar to mine above...
    public boolean isValidSerializationI(String preorder) {
        String [] array = preorder.split(",");
        LinkedList<String> l = new LinkedList<>();
        int i = 0;
        while (i < array.length) {
            l.add(array[i++]);
            while (l.size() >= 3 && l.get(l.size()-1).equals("#") && l.get(l.size()-2).equals("#") && !l.get(l.size()-3).equals("#")) {
                l.removeLast();
                l.removeLast();
                l.removeLast();
                l.add("#");
            }
        }
        return l.size() == 1 && l.getLast().equals("#");
    }

    //Method 2
//    对于二叉树，我们把空的地方也作为叶子节点（如题目中的#），那么有
//    所有的非空节点提供2个出度和1个入度（根除外）
//    所有的空节点但提供0个出度和1个入度
//    我们在遍历的时候，计算diff = outdegree – indegree.
// 当一个节点出现的时候，diff – 1，因为它提供一个入度；当节点不是#的时候，diff+2(提供两个出度) 如果序列式合法的，那么遍历过程中diff >=0 且最后结果为0.
//    https://leetcode.com/discuss/83824/7-lines-easy-java-solution
//    如果在遍历过程中的某时刻，系统的入度>出度，则说明当前序列中出现过的所有分支节点的“空闲分支”均已用完，后序节点没有办法挂载到之前出现的节点之上，从而判定先序遍历的序列是不合法的。
    public boolean isValidSerializationII(String preorder) {
        String [] array = preorder.split(",");
        int diff = 1;
        for (String node: array) {
            if (--diff < 0) return false;
            if (!node.equals("#")) diff += 2;
        }

        return diff == 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.isValidSerialization("1,#"));
//        System.out.println(s.isValidSerialization("9,#,#,1"));
        System.out.println(s.isValidSerializationI("9,3,4,#,#,1,#,#,2,#,6,#,#"));
//        System.out.println(s.isValidSerialization("1,#,#"));
    }
}
