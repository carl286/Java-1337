package com.l1337.l403;


import java.util.*;

public class Solution {
    private static final int [] directions = {-1, 0, 1};
    static class Node {
        int pos;
        int step;
        public Node(int pos, int step) {
            this.pos = pos;
            this.step = step;
        }

        public int hashCode() {
            return pos + step;
        }

        public boolean equals(Object obj) {
            Node that = (Node) obj;
            return this.pos == that.pos && this.step == that.step;
        }
    }

    public boolean canCross(int[] stones) {
        Set<Integer> set = new HashSet<>();
        for (int i : stones)
            set.add(i);

        if (!set.contains(1))
            return false;

        int last = stones[stones.length - 1];
        Set<Node> visited = new HashSet<>();
        Set<Node> unvisited = new HashSet<>();
        unvisited.add(new Node(1, 1));

        //order does not really matters here
        while (!unvisited.isEmpty()) {
            Set<Node> unvisited_2 = new HashSet<>();
            Iterator<Node> iterator = unvisited.iterator();
            while (iterator.hasNext()) {
                Node n = iterator.next();
                visited.add(n);

                int pos = n.pos;
                int step = n.step;

                for (int d : directions ) {
                    int next_step = step + d;
                    int next_pos = pos + next_step;

                    if (next_pos == last)
                        return true;
                    if (!set.contains(next_pos) || next_pos > last)
                        continue;
                    Node new_node = new Node(next_pos, next_step);

                    if (!visited.contains(new_node) && !unvisited.contains(new_node) && !unvisited_2.contains(new_node)) {
                        unvisited_2.add(new_node);
                    }
                }
            }
            unvisited = unvisited_2;
        }

        return false;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] stones = {0,1,2,3,4,8,9,11};
        System.out.println(s.canCross(stones));
    }
}
