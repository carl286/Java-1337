package com.l1337.l998;


import com.l1337.common.TreeNode;

public class Solution {

    //a horrible problem....
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if(root==null)return new TreeNode(val);
        if(root.val<val){
            TreeNode node = new TreeNode(val);
            node.left=root;
            return node;
        }
        root.right=insertIntoMaxTree(root.right,val);
        return root;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
