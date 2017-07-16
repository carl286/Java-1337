package com.l1337.l688;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    static class Node {
        int r;
        int c;
        Node (int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            Node that = (Node) obj;
            return this.r == that.r && this.c == that.c;
        }

        @Override
        public int hashCode() {
            return this.r + this.c;
        }
    }

    private int [][] directions = new int [][]{{1,2},{1,-2},{-1,2},{-1,-2},{2,-1},{2,1},{-2,-1},{-2,1}};
    private List<Node> getNext(Node n, int N, Map<Node, List<Node>> map) {
        if (map.containsKey(n))
            return map.get(n);
        List<Node> ret = new ArrayList<>();
        for (int i = 0; i < directions.length; ++i) {
            int new_r = n.r + directions[i][0];
            int new_c = n.c + directions[i][1];
            if (new_c >= 0 && new_c < N && new_r >= 0 && new_r < N)
                ret.add(new Node(new_r, new_c));
        }
        map.put(n, ret);

        return ret;
    }

    //why below code is wrong.
    public double knightProbability(int N, int K, int r, int c) {
        if (K == 0)
            return 1.00;

        Map<Node, List<Node>> map = new HashMap<>();
        Map<Node, Long> cur = new HashMap<>();
        cur.put(new Node(r,c), 1L);
        int loop = K;

        while (!cur.isEmpty() && K-- > 0) {
            Map<Node, Long> next = new HashMap<>();

            for (Map.Entry<Node, Long> entry : cur.entrySet()) {
                List<Node> newNodes = getNext(entry.getKey(), N, map);
                for (int i = 0; i < newNodes.size(); ++i) {
                    next.put(newNodes.get(i), entry.getValue() + next.getOrDefault(newNodes.get(i), 0L));
                }
            }

            cur = next;
        }

        if (K > 0)
            return 0.0;

        Double sum = 0.0;
        for (Map.Entry<Node, Long> entry : cur.entrySet()) {
            sum += entry.getValue();
        }

        return sum / Math.pow(8, loop);
    }

//    https://leetcode.com/problems/knight-probability-in-chessboard/discuss/108181/My-accepted-DP-solution
//    https://leetcode.com/problems/knight-probability-in-chessboard/discuss/108187/C++Java-DP-concise-solution
    //you can make it easy...
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.knightProbability(8,30,6,4));
    }
}
