package com.l1337.l307;

//https://leetcode.com/articles/range-sum-query-mutable/#approach-3-segment-tree-accepted
public class NumArray {


    /*
    //BIT
    //assume no overflow....
    int [] bits;
    int [] copy;

    private int lowBit(int index) {
        return index & -index;
    }

    private void updateBitTree(int index, int val) {
        while (index < bits.length) {
            bits[index] += val;
            index += lowBit(index);
        }
    }
    private int queryBit(int index) {
        int sum = 0;
        while (index > 0) {
            sum += bits[index];
            index -= lowBit(index);
        }
        return sum;
    }

    //BIT tree
    NumArray(int [] nums) {
        bits = new int [nums.length + 1];
        copy = new int [nums.length];
        for (int i = 0; i < nums.length; ++i) {
            updateBitTree(i + 1, nums[i]);
        }
        System.arraycopy(nums, 0, copy, 0, copy.length);
    }

    void update(int i, int val) {
        int delta = val - copy[i];
        if (delta != 0) {
            updateBitTree(i+1, delta);
            copy[i] += delta;
        }
    }

    int sumRange(int i, int j) {
        return queryBit(j+1) - queryBit(i);
    }
    */

    //Let's do it with interval tree


    //Below Code TLE
    /*
    static class NumArrayNode {
        NumArrayNode left;
        NumArrayNode right;
        int l;
        int r;
        int total;
        NumArrayNode(int l, int r, int val) {
            this.l = l;
            this.r = r;
            total = val;
        }
    }

    void updateNode(NumArrayNode root, int index, int val) {
        if (root == null || root.r < index || root.l > index)
            return;
        root.total += val;
        updateNode(root.left, index, val);
        updateNode(root.right, index, val);
    }

    private int [] copy;
    private int sum = 0;
    private NumArrayNode root;
    void query(NumArrayNode root, int left, int right) {
        if (root == null || root.r < left || root.l > right)
            return;

        if (root.l >= left && root.r <= right) {
            sum += root.total;
            return;
        }

        query(root.left, left, right);
        query(root.right, left, right);
    }

    NumArrayNode build(int [] nums, int l, int r) {
        if (l > r)
            return null;
        int mid = l + (r - l) / 2;
        NumArrayNode root = new NumArrayNode(l, r, 0);
        if (l == r) {
            root.total = nums[l];
            return root;
        }

        NumArrayNode left = build(nums, l, mid);
        NumArrayNode right = build (nums, mid+1, r);
        root.left = left;
        root.right = right;
        if (left != null)
            root.total += left.total;
        if (right != null)
            root.total += right.total;
        return root;
    }

    NumArray(int [] nums) {
        copy = new int [nums.length];
        System.arraycopy(nums,0,copy,0,copy.length);
        root = build(nums, 0, nums.length-1);
    }

    void update(int i, int val) {
        int delta = val - copy[i];
        if (delta != 0) {
            updateNode(root, i, delta);
            copy[i] += delta;
        }
    }

    int sumRange(int i, int j) {
        sum = 0;
        query(root, i, j);
        return sum;
    }
    */
    int[] tree;
    int n;
    public NumArray(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }

    //Becareful about the below index usage, 0 is not used here
    private void buildTree(int[] nums) {
        for (int i = n, j = 0;  i < 2 * n; i++,  j++)
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }


    void update(int pos, int val) {
        pos += n;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos;
            int right = pos;
            if (pos % 2 == 0) {
                right = pos + 1;
            } else {
                left = pos - 1;
            }
            // parent is updated after child is updated
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    public int sumRange(int l, int r) {
        // get leaf with value 'l'
        l += n;
        // get leaf with value 'r'
        r += n;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) {
                sum += tree[l];
                l++;
            }
            if ((r % 2) == 0) {
                sum += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }

    public static void main(String [] args) {
        int [] nums = {1, 3, 5};
        NumArray s = new NumArray(nums);
        System.out.println(s.sumRange(0, 2));
        s.update(1, 2);
        System.out.println(s.sumRange(0, 2));
        System.out.println("Hello World");
    }
}



//https://en.wikipedia.org/wiki/Segment_tree
//http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
//http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
// Segment tree could be implemented using either an array or a tree.
// For an array implementation, if the element at index ii is not a leaf, its left and right child are stored at index 2i2i and 2i + 12i+1 respectively.


//304	Range Sum Query 2D - Immutable
//https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
//		https://evanyang.gitbooks.io/leetcode/content/LeetCode/range_sum_query_2d_-_mutable.html
//		http://blog.csdn.net/u012175043/article/details/50093933