package com.l1337.l493;


public class Solution {
    boolean f(int a, int b) {
        Long long2 = 2l;
        return a > long2 * b;
    }

//    [i1, e1)
    public int mergeSort(int nums[], int i1, int e1, int [] buf) {
        if (i1 + 1 >= e1) {
            return 0;
        }

        //has a least 1 elemets
        int mid1 = (e1 - i1) / 2 + i1;

        int res1 = mergeSort(nums, i1, mid1, buf);
        int res2 = mergeSort(nums, mid1, e1, buf);

        int index = 0;
        //merge
        int j1 = i1, j2 = mid1;
//        while (j1 < mid1 || j2 < e1) {
//            if ((j1 < mid1) && ( j2 >= e1 || nums[j1] < nums[j2])) {
//                buf[index++] = nums[j1++];
//            } else {
//                buf[index++] = nums[j2++];
//            }
//        }

        int res = 0;

        while (j1 < mid1 && j2 < e1) {
            if (f(nums[j1], nums[j2])) {
                res += (mid1 - j1);
                ++j2;
            } else {
                ++j1;
            }
        }

        j1 = i1;
        j2 = mid1;
        while (j1 < mid1 && j2 < e1) {
            if (nums[j1] < nums[j2]) {
                buf[index++] = nums[j1++];
            } else {
                buf[index++] = nums[j2++];
            }
        }

        while (j1 < mid1) {
            buf[index++] = nums[j1++];
        }

        while (j2 < e1) {
            buf[index++] = nums[j2++];
        }

        for (int i = i1; i < e1; ++i) {
            nums[i] = buf[i - i1];
        }

        return res1 + res2 + res;
    }

    //BF
    public int reversePairs(int[] nums) {

        int ret =  mergeSort(nums, 0, nums.length, new int[nums.length]);

        for (int i = 0; i < nums.length; ++i) {
            System.out.print(nums[i] + "\t");
        }

        System.out.println();
        return ret;
    }

    public int reversePairsBF(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] > (long) 2*nums[j]) {
                    ++count;
                }
            }
        }

        return count;
    }

//    https://leetcode.com/problems/reverse-pairs/discuss/157302/C++-solutions-Easy-to-understandbeat-90.73BIT

//    https://leetcode.com/problems/reverse-pairs/discuss/144333/JAVA-20-lines-beats-99-mergeSort
//    https://leetcode.com/problems/reverse-pairs/discuss/97272/Clean-Java-Solution-using-Enhanced-Binary-Search-Tree


    //poorly feedback for below document
//    https://leetcode.com/articles/reverse-pairs/
    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.reversePairsBF(new int [] {2147483647, 2147483647}));
//        int [] nums = new int [] {2147483647, 2147483647};
//        int [] nums = new int [] {4,5,6, 1,2,3};
        int [] nums = new int [] {2,4,3,5,1};
        System.out.println(s.reversePairs(nums));

//        System.out.println(2147483647*2);
    }
}
