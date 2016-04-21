package com.googlePrep;

/**
 * Created by Ke.Liu on 6/10/16.
 */
class BstHeapNode {
    int bstVal;
    int heapVal;
    BstHeapNode left;
    BstHeapNode right;
    BstHeapNode(int bstVal, int heapVal) {
        this.bstVal = bstVal;
        this.heapVal = heapVal;
        left = null;
        right = null;
    }
}
public class BstHeap {
    BstHeapNode root;
    public BstHeap() {
        root = null;
    }

    public BstHeapNode addHelper(BstHeapNode root, BstHeapNode node) {
        if (root == null)
            return node;

        if (node.heapVal > root.heapVal) {
            //insert by bst val
            if (root.bstVal < node.bstVal) {
                node.left = root;
            } else {
                node.right = root;
            }
            return node;
        } else {
            if (root.bstVal < node.bstVal)
                root.right = addHelper(root.right, node);
            else
                root.left = addHelper(root.left, node);
            return root;
        }
    }
    public void add(BstHeapNode node) {
        root = addHelper(root, node);
    }
}
