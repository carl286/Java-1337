package com.l1337.l332;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//https://leetcode.com/submissions/detail/57355660/
public class Solution {
    boolean findItinerary(List<String> ret, String start, HashMap<String, List<String>> map, int total) {
        ret.add(start);
        if (ret.size() == total)
            return true;

        //expand from start
        if (map.containsKey(start)) {
            for (int i = 0; i < map.get(start).size(); ++i) {
                String dst = map.get(start).get(i);
                map.get(start).remove(i);
                if (findItinerary(ret, dst, map, total))
                    return true;
                map.get(start).add(i, dst);
            }
        }

        ret.remove(ret.size()-1);
        return false;
    }

    public List<String> findItinerary(String[][] tickets) {
        //assume tickets are not empty...
        List<String> ret = new ArrayList<>();
        String start = new String("JFK");
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < tickets.length; ++i) {
            if (!map.containsKey(tickets[i][0]))
                map.put(tickets[i][0], new ArrayList<>());
            map.get(tickets[i][0]).add(tickets[i][1]);
        }
        for (List<String> l : map.values())
            Collections.sort(l);
        int total = tickets.length + 1;
        findItinerary(ret, start, map, total);
        return ret;
    }

    public static void main(String [] args) {
//        String [][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
        String [][] tickets = {{"JFK","SFO"}, {"JFK","ATL"}, {"SFO","ATL"}, {"ATL","JFK"}, {"ATL","SFO"}};
//        String [][] tickets = {{"JFK", "MUC"}, {"MUC", "SJC"}};
//        String [][] tickets = {{"JFK", "MUC"}};
        Solution s = new Solution();
        for (String tt : s.findItinerary(tickets))
            System.out.print(tt + "\t");
        System.out.println("");
    }
}
