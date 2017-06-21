package com.topcoder.arena.srm207;


import java.util.HashMap;
import java.util.LinkedList;

//https://community.topcoder.com/stat?c=problem_statement&pm=2915&rd=5853

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int hashCode() {
        return x + y;
    }

    public boolean equals(Object c) {
        Node n = (Node) c;
        return this.x == n.x && this.y == n.y;
    }
}

public class CaptureThemAll {
    private final int BOARD_SIZE = 8;
    private int [][] directions = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}, {-1, 2}, {1, 2}};

    //x >= 0, x < BOARD_SIZE; y >= 0, y < BOARD_SIZE
    private String getCode(int x, int y) {
        return ((char)(x + 'a')) + Integer.toString(y + 1);
    }

    private int getX(String code) {
        return code.charAt(0) - 'a';
    }

    private int getY(String code) {
        return code.charAt(1) - '1';
    }

    public int fastKnight(String knight, String rook, String queen) {

        Node nKnight = new Node(getX(knight), getY(knight));
        Node nRook = new Node(getX(rook), getY(rook));
        Node nQueen = new Node(getX(queen), getY(queen));

        LinkedList<Node> q = new LinkedList<>();
        HashMap<Node, Node> map = new HashMap<>();

        q.add(nKnight);
        map.put(nKnight, null);

        int find = 0;
        boolean findR = false;
        int levelR = 1;
        int levelQ = 1;
        boolean findQ = false;

        while (find != 2 && !q.isEmpty()) {

            LinkedList<Node> next_q = new LinkedList<>();

            while (find != 2 && !q.isEmpty()) {
                Node cur = q.poll();
                int x = cur.x;
                int y = cur.y;

                for (int i = 0; i < directions.length && find != 2; ++i) {
                    int newX = x + directions[i][0];
                    int newY = y + directions[i][1];

                    if (newX >= 0 && newY >= 0 && newX < BOARD_SIZE && newY < BOARD_SIZE) {
                        Node n = new Node(newX, newY);
                        if (!map.containsKey(n)) {
                            if (!findQ) {
                                if (n.equals(nQueen)) {
                                    ++find;
                                    findQ = true;
                                }
                            }
                            if (!findR) {
                                if (n.equals(nRook)) {
                                    ++find;
                                    findR = true;
                                }
                            }
                            map.put(n, cur);
                            next_q.add(n);
                        }
                    }
                }
            }

            q = next_q;
            if (!findQ)
                ++levelQ;
            if (!findR)
                ++levelR;
        }


        int ret = Integer.MAX_VALUE;
        if (find == 2) {
            ret = Math.abs(levelQ - levelR);
            if (levelR < levelQ) {
                while (levelQ > levelR) {
                    --levelQ;
                    nQueen = map.get(nQueen);
                }
            } else {
                while (levelR > levelQ) {
                    --levelR;
                    nRook = map.get(nRook);
                }
            }

            while (!nQueen.equals(nRook)) {
                ret += 3;
                nQueen = map.get(nQueen);
                nRook = map.get(nRook);
            }

            while (!nQueen.equals(nKnight)) {
                ret += 1;
                nQueen = map.get(nQueen);
            }
        }
        return ret;
    }

    public static void main(String [] args) {
        CaptureThemAll ct = new CaptureThemAll();
//        System.out.println(ct.fastKnight("a1", "b3", "c5"));
//        System.out.println(ct.fastKnight("b1", "c3", "a3"));
        System.out.println(ct.fastKnight("a1", "a2", "b2"));
    }
}
