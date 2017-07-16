package com.l1337.l558;


public class Solution {


// Definition for a QuadTree node.
    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };

    public Node intersect(Node quadTree1, Node quadTree2) {
        //assume there is no null to handles.....
//        if (quadTree1 == null)
//            return quadTree2;
//        if (quadTree2 == null)
//            return quadTree1;

        if (quadTree1.isLeaf) {
            if (quadTree1.val)
                return quadTree1;
            else
                return quadTree2;
        }

        if (quadTree2.isLeaf) {
            if (quadTree2.val)
                return quadTree2;
            else
                return quadTree1;
        }

        //both are non leaf node
        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf) {
            if (topLeft.val && topRight.val && bottomLeft.val && bottomRight.val)
                return new Node(true, true, null, null, null, null);

//            if (!(topLeft.val || topRight.val || bottomLeft.val || bottomRight.val))
//                return new Node(false, true, null, null, null, null);
        }

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
