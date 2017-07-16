package com.l1337.l207;


import javax.naming.ldap.HasControls;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> heads = new HashSet<>();
        for (int i = 0; i < numCourses; ++i)
            heads.add(i);
        for (int i = 0; i < prerequisites.length; ++i)
        {
            heads.remove(prerequisites[i][0]);
            Set<Integer> localSet = map.get(prerequisites[i][0]);
            if (localSet == null)
            {
                localSet = new HashSet<>();
                map.put(prerequisites[i][0], localSet);
            }
            localSet.add(prerequisites[i][1]);
        }

        while (heads.size() != numCourses)
        {
            Set<Integer> newHeads = new HashSet<>();
            for (Map.Entry<Integer, Set<Integer>> entry: map.entrySet()) {
                if (heads.containsAll(entry.getValue()))
                {
                    newHeads.add(entry.getKey());

                }
            };
            if (newHeads.size() == 0)
                return false;
            for (Integer k : newHeads)
                map.remove(k);

            heads.addAll(newHeads);
        }

        return heads.size() == numCourses;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
