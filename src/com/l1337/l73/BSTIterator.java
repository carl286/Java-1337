package com.l1337.l73;

import com.l1337.common.TreeNode;

import java.util.Stack;

public class BSTIterator {

    public void setZeroes(int[][] matrix) {
        int x = -1, y = -1;
        boolean found = false;
        // find first 0 values
        for (int i = 0; i < matrix.length && !found; ++i)
            for (int j = 0; j < matrix[0].length && !found; ++j)
                if (matrix[i][j] == 0) {
                    x = i;
                    y = j;
                    found = true;
                }

        //row x in [0...matrix.length]
        //col y in [0...matrix[0].length]

        if (x >= 0) {
            // should skip y
            for (int i = 0; i < matrix[0].length; ++i) {
                if (matrix[x][i] != 0) {
                    //search column 0s
                    for (int k = 0; k < matrix.length; ++k) {
                        if (matrix[k][i] == 0) {
                            matrix[x][i] = 0;
                            break;
                        }
                    }
                }
            }

            // search rows
            for (int i = x + 1; i < matrix.length; ++i) {
                if (matrix[i][y] != 0) {
                    for (int j = 0; j < matrix[0].length; ++j)
                        if (matrix[i][j] == 0) {
                            matrix[i][y] = 0;
                            break;
                        }
                }
            }

            // set 0s
            for (int i = x + 1; i < matrix.length; ++i)
                if (matrix[i][y] == 0) {
                    for (int j = 0; j < matrix[0].length; ++j) {
                        matrix[i][j] = 0;
                    }
                }

            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[x][j] == 0) {
                    for (int i = 0; i < matrix.length; ++i)
                        matrix[i][j] = 0;
                }
            }

            for (int i = 0; i < matrix[0].length; ++i)
                matrix[x][i] = 0;
        }
    }

    private Stack<TreeNode> st = new Stack<>();
    private TreeNode cur = null;

    public BSTIterator(TreeNode root) {
        //assume root is not null here
        cur = root;
        if (cur != null) {
            while (cur.left != null) {
                st.push(cur);
                cur = cur.left;
            }
        }
    }

    public int next() {
        int ret = Integer.MIN_VALUE;
        if (cur != null) {
            ret = cur.val;
            if (cur.right != null) {
                cur = cur.right;
                while (cur.left != null) {
                    st.push(cur);
                    cur = cur.left;
                }
            } else if (!st.isEmpty()) {
                cur = st.pop();
            } else {
                cur = null;
            }
        }
//        else
//        {
//            cur = st.pop();
//            ret = cur.val;
//            while (cur != null)
//            {
//                st.push(cur);
//                cur = cur.left;
//            }
//        }

        return ret;
    }

    public boolean hasNext() {
        return cur != null;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n3.left = n2;
        n3.right = n4;
        n2.left = n1;
        BSTIterator iterator = new BSTIterator(n3);
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());

    }
}
