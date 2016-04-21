package com.l1337.l285;


import com.l1337.common.TreeNode;

public class Solution {
    //	Inorder Successor in BST, 285
//Problem Description:
//	Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
//	Note: If the given node has no in-order successor in the tree, return null.
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null)
            return null;
        if (p.right != null) {
            p = p.right;
            while (p.left != null)
                p = p.left;
            return p;
        }
        //do in order by yourself???
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == p.val) {
                /*
                if (pre != null && pre.val > cur.val)
                    return pre;
                else
                    return null;
                */
                //actually, return pre should be good
                return pre;
            } else if (cur.val < p.val) {
                cur = cur.right;
            } else {
                pre = cur;
                cur = cur.left;
            }

        }
        return null;
    }

//    http://www.jiuzhang.com/solutions/inorder-successor-in-bst/
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}


/*
		if (root == null || p == null)
			return null;
		//assume p must be in the tree now..
		TreeNode cur = root, pre = null;

//		There are just two cases:
//		The easier one: p has right subtree, then its successor is just the leftmost child of its right subtree;
//		The harder one: p has no right subtree, then a traversal is needed to find its successor.
//		Traversal: we start from the root, each time we see a node with val larger than p -> val,
// we know this node may be p's successor. So we record it in suc.
// Then we try to move to the next level of the tree: if p -> val > root -> val, which means p is in the right subtree,
// then its successor is also in the right subtree, so we update root = root -> right; if p -> val < root -> val, we update root = root -> left similarly;
// once we find p -> val == root -> val, we know we've reached at p and the current sucis just its successor.
		while (cur != null && !cur.equals(p)) {
			/* Sorry, below logic is wrong...
			pre = cur;
			if (cur.val < p.val)
				cur = cur.left;
			else
				cur = cur.right;
			*/
/*
            if (cur.val < p.val) {
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }

        if (cur != null) {
            if (cur.right == null)
                return pre;
            else {
                TreeNode ret = cur.right;
                while (ret.left != null)
                ret = ret.left;
            }
        }
        return null;
 */