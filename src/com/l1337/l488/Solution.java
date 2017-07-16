package com.l1337.l488;


import java.util.HashMap;
import java.util.Map;

public class Solution {
    static class Node {
       char c;
       int count;
       Node pre;
       Node next;
       Node(char c, int count) {
           this.c = c;
           this.count = count;
           pre = null;
           next = null;
       }
    };

    private Node copyList(Node header) {
        Node ret = new Node('X',0);
        Node pre = ret;
        header = header.next;
        while (header != null) {
            Node newNode = new Node(header.c, header.count);
            pre.next = newNode;
            newNode.pre = pre;
            pre = newNode;
            header = header.next;
        }
        return ret;
    }

    int checkLeftOver(Map<Character, Integer> map) {
        int ret = 0;
        for (int i : map.values())
            ret += i;
        return ret;
    }

    int dfs(Node head, Map<Character, Integer> map, int org) {

        //merge nodes
        while (true) {
            Node pre = head;
            Node cur = pre.next;
            boolean hasChanged = false;

            while (cur != null) {
                int count = 0;
                Node runner = cur;
                pre = cur.pre;
                while (runner != null && runner.c == cur.c) {
                    count += runner.count;
                    runner = runner.next;
                }

                if (count >= 3) {
                    hasChanged = true;
                    Node tmp = cur;
                    while (tmp != runner) {
                        Node next = tmp.next;
                        tmp.next = null;
                        tmp.pre = null;
                        tmp = next;
                    }
                    pre.next = runner;
                    if (runner != null)
                        runner.pre = pre;

                    while (runner != null && pre != head && pre.c == runner.c) {
                        pre = pre.pre;
                    }
                    cur = pre.next;
                } else {
                    cur = runner;
                }
            }

            if (!hasChanged)
                break;
        }

        if (head.next == null)
            return org - checkLeftOver(map);

        int ret = -1;
        Node cur = head.next;
        while (cur != null) {
            int cache = map.getOrDefault(cur.c, 0);
            if ( cache >  0) {
                cur.count++;
                Node newHeader = copyList(head);
                map.put(cur.c, cache - 1);
                int local = dfs(newHeader, map, org);
                if (ret < 0 || local >= 0 && local < ret) {
                    ret = local;
                }

                map.put(cur.c, cache);
                cur.count--;
            }
            cur = cur.next;
        }

        return ret;
    }

    public int findMinStep(String board, String hand) {
        //construct the map
        int i = 0;
        Node header = new Node('X',0);
        Node pre = header;

        while (i < board.length()) {
            int j = i;
            while (j < board.length() && board.charAt(j) == board.charAt(i)) {
                ++j;
            }
            Node newNode = new Node(board.charAt(i), j - i);
            pre.next = newNode;
            newNode.pre = pre;
            pre = newNode;
            i = j;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (i = 0; i < hand.length(); ++i) {
            map.put(hand.charAt(i), map.getOrDefault(hand.charAt(i), 0) + 1);
        }


        return dfs(header, map, hand.length());
    }

//    https://leetcode.com/problems/zuma-game/discuss/97007/Standard-test-program-is-wrong
//    https://leetcode.com/problems/zuma-game/discuss/142221/DFS-with-memoization
//    https://leetcode.com/problems/zuma-game/discuss/97010/%22short%22-java-solution-beats-98

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.findMinStep("G", "GG"));
//        System.out.println(s.findMinStep("WRRBBW", "RB"));
//        System.out.println(s.findMinStep("WWRRBBWW", "WRBRW"));
        System.out.println(s.findMinStep("RBYYBBRRB", "YRBGB"));
    }
}
