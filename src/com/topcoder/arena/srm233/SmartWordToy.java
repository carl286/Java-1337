package com.topcoder.arena.srm233;

import java.util.HashSet;
import java.util.LinkedList;

//https://community.topcoder.com/stat?c=problem_statement&pm=3935&rd=6532
public class SmartWordToy {

    private final String pattern = "\\s+";
    private final int [] directions = {-1,+1};

    void extandSet(String [] tmp, int depth, char [] path, HashSet<String> forbids) {
        if (depth == tmp.length) {
            forbids.add(new String(path));
        } else {
            for (int i = 0; i < tmp[depth].length(); ++i) {
                path[depth] = tmp[depth].charAt(i);
                extandSet(tmp, depth+1, path, forbids);
            }
        }
    }

    int bfs(String start, String finish, HashSet<String> forbids) {

        LinkedList<String> q = new LinkedList<>();
        q.add(start);
        int level = 1;

        while (!q.isEmpty()) {
            LinkedList<String> next_q = new LinkedList<>();

            while (!q.isEmpty()) {
                String cur = q.poll();
                StringBuilder sb = new StringBuilder(cur);

                for (int i = 0; i < sb.length(); ++i) {
                    char c = sb.charAt(i);
                    for (int k = 0; k < directions.length; ++k) {
                        sb.setCharAt(i, (char)((((c - 'a') + 26 + directions[k]) % 26) + 'a'));
                        String sbs = sb.toString();
                        if (!forbids.contains(sbs)) {
                            if (sbs.equals(finish))
                                return level;
                            forbids.add(sbs);
                            next_q.add(sbs);
                        }
                    }
                    sb.setCharAt(i, c);
                }
            }

            q = next_q;
            ++level;
        }

        return -1;
    }

    public int minPresses(String start, String finish, String[] forbid) {
        if (start.equals(finish))
            return 0;

        HashSet<String> forbids = new HashSet<>();
        for (int i = 0; i < forbid.length; ++i) {
            String [] tmp = forbid[i].split(pattern);
            extandSet(tmp, 0, new char [tmp.length], forbids);
        }

        if (forbids.contains(finish))
            return -1;

        return bfs(start, finish, forbids);
    }

    public static void main(String [] args) {

        String start = "aaaa";
        String finish = "zzzz";
        String[] forbid = {"a a a z", "a a z a", "a z a a", "z a a a", "a z z z", "z a z z", "z z a z", "z z z a"};

//        String start = "aaaa";
//        String finish = "bbbb";
//        String[] forbid = {};

        SmartWordToy sm = new SmartWordToy();
        System.out.println(sm.minPresses(start, finish, forbid));
    }

}
