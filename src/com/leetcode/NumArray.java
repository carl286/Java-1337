package com.leetcode;

//    https://leetcode.com/problems/range-sum-query-mutable/
// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);

//https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/#introduction
//http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
//An array representation of tree is used to represent Segment Trees. For each node at index i, the left child is at index 2*i+1, right child at 2*i+2 and the parent (i-1)/2
public class NumArray {

    /* Using BIT
    int [] array;
    int [] clone;

    private int bitRead(int index) {
        int sum = 0;
        while (index > 0) {
            sum += array[index];
            index -= (index & -index);
        }
        return sum;
    }

    private int bitReadSingle(int index) {
        int sum = array[index];
        if (index > 0) {
            int z = index - (index & -index);
            --index;
            while (index != z) {
                sum -= array[index];
                index -= (index & -index);
            }
        }
        return sum;
    }

    private void bitUpdate(int index, int val) {
        while (index < array.length) {
            array[index] += val;
            index += (index & -index);
        }
//        for (int i : array)
//            System.out.print(i);
//        System.out.println();
    }

    public NumArray(int[] nums) {
        if (nums == null || nums.length <= 0) {
            array = null;
            clone = null;
            return;
        }
        array = new int [1 + nums.length];
        clone = nums.clone();
//        for (int i = 0; i < nums.length; i++)
        for (int i = nums.length - 1; i >= 0; --i)
            bitUpdate(i+1, nums[i]);
//        for (int i : array)
//            System.out.print(i);
//        System.out.println();
    }

    void update(int i, int val) {
        if (array == null || i + 1 >= array.length)
            return;
//        bitUpdate(i+1, val);

        //We could avoid this single read by recordin orignal array
    //        TLE
    //        int delta = val - bitReadSingle(i+1);
    //        if (delta != 0)
    //            bitUpdate(i+1, delta);

        int delta = val - clone[i];
        //Please remember to update clone array...
        clone[i] = val;
        if (delta != 0)
            bitUpdate(i+1, delta);
    }

    public int sumRange(int i, int j) {
        if (i + 1 >= array.length || j + 1 >= array.length)
            return 0;
        return bitRead(j + 1) - bitRead(i);
    }

    public static void main(String [] args) {
        int nums [] = {1, 3, 5};
        NumArray nm = new NumArray(nums);
        System.out.println(nm.sumRange(0, 2));
        nm.update(1, 2);
        System.out.println(nm.sumRange(0, 2));
    }
    */

    //If a node index is i, 2i+1 is left child, 2i+2 is right child, (i-1)/2 is parent
    class countRangeSumSegmentNode {
        int sum;
        int left;
        int right;
        countRangeSumSegmentNode ln;
        countRangeSumSegmentNode rn;
        countRangeSumSegmentNode(int sum, int left, int right, countRangeSumSegmentNode ln, countRangeSumSegmentNode rn) {
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.ln = ln;
            this.rn = rn;
        }
    }
    private countRangeSumSegmentNode countRangeSumSegmentTreeBuild(int left, int right) {
        if (left > right)
            return null;
        countRangeSumSegmentNode ret = new countRangeSumSegmentNode(0, left, right, null, null);
        int mid = (right - left) / 2 + left;
        ret.ln = countRangeSumSegmentTreeBuild(left, mid);
        ret.rn = countRangeSumSegmentTreeBuild(mid + 1, right);
        return ret;
    }

    private void countRangeSumSegmentUpdate(countRangeSumSegmentNode root, int i, int val) {
        if (root == null || i < root.left || i > root.right)
            return;
        root.sum += val;
        countRangeSumSegmentUpdate(root.ln, i, val);
        countRangeSumSegmentUpdate(root.rn, i, val);
    }
    private int countRangeSumSegmentQuery(countRangeSumSegmentNode root, int lower, int upper) {
        if (root == null || lower < root.left || upper > root.right)
            return 0;
        if (lower <= root.left && root.right <= upper)
            return root.sum;

        return countRangeSumSegmentQuery(root.ln, lower, upper) + countRangeSumSegmentQuery(root.rn, lower, upper);
    }

    countRangeSumSegmentNode root;
    int orignal;
    public NumArray(int[] nums) {

    }
    void update(int i, int val) {

    }
    public int sumRange(int i, int j) {
        return 0;
    }

    public static void main(String [] args) {
        int nums [] = {1, 3, 5};
        NumArray nm = new NumArray(nums);
        System.out.println(nm.sumRange(0, 2));
        nm.update(1, 2);
        System.out.println(nm.sumRange(0, 2));
    }


    //Using segment tree
}

/*
  Blogs: segment tree
  Segment tree is the most useful data structure and every problem solvable by Fenwick is also solvable by Segment tree.
  In computer science, a segment tree is a tree data structure for storing intervals, or segments. It allows querying which of the stored segments contain a given point. It is, in principle, a static structure; that is, its structure cannot be modified once it is built. A similar data structure is the interval tree.

 */