package com.l1337.l480;


import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
//    https://leetcode.com/problems/sliding-window-median/discuss/96339/Java-clean-and-easily-readable-solution-with-a-helper-class
//    https://leetcode.com/problems/sliding-window-median/discuss/129262/Java-Self-explanatory-Code-Using-Heaps
    //See above examples on how to maintain the invariant of the two pq sizes....
    void InsertToPq(int x, int y, PriorityQueue<Integer> bigPq, int bigPqCapacity, PriorityQueue<Integer> littlePq, int littlePqCapacity) {
        if (bigPq.size() < bigPqCapacity) {
            bigPq.add(x);
            return;
        }
        //bigPq.size() >= bigPqCapacity
        if (littlePq.size() >= littlePqCapacity) {
            //must remove y before insert
            if (bigPq.remove(y)) {
                //removed from bigPq
                if (x <= littlePq.peek()) {
                    bigPq.add(x);
                } else {
                    bigPq.add(littlePq.poll());
                    littlePq.add(x);
                }
            } else {
                //removed from littlePq
                littlePq.remove(y);
                if (x >= bigPq.peek()) {
                    littlePq.add(x);
                } else {
                    littlePq.add(bigPq.poll());
                    bigPq.add(x);
                }
            }
        } else {
            //no remove is needed
            if (bigPq.peek() <= x) {
                littlePq.add(x);
            } else {
                littlePq.add(bigPq.poll());
                bigPq.add(x);
            }
        }
    }
    //assert bigPq.size() <= littlePq.size() && bigPq.size() + 1 >= littlePq.size()
    double getMedian(PriorityQueue<Integer> bigPq, PriorityQueue<Integer> littlePq) {
        if (bigPq.size() != littlePq.size()) {
            return littlePq.peek();
        } else {
            return ((double)(bigPq.peek()) + littlePq.peek()) / 2;
        }
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length < k || k <= 0)
            return null;

        double[] ret = new double[nums.length - k + 1];
        if (k == 1) {
            for (int i = 0; i < nums.length; ++i)
                ret[i] = nums[i];
        } else {
            int bigPqCapacity = k / 2;
            int littlePqCapacity = k - bigPqCapacity;
            PriorityQueue<Integer> bigPq = new PriorityQueue<Integer>(bigPqCapacity, (a,b) -> Integer.compare(b, a));
            PriorityQueue<Integer> littlePq = new PriorityQueue<Integer>(littlePqCapacity);
            int i = 0;
            for (int idx = 0; idx < nums.length; ++idx) {
                if (idx < k) {
                    InsertToPq(nums[idx], 0, bigPq, bigPqCapacity, littlePq, littlePqCapacity);
                } else {
                    ret[i++] = getMedian(bigPq, littlePq);
                    InsertToPq(nums[idx], nums[idx-k], bigPq, bigPqCapacity, littlePq, littlePqCapacity);
                }
            }
            ret[i++] = getMedian(bigPq, littlePq);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
