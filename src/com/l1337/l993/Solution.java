package com.l1337.l993;


import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public boolean isCousins(TreeNode root, int x, int y) {
//        if(root == null)
//            return false;

        if (root.val == x || root.val == y)
            return false;
        List<TreeNode> cur = new ArrayList<>();
        cur.add(root);

        while (!cur.isEmpty())
        {
            List<TreeNode> next = new ArrayList<>();
            TreeNode px = null, py = null;
            for(int i = 0; i < cur.size(); ++i)
            {
                if(cur.get(i).left != null)
                {
                    next.add(cur.get(i).left);
                    if(cur.get(i).left.val == x)
                    {
                        px = cur.get(i);
                    }
                    if(cur.get(i).left.val == y)
                    {
                        py = cur.get(i);
                    }
                }

                if(cur.get(i).right != null)
                {
                    next.add(cur.get(i).right);
                    if(cur.get(i).right.val == x)
                    {
                        px = cur.get(i);
                    }
                    if(cur.get(i).right.val == y)
                    {
                        py = cur.get(i);
                    }
                }
            }

            if (px != null || py != null)
            {
                if (px != null && py != null && px != py)
                    return true;
                return false;
            }
            cur = next;
        }


        return false;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
