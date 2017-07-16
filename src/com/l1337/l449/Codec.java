package com.l1337.l449;

import com.l1337.common.TreeNode;

public class Codec {
    private static final char DELIMETER = '#';
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        String left = serialize(root.left);
        String right = serialize(root.right);

        if (left.length() == 0) {
            if (right.length() == 0) {
                return Integer.toString(root.val);
            } else {
                return Integer.toString(root.val) + DELIMETER + right;
            }
        } else {
            if (right.length() == 0)
                return Integer.toString(root.val) + DELIMETER + left;
            else
                return Integer.toString(root.val) + DELIMETER + left + DELIMETER + right;
        }
    }

    public TreeNode deserialize(int [] data, int i, int j) {
        if (i > j)
            return null;
        TreeNode root = new TreeNode(data[i]);
        int k = i + 1;
        while (k <= j && data[k] <= root.val) ++k;
        TreeNode left = deserialize(data, i + 1, k - 1);
        TreeNode right = deserialize(data, k, j);
        root.left = left;
        root.right = right;
        return root;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() <= 0)
            return null;
        //split function is terrible, it's much better to do it manually
        String[] datas = data.split(Character.toString(DELIMETER));
        int [] input = new int [datas.length];
        for (int k = 0; k < input.length; ++k)
            input[k] = Integer.valueOf(datas[k]);
        return deserialize(input, 0, input.length - 1);
    }

//    https://leetcode.com/problems/serialize-and-deserialize-bst/discuss/93167/Concise-C++-19ms-solution-beating-99.4
    public static void main(String [] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n2.left = n1;
        n2.right = n3;

        Codec s = new Codec();
        s.deserialize(s.serialize(null));
    }

}
