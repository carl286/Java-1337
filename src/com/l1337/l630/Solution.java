package com.l1337.l630;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Solution {


    //TLE
    private int dfs(int startTime, Set<Integer> coursesTake, int[][] courses) {

        int ret = 0;
        for (int i = 0; i < courses.length; ++i) {
            if (!coursesTake.contains(i) && startTime + courses[i][0] <= courses[i][1]) {
                coursesTake.add(i);
                ret = Math.max(ret, 1 + dfs(startTime + courses[i][0], coursesTake, courses));
                coursesTake.remove(i);
            }
        }
        return ret;
    }

    private String buildKey(int startTime, TreeSet<Integer> coursesTake) {
        String sb = Integer.toString(startTime) + "#";
        Iterator<Integer> iter = coursesTake.iterator();
        while (iter.hasNext()) {
            sb += iter.next() + "#";
        }
        return sb;
    }
    private int dfsWithMemo(int startTime, TreeSet<Integer> coursesTake, int[][] courses, Map<String, Integer> memo) {
        String key = buildKey(startTime, coursesTake);
        if (memo.containsKey(key))
            return memo.get(key);

        int ret = 0;
        for (int i = 0; i < courses.length; ++i) {
            if (!coursesTake.contains(i) && startTime + courses[i][0] <= courses[i][1]) {
                coursesTake.add(i);
                ret = Math.max(ret, 1 + dfs(startTime + courses[i][0], coursesTake, courses));
                coursesTake.remove(i);
            }
        }

        memo.put(key, ret);
        return ret;
    }


    private int dp(int[][] courses) {



        return 0;
    }

//    https://leetcode.com/articles/course-schedule-iii/


    /*
    we can conclude that it is always profitable to take the course with a smaller end day prior to a course with a larger end day
     */
    public int scheduleCourse(int[][] courses) {

//        return dfs(0, new HashSet<>(), courses);
//        return dfsWithMemo(0, new TreeSet<Integer>(), courses, new HashMap<>());
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue< Integer > queue = new PriorityQueue < > ((a, b) -> b - a);
        int time = 0;
        for (int[] c: courses) {
            if (time + c[0] <= c[1]) {
                queue.offer(c[0]);
                time += c[0];
            } else if (!queue.isEmpty() && queue.peek() > c[0]) {
                time += c[0] - queue.poll();
                queue.offer(c[0]);
            }
        }
        return queue.size();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.scheduleCourse(new int [][]{{100, 200},{200, 1300},{1000, 1250},{2000, 3200}}));
//        System.out.println(s.scheduleCourse(new int [][]{{100, 200},{200, 1300}}));
    }
}
