package com.l1337.l692;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

    class Node {
        String val;
        int count;

        Node(String v, int c) {
            this.val = v;
            this.count = c;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> ret = new ArrayList<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.count != o2.count)
                    return Integer.compare(o2.count, o1.count);
                else
                    return o1.val.compareTo(o2.val);
            }
        });

        Map<String, Integer> map = new HashMap<>();
        for(String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (k-- > 0) {
            ret.add(pq.poll().val);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
