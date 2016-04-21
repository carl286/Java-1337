package com.l1337.l327;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

//https://leetcode.com/problems/count-of-range-sum/
//327. Count of Range Sum

//	https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/#introduction
//Basic idea
//        Each integer can be represented as sum of powers of two. In the same way, cumulative frequency can be represented as sum of sets of subfrequencies. In our case, each set contains some successive number of non-overlapping frequencies.

//	http://www.cnblogs.com/zhangshu/archive/2011/08/16/2141396.html
//当 i 为奇数时，ci=ai ；当 i 为偶数时，就要看 i 的因子中最多有二的多少次幂，例如，6 的因子中有 2 的一次幂，等于 2 ，所以 c6=a5+a6（由六向前数两个数的和），4 的因子中有 2 的两次幂，等于 4 ，所以 c4=a1+a2+a3+a4（由四向前数四个数的和）。
//	有公式：cn=a(n-a^k+1)+.........+an（其中 k 为 n 的二进制表示中从右往左数的 0 的个数）。
//		Basically, in this problem, we use BIT to count the number of integers that are less than a specific number.
//		Suppose that a number N = A1B > 0 in binary representation, where B contains all 0 . The array tree is a BIT where tree[N] count the number of integers that are from A0B and A1B - 1 .
//We also know that A0B = N & (N-1) using bit manipulation. (NOTE: on the Topcoder, they use A0B= N - (N & -N) .
//	https://www.hrwhisper.me/binary-indexed-tree-fenwick-tree/
////	https://leetcode.com/problems/count-of-range-sum/
////	https://leetcode.com/discuss/79083/share-my-solution

//	http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
//	http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
//	class countRangeSumSegmentNode {
//		int sum;
//		int left;
//		int right;
//		countRangeSumSegmentNode ln;
//		countRangeSumSegmentNode rn;
//		countRangeSumSegmentNode(int sum, int left, int right, countRangeSumSegmentNode ln, countRangeSumSegmentNode rn) {
//			this.sum = sum;
//			this.left = left;
//			this.right = right;
//			this.ln = ln;
//			this.rn = rn;
//		}
//	}
//	private countRangeSumSegmentNode countRangeSumSegmentTreeBuild(int left, int right) {
//		if (left > right)
//			return null;
//		countRangeSumSegmentNode ret = new countRangeSumSegmentNode(0, left, right, null, null);
//		int mid = (right - left) / 2 + left;
//		ret.ln = countRangeSumSegmentTreeBuild(left, mid);
//		ret.rn = countRangeSumSegmentTreeBuild(mid + 1, right);
//		return ret;
//	}
//
//	private void countRangeSumSegmentUpdate(countRangeSumSegmentNode root, int i, int val) {
//		if (root == null || i < root.left || i > root.right)
//			return;
//		root.sum += val;
//		countRangeSumSegmentUpdate(root.ln, i, val);
//		countRangeSumSegmentUpdate(root.rn, i, val);
//	}
//	private int countRangeSumSegmentQuery(countRangeSumSegmentNode root, int lower, int upper) {
//		if (root == null || lower < root.left || upper > root.right)
//			return 0;
//		if (lower <= root.left && root.right <= upper)
//			return root.sum;
//
//		return countRangeSumSegmentQuery(root.ln, lower, upper) + countRangeSumSegmentQuery(root.rn, lower, upper);
//	}

//	http://bookshadow.com/weblog/2016/01/11/leetcode-count-of-range-sum/
//		Range Sum Query 2D - Mutable, 308
//		https://evanyang.gitbooks.io/leetcode/content/LeetCode/range_sum_query_2d_-_mutable.html
//		http://blog.csdn.net/u012175043/article/details/50093933


//		还记得 Count of Smaller Numbers After Self  么？
//		那时候，我们用Fenwick树或者线段树，先离散化，然后从右向左扫描，每扫描一个数，对小于它的求和。然后更新…..
//		这题也差不多，需要找满足条件 lower ≤ sum[j] – sum[i – 1] ≤ upper ，也就是lower + sum[i – 1] ≤ sum[j] ≤ upper + sum[i – 1]
//		我们同样的求出和，然后离散化，接着从右向左扫描，对每个i 查询满足在[ lower + sum[i – 1], upper + sum[i – 1] ]范围内的个数（用线段树或者Fenwick Tree）
//		这样复杂度就是O(nlogn)

//		http://bookshadow.com/weblog/2016/01/11/leetcode-count-of-range-sum/
//		预处理前n项和数组sums, 将sums数组离散化（排序+去重）得到数组osums,
//		遍历sums，记sumi = sums[i]
//		用二分查找得到[sumi - upper, sumi - lower]的离散化下标[left, right]
//		用树状数组统计范围[left, right]内的元素个数，并累加至最终结果ans
//		若lower <= sumi <= upper，额外地令ans+1
//		将sumi的离散化下标记入树状数组
//		等价于, 对于数组sums中的每一个元素sumi，统计出现在sumi左侧，并且数值在[sumi - upper, sumi - lower]范围内的元素个数。
//		这就等价于统计区间和[0, i]，[1, i]... [i - 1, i]当中所有落在范围[lower, upper]之内的区间个数。

//Merge Sort, https://leetcode.com/discuss/79083/share-my-solution
//		Recall count smaller number after self where we encountered the problem
//		count[i] = count of nums[j] - nums[i] < 0 with j > i
//		Here, after we did the preprocess, we need to solve the problem
//		count[i] = count of a <= S[j] - S[i] <= b with j > i
//				ans = sum(count[:])

class FenwickTree {
    int [] bitarray;
    FenwickTree(int maxN) {
        bitarray = new int [maxN + 1];
    }
    private int lowbit(int x) {
        return x & -x;
    }
    void add(int index, int val) {
        while (index < bitarray.length) {
            bitarray[index] += val;
            index += lowbit(index);
        }
    }
    int sum(int index) {
        int ret = 0;
        while (index > 0) {
            ret += bitarray[index];
            index -= lowbit(index);
        }
        return ret;
    }
}

public class Solution {

    private void addToSet(int data, HashSet<Integer> set, int lower, int upper) {
        if (data <= upper && data >= lower)
            set.add(data);
    }

    int leftMost(long [] nums, long val) {
        int left = -1, right = nums.length;
        while (left + 1 != right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] >= val)
                right = mid;
            else
                left = mid;
        }
        return right;
    }
    //return index satisfies: nums[t] <= val, nums[t+1] > val;
    int rightMost(long [] nums, long val) {
        int left = -1, right = nums.length;
        while (left + 1 != right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] > val)
                right = mid;
            else
                left = mid;
        }
        return left;
    }

//    https://www.hrwhisper.me/leetcode-count-of-range-sum/
//    http://bookshadow.com/weblog/2016/01/11/leetcode-count-of-range-sum/
    public int countRangeSum(int[] nums, int lower, int upper) {
        /*
        //trivial one
        HashSet<Integer> set = new HashSet();
        for (int i = 0; i < nums.length; ++i)
            addToSet(nums[i], set, lower, upper);
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i-1];
            addToSet(nums[i], set, lower, upper);
        }
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j)
                addToSet(nums[j] - nums[i], set, lower, upper);
        }

        return set.size();
        */

        if (nums == null || nums.length <= 0)
            return 0;

        HashSet<Long> set = new HashSet();

        //so that our sum will not overflow...
        long [] org = new long[nums.length];
        org[0] = nums[0];
        set.add(org[0]);
        for (int i = 1; i < nums.length; i++) {
            org[i] = org[i-1] + nums[i];
            set.add(org[i]);
        }
        long [] osnum = new long[set.size()];
        Iterator<Long> i = set.iterator();
        int k = 0;
        while (i.hasNext()) {
            osnum[k++] = i.next();
        }
        //减少sort的负担????
        Arrays.sort(osnum);
        FenwickTree ft = new FenwickTree(osnum.length);
        int ret = 0;
        for (long s : org) {
			/* Java is not as convenient as Python
			int left = Arrays.binarySearch(osnum, s - upper);
			int right = Arrays.binarySearch(osnum, s - lower);
			*/
            int left = leftMost(osnum, s - upper);
            int right = rightMost(osnum, s - lower);
            if (s <= upper && s >= lower)
                ret++;
            if (left < osnum.length && right >= 0)
                ret += (ft.sum(right+1) - ft.sum(left));

            ft.add(rightMost(osnum, s)+1, 1);
//			System.out.println(s);
        }
        return ret;
    }
    public static void main(String [] args) {
        int [] nums = {-2, 5, -1};
        int lower = -2, upper = 2;
        Solution s = new Solution();
        System.out.println(s.countRangeSum(nums,lower,upper));
    }
}
