package com.l1337.l469;


import java.util.List;

public class Solution {

    /*
    [LeetCode] Convex Polygon 凸多边形


Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).

Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True

Explanation:


Example 2:

[[0,0],[0,10],[10,10],[10,0],[5,5]]

Answer: False
     */

//    https://yq.aliyun.com/articles/336560
//    https://blog.csdn.net/magicbean2/article/details/78593338
    /*
    凸多边形的性质, 所有的顶点角都不大于180度
    可以算由三个点组成的一小段曲线的法线方向，而凸多边形的每个三个相邻点的法向量方向都应该相同，要么同正，要么同负。那么我们只要遍历每个点，然后取出其周围的两个点计算法线方向，然后跟之前的方向对比，如果不一样，直接返回false。这里我们要特别注意法向量为0的情况，如果某一个点的法向量算出来为0，那么正确的pre就会被覆盖为0，后面再遇到相反的法向就无法检测出来，所以我们对计算出来法向量为0的情况直接跳过即可，
    遍历顶点，判断相邻三个顶点A、B、C组成的两个向量(AB, AC)的叉积是否同负同正。
     */
    public boolean isConvex(List<List<Integer>> points) {
        return false;

    }
        public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
