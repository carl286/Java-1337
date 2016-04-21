package com.topcoder.arena.srm203;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

class Node {
    String name;
    String answer;
    Node(String n, String a) {
        this.name = n;
        this.answer = a;
    }
}

public class MatchMaking
{
    private final char A = 'a';
    private final char B = 'b';

    int countShareString(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); ++i)
            if (s1.charAt(i) == s2.charAt(i))
                ++count;
        return count;
    }
    int compareString(String s1, String s2) {
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) < s2.charAt(i))
                return -1;
            else if (s1.charAt(i) > s2.charAt(i))
                return +1;
        }

        return 0;
    }
    public String makeMatch(String[] namesWomen, String[] answersWomen, String[] namesMen, String[] answersMen, String queryWoman)
    {
        Node [] nArray = new Node[namesWomen.length];
        for (int i = 0; i < namesWomen.length; ++i) {
            nArray[i] = new Node(namesWomen[i],answersWomen[i]);
        }

        Arrays.sort(nArray, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
//                int diff1 = compareString(o1.answer, o2.answer);
//                if (diff1 != 0)
//                    return diff1;
                return compareString(o1.name, o2.name);
            }
        });
        boolean [] isManSelected = new boolean[namesMen.length];

        for (int i = 0; i < nArray.length; ++i) {
            int diff = Integer.MIN_VALUE;
            int candidate = -1;

            for (int j = 0; j < namesMen.length; ++j) {
                if (!isManSelected[j]) {
                    //calculate
                    int localDiff = countShareString(nArray[i].answer, answersMen[j]);

                    if (localDiff > diff) {
                        diff = localDiff;
                        candidate = j;
                    } else if (localDiff == diff && compareString(namesMen[j], namesMen[candidate]) < 0) {
                        candidate = j;
                    }

                }
            }
            isManSelected[candidate] = true;
            if (nArray[i].name.equals(queryWoman)) {
                return namesMen[candidate];
            }
        }

        return null;
    }

    public static void main(String [] args) {
        MatchMaking m = new MatchMaking();
        String[] p0;
        String[] p1;
        String[] p2;
        String[] p3;
        String p4;
        String p5;

//         ----- test 0 -----
//        p0 = new String[]{"Constance","Bertha","Alice"};
//        p1 = new String[]{"aaba","baab","aaaa"};
//        p2 = new String[]{"Chip","Biff","Abe"};
//        p3 = new String[]{"bbaa","baaa","aaab"};
//        p4 = "Bertha";
//        p5 = "Biff";



        // test 1
        p0 = new String[]{"Constance","Bertha","Alice"};
        p1 = new String[]{"aaba","baab","aaaa"};
        p2 = new String[]{"Chip","Biff","Abe"};
        p3 = new String[]{"bbaa","baaa","aaab"};
        p4 = "Constance";
        p5 = "Chip";

        System.out.println(m.makeMatch(p0,p1,p2,p3,p4));
    }
}