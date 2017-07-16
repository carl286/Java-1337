package com.l1337.l252;


import com.l1337.common.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    /*
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
     */

//    https://www.jiuzhang.com/problem/meeting-rooms/
    // https://www.cnblogs.com/grandyang/p/5240774.html
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int []>() {
            public int compare(int [] a, int [] b) {
                if (a[0] < b[0])
                    return -1;
                else
                    return Integer.compare(a[1], b[1]);
            }});

        boolean canAttend = true;
        for (int i = 1; i < intervals.length && canAttend; ++i)
        {
            if (intervals[i-1][1] > intervals[i][0])
            {
                canAttend = false;
            }
        }
        return canAttend;
    }

//    https://www.jiuzhang.com/problem/meeting-rooms-ii/
    /*
    给定一系列的会议时间间隔intervals，包括起始和结束时间[[s1,e1],[s2,e2],...] (si < ei)，找到所需的最小的会议室数量。

样例
样例1

输入: intervals = [(0,30),(5,10),(15,20)]
输出: 2
解释:
需要两个会议室
会议室1:(0,30)
会议室2:(5,10),(15,20)
样例2

输入: intervals = [(2,7)]
输出: 1
解释:
只需要1个会议室就够了
     */
//    https://www.programcreek.com/2014/05/leetcode-meeting-rooms-ii-java/
    /*
    When a room is taken, the room can not be used for anther meeting until the current meeting is over. As soon as the current meeting is finished, the room can be used for another meeting. We can sort the meetings by start timestamps and sequentially assign each meeting to a room. Each time when we assign a room for a meeting, we check if any meeting is finished so that the room can be reused.
    In order to efficiently track the earliest ending meeting, we can use a min heap.
    Whenever an old meeting ends before a new meeting starts, we reuse the room (i.e., do not add more room). Otherwise, we need an extra room (i.e., add a room).
     */
public int minMeetingRooms2(int[][] intervals) {
//    Arrays.sort(intervals, new Comparator<int[]>() {
//        @Override
//        public int compare(int[] a, int[] b) {
//            return Integer.compare(a[0], b[0]);
//        }
//    });

    Arrays.sort(intervals, Comparator.comparing((int[] itv) -> itv[0]));

    PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return Integer.compare(a, b);
        }
    });
    int ret = 0;

    for (int i = 0; i < intervals.length; ++i)
    {
        while (!pq.isEmpty() && intervals[i][0] >= pq.peek())
        {
            pq.poll();
        }
        pq.offer(intervals[i][1]);
        ret = Math.max(ret, pq.size());
    }
    return ret;
}


    /*
    Inspiring solution https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda
Put start time and end time into two arrays and get them sorted
Basically, it use a pointer to maintain minimum value of next end time as a simulation of poll() element in PriorityQueue
Beats over 100% submissions
     */
//    http://happycoding2010.blogspot.com/2015/11/leetcode-253-meeting-rooms-ii.html
//    https://www.jiuzhang.com/problem/meeting-rooms-ii/
    public int minMeetingRooms2(Interval[] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i=0; i<intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int room = 0, ptr = 0;
        for (int i=0; i<start.length; i++) {
            if (start[i] < end[ptr]) {
                room ++;
            }
            else {
                ptr ++;
            }
        }
        return room;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
