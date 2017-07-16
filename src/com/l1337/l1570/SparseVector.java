package com.l1337.l1570;


import java.util.HashMap;
import java.util.Map;

public class SparseVector {

//    https://leetcode.jp/problemdetail.php?id=1570
//    Dot Product of Two Sparse Vectors
    /*
    给定两个稀疏向量，计算它们的点积（数量积）。

实现类 SparseVector：

SparseVector(nums) 以向量 nums 初始化对象。
dotProduct(vec) 计算此向量与 vec 的点积。
稀疏向量 是指绝大多数分量为 0 的向量。你需要 高效 地存储这个向量，并计算两个稀疏向量的点积。
示例1
输入：nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
输出：8
解释：v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
示例2
输入：nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
输出：0
解释：v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 00 + 10 + 00 + 00 + 0*2 = 0
示例3
输入：nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
输出：6
————————————————

Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

Example 1:

Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
Example 2:

Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
Example 3:

Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6
Constraints:

n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
     */

    private Map<Integer, Integer> map;
    SparseVector(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            //have you blown your mind????
            map.put(i, nums[i]);
    }

    public int dotProduct(SparseVector vec) {
        int length1 = this.map.size();
        int length2 = vec.map.size();
        SparseVector left, right;

//        https://leetcode.ca/2020-03-18-1570-Dot-Product-of-Two-Sparse-Vectors/
        /*
        if (indexToNum.size() < vec.indexToNum.size()) {
                return vec.dotProduct(this);
            }
         */
        if (length1 <= length2)
        {
            left = this;
            right = vec;
        }
        else
        {
            left = vec;
            right = this;
        }

        int ret = 0;
        for(Map.Entry<Integer, Integer> entry : left.map.entrySet())
        {
            if (right.map.containsKey(entry.getKey()))
                ret += entry.getValue() * right.map.getOrDefault(entry.getKey(), 0);
        }
        return ret;
    }
    public static void main(String [] args) {
        SparseVector s = new SparseVector(new int [] {0,1,0,0,0});
        SparseVector s2 = new SparseVector(new int [] {0,0,0,0,2});
        System.out.println(s.dotProduct(s2));

        SparseVector s3 = new SparseVector(new int [] {0,1,0,0,2,0,0});
        SparseVector s4 = new SparseVector(new int [] {1,0,0,0,3,0,4});
        System.out.println(s3.dotProduct(s4));
    }
}
