package com.l1337.l582;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {

//    https://leetcode.com/articles/kill-process/

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        //from parent to child
        for (int i = 0; i < ppid.size(); ++i) {
            List<Integer> list = map.get(ppid.get(i));
            if (list != null) {
                list.add(pid.get(i));
            } else {
                list = new ArrayList<>();
                list.add(pid.get(i));
                map.put(ppid.get(i), list);
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(kill);
        List<Integer> ret = new ArrayList<>();

        while (!queue.isEmpty()) {
            Integer cur = queue.removeFirst();
            ret.add(cur);
            List<Integer> list = map.get(cur);
            if (list != null)
                queue.addAll(list);
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.killProcess(Arrays.asList(1, 3, 10, 5), Arrays.asList(3, 0, 5, 3), 5));
    }
}
