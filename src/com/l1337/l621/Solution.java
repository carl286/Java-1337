package com.l1337.l621;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    static class Node {
        char c;
        int count;

        Node(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

//    https://leetcode.com/problems/task-scheduler/discuss/104495/Java-O(n)-solution-beats-99.76-use-only-array-easy-understanding
//    https://leetcode.com/problems/task-scheduler/discuss/104496/concise-Java-Solution-O(N)-time-O(26)-space
//    https://leetcode.com/problems/task-scheduler/discuss/104501/Java-PriorityQueue-solution-Similar-problem-Rearrange-string-K-distance-apart
//    https://leetcode.com/problems/task-scheduler/discuss/104507/Python-Straightforward-with-Explanation

    //analysis in below article is great....
//    https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
    public int leastInterval(char[] tasks, int n) {
        if (n <= 0 || tasks.length <= 1)
            return tasks.length;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; ++i) {
            int count = map.getOrDefault(tasks[i], 0) + 1;
            map.put(tasks[i], count);
        }

        //quick hack
        if (map.size() == 1)
            return (map.get(tasks[0]) - 1) * (n + 1) + 1;

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.count - o1.count;
            }
        });

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        int ret = 0;
        while (!pq.isEmpty()) {
            List<Node> list = new ArrayList<>();
            while (list.size() < n + 1 && !pq.isEmpty()) {
                list.add(pq.poll());
            }

            for (int i = 0; i < list.size(); ++i) {
                --list.get(i).count;
                if (list.get(i).count > 0)
                    pq.add(list.get(i));
            }

            if (pq.isEmpty()) {
                ret += list.size();
            } else {
                ret += (n + 1);
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.leastInterval(new char[]{'A','A','A','C'}, 2));
    }
}
