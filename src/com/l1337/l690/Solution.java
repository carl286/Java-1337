package com.l1337.l690;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    };

    public int getImportance(List<Employee> employees, int id) {
        int ret = 0;

        Map<Integer, Employee> data = new HashMap<>();
        for (Employee e : employees) {
            data.put(e.id, e);
        }

        if (data.get(id) == null)
            return 0;

        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(id);
        visited.add(id);

        while (!queue.isEmpty()) {
            Employee cur = data.get(queue.removeFirst());
            ret += cur.importance;
            for (int i = 0; i < cur.subordinates.size(); ++i) {
                if (!visited.contains(cur.subordinates.get(i))) {
                    visited.add(cur.subordinates.get(i));
                    queue.add(cur.subordinates.get(i));
                }
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
