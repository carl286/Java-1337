package com.l1337.l502;


import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public static class Node {
        int profit;
        int capital;

        public Node(int p, int c) {
            profit = p;
            capital = c;
        }

    };

//    https://leetcode.com/problems/ipo/discuss/98210/Very-Simple-(Greedy)-Java-Excel-using-two-PriorityQueues
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        if (k <= 0)
            return 0;

        //small node on top
        PriorityQueue<Node> capitalQueue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.capital - o2.capital;
            }
        });

        //big node on top
        PriorityQueue<Node> profitQueue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.profit - o1.profit;
            }
        });

        for (int i = 0; i < Profits.length; ++i) {
            Node node = new Node(Profits[i], Capital[i]);
            if (node.capital <= W) {
                profitQueue.add(node);
            } else {
                capitalQueue.add(node);
            }
        }

        while (k-- > 0 && !profitQueue.isEmpty()) {
            Node n = profitQueue.poll();
            W += n.profit;
            while (!capitalQueue.isEmpty() && W >= capitalQueue.peek().capital) {
                profitQueue.add(capitalQueue.poll());
            }
        }

        return W;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int k=2, W=0;
        int [] Profits= new int [] {1,2,3};
        int [] Capital= new int [] {0,1,1};
        System.out.println(s.findMaximizedCapital(k,W,Profits,Capital));
    }
}
