package com.l1337.l133;


import java.util.*;

public class Solution {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        Map<Node, Node> map = new HashMap<>();

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty())
        {
            Node current = queue.poll();
            if (map.get(current) == null) {
                map.put(current, new Node(current.val));
            }

            Node currentMappedNode = map.get(current);
            for (int i = 0; i < current.neighbors.size(); ++i)
            {
                if (map.get(current.neighbors.get(i)) == null)
                {
                    map.put(current.neighbors.get(i), new Node(current.neighbors.get(i).val));
                    queue.add(current.neighbors.get(i));
                }
                currentMappedNode.neighbors.add(map.get(current.neighbors.get(i)));
            }
        }

        return map.get(node);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
