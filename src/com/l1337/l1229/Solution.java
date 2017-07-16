package com.l1337.l1229;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {

    /*
    https://www.acwing.com/solution/LeetCode/content/5442/
你是一名行政助理，手里有两位客户的空闲时间表：slots1 和 slots2，以及会议的预计持续时间 duration，请你为他们安排合适的会议时间。
「会议时间」是两位客户都有空参加，并且持续时间能够满足预计时间 duration 的 最早的时间间隔。
如果没有满足要求的会议时间，就请返回一个 空数组。
「空闲时间」的格式是 [start, end]，由开始时间 start 和结束时间 end 组成，表示从 start 开始，到 end 结束。
题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，对于同一个人的两个空闲时间 [start1, end1] 和 [start2, end2]，要么 start1 > end2，要么 start2 > end1。
样例
输入：
slots1 = [[10,50],[60,120],[140,210]],
slots2 = [[0,15],[60,70]],
duration = 8

输出：[60,68]
输入：
slots1 = [[10,50],[60,120],[140,210]],
slots2 = [[0,15],[60,70]],
duration = 12

输出：[]
限制
1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6
     */
    /*
    Schedule a meeting. Meaning of the questions is to schedule two people and a duration long, the schedule of interval representation, please return two people may have the opportunity to length of time a duration of what the conference interval Yes. example,

Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
Analogy, such as the first example, they want to open a long 8-minute meeting, the two following schedule, the result returned is 60 - 68 minutes time the two have met.
     */

    /*
    Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
Constraints:

1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6
     */
    // is slot1 or slot2 sorted by started time? Assuming it's sorted here....
    //首先对slots1和slots2分别按start排好序。接下来分别从slots1和slots2中取第一个元素，判断两个元素是否满足会议，如果不满足，则从end较小的元素对应的slots中取后一个元素。以此类推，直到找出符合条件的slot为止。
    // https://www.codetd.com/en/article/10490715
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> ret = new ArrayList<>();
        Arrays.sort(slots1, new Comparator<int[]>() {
                    @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                else
                    return Integer.compare(a[1], b[1]);
            }
        });
        Arrays.sort(slots2, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                else
                    return Integer.compare(a[1], b[1]);
            }
        });
        for (int i = 0, j = 0; i < slots1.length && j < slots2.length;)
        {
            //non-overlapping
            if (slots1[i][1] < slots2[j][0])
                ++i;
            else if (slots2[j][1] < slots1[i][0])
                ++j;
            else
            {
                //overlap
                int left = Math.max(slots1[i][0], slots2[j][0]);
                int right = Math.min(slots1[i][1], slots2[j][1]);
                if (right - left >= duration)
                {
                    ret.add(left);
                    // ret.add(right);
                    ret.add(left + duration);
                    break;
                }
                //seems whom to move is very tricky here....
                //if start is the same, move the one with earlier ending
                if (slots1[i][0] == slots2[j][0])
                {
                    if (slots1[i][1] < slots2[j][1])
                        ++i;
                    else
                        ++j;
                }
                else if (slots1[i][1] < slots2[j][1])
                {
                    ++i;
                }
                else if (slots1[i][1] > slots2[j][1])
                {
                    ++j;
                }
                else {
                    //same ending, different start
                    if (slots1[i][0] < slots2[j][0])
                        ++i;
                    else
                        ++j;
                }

                //after list all cases, you can optimize code above....
            }
        }


        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
