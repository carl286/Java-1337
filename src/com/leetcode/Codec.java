package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class Codec {
    final String DELEMETER = ",";
    final String NULLDENOTER = "#";

    //PLEASE BE CAREFUL ANY USE != and == in JAVA.....euqal....
//    After compare with NLR, LNR, LRN, go though by level looks like the only route to go
//    https://leetcode.com/faq/#binary-tree, see example here
//    avoid uncessary ops, like one node, no need for left and right

//https://leetcode.com/discuss/97164/23ms-pre-order-traversal-based-solution-in-java
// preorder is also possible..
//    http://articles.leetcode.com/serializationdeserialization-of-binary/
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder ret = new StringBuilder();
        if (root == null)
            return ret.toString();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean next = true;
        //How to prune remaing null denometer in one level??
        int acc = 1;
        while (queue.size() != 0 && acc > 0) {
            Queue<TreeNode> nextLevel = new LinkedList<>();
            next = false;
            int nextacc = 0;
            while (queue.size() != 0) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    --acc;
                    ret.append(DELEMETER);
                    ret.append(Integer.toString(cur.val));
                    nextLevel.add(cur.left);
                    nextLevel.add(cur.right);
                    if (cur.left != null) {
                        nextacc++;
                    }
                    if (cur.right != null) {
                        nextacc++;
                    }
                } else {
                    if (acc != 0 || nextacc != 0) {
                        ret.append(DELEMETER);
                        ret.append(NULLDENOTER);
                    } else {
                        break;
                    }
                }
            }
            queue = nextLevel;
            acc = nextacc;
        }
        return ret.toString().substring(1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() <= 0)
            return null;
        String [] splited = data.split(DELEMETER);
        for (String s : splited) {
            System.out.println(s);
        }
        TreeNode root = new TreeNode(Integer.valueOf(splited[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < splited.length) {
            TreeNode cur = queue.poll();
            if (!splited[i].equals(NULLDENOTER)) {
                TreeNode left = new TreeNode(Integer.valueOf(splited[i]));
                cur.left = left;
                queue.add(left);
            }
            ++i;
            if (i < splited.length && !splited[i].equals(NULLDENOTER)) {
                TreeNode right = new TreeNode(Integer.valueOf(splited[i]));
                cur.right = right;
                queue.add(right);
            }
            ++i;
        }

        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        String s1 = codec.serialize(root);
        System.out.println(s1);
        TreeNode ret = codec.deserialize(s1);
        System.out.println(ret);
    }
}
