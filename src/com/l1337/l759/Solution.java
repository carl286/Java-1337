package com.l1337.l759;


import com.l1337.common.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

    /*
    Employee Free Time
    https://www.lintcode.com/problem/employee-free-time/

    Description
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

The Intervals is an 1d-array. Each two numbers shows an interval. For example, [1,2,8,10] represents that the employee works in [1,2] and [8,10].

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

1.schedule and schedule[i] are lists with lengths in range [1, 100].
2.0 <= schedule[i].start < schedule[i].end <= 10^8.

Example
Example 1:

Input：schedule = [[1,2,5,6],[1,3],[4,10]]
Output：[(3,4)]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:

Input：schedule = [[1,3,6,7],[2,4],[2,5,9,12]]
Output：[(5,6),(7,9)]
Explanation：
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [5, 6], [7, 9],[12,inf].
We discard any intervals that contain inf as they aren't finite
     */

//    https://www.codertrain.co/employee-free-time
    private boolean isOverLap(int [] a, int [] b)
    {
        return !(a[1] < b[0] || b[1] < a[0]);
    }

    public List<int []> employeeFreeTime(int[][] schedule) {
        List<int []> inputs = new ArrayList<>();
        int min_start = Integer.MAX_VALUE, max_end = Integer.MIN_VALUE;
        for(int i = 0; i < schedule.length; ++i)
        {
            min_start = Math.min(min_start, schedule[i][0]);
            max_end = Math.max(max_end, schedule[i][schedule[i].length-1]);
            for(int j = 0; j < schedule[i].length; j += 2)
                inputs.add(new int [] {schedule[i][j],schedule[i][j+1]});
        }
        Collections.sort(inputs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return Integer.compare(a[0],b[0]);
                else
                    return Integer.compare(b[1], a[1]);
            }
        });

        List<int []> mergedIntervals = new ArrayList<>();
        //merge intervals
        for(int i = 0; i < inputs.size(); ++i)
        {
            if (mergedIntervals.isEmpty() || !isOverLap(inputs.get(i), mergedIntervals.get(mergedIntervals.size()-1)))
            {
                mergedIntervals.add(inputs.get(i));
            }
            else
            {
                int [] last = mergedIntervals.get(mergedIntervals.size()-1);
                last[0] = Math.min(last[0], inputs.get(i)[0]);
                last[1] = Math.max(last[1], inputs.get(i)[1]);
            }
        }

        int [] spareInterval = new int [] {1, max_end};
        List<int []> tmp = new ArrayList<>();
        for(int i = 0; i < mergedIntervals.size(); ++i)
        {
            if (spareInterval[0] < mergedIntervals.get(i)[0])
            {
                tmp.add(new int [] {spareInterval[0], mergedIntervals.get(i)[0]});
            }
            spareInterval[0] = mergedIntervals.get(i)[1];
        }

        return tmp;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        int[][] schedule = new int [][] {{1,2,5,6}, {1,3}, {4,10}};
        int[][] schedule = new int [][] {{1,3,6,7}, {2,4}, {2,5,9,12}};
        List<int []> ret = s.employeeFreeTime(schedule);
        for(int i = 0; i < ret.size(); ++i)
        {
            System.out.println(ret.get(i)[0] + "\t" + ret.get(i)[1]);
        }
        System.out.println("Hello World");
    }
}
