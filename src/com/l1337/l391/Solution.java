package com.l1337.l391;


import com.l1337.common.Interval;

import java.util.*;


//http://www.cnblogs.com/grandyang/p/5825619.html
//https://discuss.leetcode.com/topic/55923/o-n-solution-by-counting-corners-with-detailed-explaination
//https://discuss.leetcode.com/topic/55944/o-n-log-n-sweep-line-solution

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int hashCode() {
        int ret = 17;
        ret = 31 * ret + x;
        ret = 31 * ret + y;
        return ret;
    }
    public boolean equals(Object that) {
        Node n = (Node) that;
        return n.x == x && n.y == y;
    }
}
public class Solution {

    //How do you think about this problem
    //1. some random thoughts
    //2. interval tree to reduce complexity
    //3. how do you do if this is one diemnsion
    //
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length < 1)
            return false;

        Arrays.sort(rectangles, (a, b) -> {
            for (int i = 0; i < a.length; ++i) {
                if (a[i] != b[i])
                    return a[i] < b[i] ? -1 : +1;
            }
           return 0;
        });

        TreeMap<Integer, Interval> map = new TreeMap<>();
        //key is the height, interval is how the interval covered at this height

        //let's do the start outside
        map.put(rectangles[0][1], new Interval(rectangles[0][0], rectangles[0][2]));
        map.put(rectangles[0][3], new Interval(rectangles[0][0], rectangles[0][2]));
        //to represent a rectangular, we use the interval in lower height to represent the ranges

        int firstKey = rectangles[0][1], lastKey = rectangles[0][3];

        for (int i = 1; i < rectangles.length; ++i) {
            //add rectangles[i][1] -> new Interval(rectangles[i][0], rectangles[i][2]
            //add rectangles[i][3] -> new Interval(rectangles[i][0], rectangles[i][2]
            if (rectangles[i][1] <= lastKey) {
                Map.Entry<Integer, Interval> floor1 = map.floorEntry(rectangles[i][1]);
                Map.Entry<Integer, Interval> ceiling1 = map.ceilingEntry(rectangles[i][1]);
                Map.Entry<Integer, Interval> floor2 = map.floorEntry(rectangles[i][3]);
                Map.Entry<Integer, Interval> ceiling2 = map.ceilingEntry(rectangles[i][3]);
            } else {
                return false;
            }
        }

        //once clear, go through the map to check covered range.
        Interval range = map.get(lastKey);
        for (Interval value: map.values())
            if (!value.equals(range))
                return false;
        return true;
    }


    public boolean isRectangleCoverII(int[][] rectangles) {
        if (rectangles == null || rectangles.length < 1)
            return false;

        HashMap<Node, Integer> map = new HashMap<>();
        Node [] na = new Node[4];
        int [] mask = {1,2,4,8};
        int [] interiorMasks = {3,9,12,6,15};
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int i = 0; i < rectangles.length; ++i) {
            na[0] = new Node(rectangles[i][0], rectangles[i][1]);//1
            na[1] = new Node(rectangles[i][0], rectangles[i][3]);//2
            na[2] = new Node(rectangles[i][2], rectangles[i][3]);//4
            na[3] = new Node(rectangles[i][2], rectangles[i][1]);//8

            for (int j = 0; j < na.length; ++j) {
                if (!map.containsKey(na[j])) {
                    map.put(na[j], mask[j]);
                } else if ((map.get(na[j]) & mask[j]) == 0) {
                    map.put(na[j], map.get(na[j]) | mask[j]);
                } else {
                    return false;
                }
            }
            minX = Math.min(minX, rectangles[i][0]);
            maxX = Math.max(maxX, rectangles[i][2]);
            minY = Math.min(minY, rectangles[i][1]);
            maxY = Math.max(maxY, rectangles[i][3]);
        }

        //check corners and boundarys
        int cnt = 0;
        Iterator<Node> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Node cur = iterator.next();
            int val = map.get(cur);
            if ((cur.x == minX || cur.x == maxX) && (cur.y == minY || cur.y == maxY)) {
                int k = 0;
                while (k < mask.length) {
                    if (val == mask[k])
                        break;
                    else
                        ++k;
                }
                if (k == mask.length)
                    return false;
                ++cnt;
            } else {
                int k = 0;
                while (k < interiorMasks.length) {
                    if (val == interiorMasks[k])
                        break;
                    else
                        ++k;
                }
                if (k == interiorMasks.length)
                    return false;
            }
        }
        return cnt == 4;
    }


    /*
    下面这种方法也相当的巧妙， 提出这种算法的大神细心的发现了每种点的规律，
    每个绿点其实都是两个顶点的重合，每个红点都是四个顶点的重合，而每个蓝点只有一个顶点
    有了这条神奇的性质就不用再去判断“每个点最多只能是一个矩形的左下，左上，右上，或右下顶点”这条性质了
    我们直接用一个set，对于遍历到的任意一个顶点，
    如果set中已经存在了，则删去这个点，如果没有就加上，这样最后会把绿点和红点都滤去，剩下的都是蓝点，
    我们只要看蓝点的个数是否为四个，再加上检测每个矩形面积累加和要等于最后的大矩形的面积即可
     */

    /*
    This algorithm is wrong, see counter example....
    [[0,0,1,1],[0,0,2,1],[1,0,2,1],[0,2,2,3]]
     */
    public boolean isRectangleCoverIII(int[][] rectangles) {
        if (rectangles == null || rectangles.length < 1)
            return false;

        HashSet<Node> set = new HashSet<>();
        Node[] na = new Node[4];
        int subSize = 0;
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int i = 0; i < rectangles.length; ++i) {
            subSize += (rectangles[i][2] - rectangles[i][0]) * (rectangles[i][3] - rectangles[i][1]);
            na[0] = new Node(rectangles[i][0], rectangles[i][1]);//1
            na[1] = new Node(rectangles[i][0], rectangles[i][3]);//2
            na[2] = new Node(rectangles[i][2], rectangles[i][3]);//4
            na[3] = new Node(rectangles[i][2], rectangles[i][1]);//8

            for (int j = 0; j < na.length; ++j) {
                if (set.contains(na[j]))
                    set.remove(na[j]);
                else
                    set.add(na[j]);
            }
            minX = Math.min(minX, rectangles[i][0]);
            maxX = Math.max(maxX, rectangles[i][2]);
            minY = Math.min(minY, rectangles[i][1]);
            maxY = Math.max(maxY, rectangles[i][3]);
        }
        return set.size() == 4 && (subSize == ((maxX - minX)*(maxY - minY)));
    }
    public static void main(String [] args) {
        int [][] rectangles = {

                {1,1,3,3},
                {3,1,4,2},
        {3,2,4,4},
            {1,3,2,4},
                {2,3,3,4}


                /*
                {1,1,2,3},
        {1,3,2,4},
            {3,1,4,2},
                {3,2,4,4}
                */

//                {0,0,4,1},{0,0,4,1}
        };
        Solution s = new Solution();

        System.out.println(s.isRectangleCoverII(rectangles));
    }
}
