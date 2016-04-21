package com.l1337.l297;

import com.l1337.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//297. Serialize and Deserialize Binary Tree

//PLEASE BE CAREFUL ANY USE != and == in JAVA.....euqal....
//    After compare with NLR, LNR, LRN, go though by level looks like the only route to go
//    https://leetcode.com/faq/#binary-tree, see example here
//    avoid uncessary ops, like one node, no need for left and right

//https://leetcode.com/discuss/97164/23ms-pre-order-traversal-based-solution-in-java
// preorder is also possible..
//    http://articles.leetcode.com/serializationdeserialization-of-binary/

public class Codec {
    //do it by level
    public final String DELEMETER = ",";
    public final String NULLDENOTER = "#";
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int c = 1;
            while (!q.isEmpty() && c > 0) {
                Queue<TreeNode> next = new LinkedList<>();
                int counter = 0;
                while (!q.isEmpty() && (c > 0 || counter > 0)) {
                    TreeNode head = q.remove();
                    if (head != null) {
                        --c;
                        sb.append(DELEMETER + head.val);
                        next.add(head.left);
                        next.add(head.right);
                        if (head.left != null) {
                            ++counter;
                        }
                        if (head.right != null) {
                            ++counter;
                        }
                    } else {
                        sb.append(DELEMETER + NULLDENOTER);
                    }
                }
                c = counter;
                q = next;
            }
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
    public TreeNode deserialize(String data) {
        if (data.length() == 0)
            return null;
        String [] array = data.split(DELEMETER);
        TreeNode root = new TreeNode(Integer.valueOf(array[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (i < array.length) {
            TreeNode parent = q.remove();
            if (!array[i].equals(NULLDENOTER)) {
                TreeNode left = new TreeNode(Integer.valueOf(array[i]));
                parent.left = left;
                q.add(left);
            }
            ++i;
            if (i < array.length && !array[i].equals(NULLDENOTER)) {
                TreeNode right = new TreeNode(Integer.valueOf(array[i]));
                parent.right = right;
                q.add(right);
            }
            ++i;
        }

        return root;
    }
    public static void main(String [] args) {
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
