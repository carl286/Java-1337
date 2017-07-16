package com.l1337.meetingRooms;


import com.l1337.common.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


class IntervalComparator implements Comparator<Interval> {
    public int compare(Interval e1, Interval e2) {
        return e1.start - e2.start;
    }
}

public class Solution {

    //	Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
//	For example,
//	Given [[0, 30],[5, 10],[15, 20]],
//			return false.
    public boolean canAttendMeetingsI(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1)
            return true;
        Arrays.sort(intervals, new IntervalComparator());
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].start >= intervals[i-1].end)
                continue;
            else
                return false;
        }

        return true;
    }

    //	Meeting Rooms II, 253
//	Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
// find the minimum number of conference rooms required.
//	For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
//    http://www.cnblogs.com/grandyang/p/5244720.html, at least so3 is wrong.
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length <= 0)
            return 0;
        Arrays.sort(intervals, new IntervalComparator());
        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return Integer.compare(a.end, b.end);
            }
        });

        int ret = 1;
        for (int i = 0; i < intervals.length; ++i) {
            while (!pq.isEmpty() && pq.peek().end <= intervals[i].start)
                pq.poll();
            pq.add(intervals[i]);
            ret = Math.max(ret, pq.size());
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        //	Given [[0, 30],[5, 10],[15, 20]],
//        Interval t1 = new Interval(0,30);
//        Interval t2 = new Interval(5,10);
//        Interval t3 = new Interval(15,20);

        Interval t1 = new Interval(1,7);
        Interval t2 = new Interval(1,7);
        Interval t3 = new Interval(4,5);

        Interval[] intervals = {t1,t2,t3};
//        System.out.println(s.canAttendMeetingsI(intervals));
        System.out.println(s.minMeetingRooms(intervals));

    }
}


//	https://leetcode.com/discuss/50783/java-ac-code-using-comparator
//When pq poll, it might need a while loop to do so...
//	http://buttercola.blogspot.com/2015/08/leetcode-meeting-rooms-ii.html
//Actually, when you poll, you don't care about start any more and you can use a regular pq.