package com.topcoder.arena.srm236;

//https://community.topcoder.com/stat?c=problem_statement&pm=1585&rd=6535
//https://en.wikipedia.org/wiki/Josephus_problem

public class BusinessTasks {
    public String getTask(String[] list, int n) {
        int x = 0;
        int length = list.length;
        int round = 1;
        while (length > round) {
            x = (x + n) % ++round;
            System.out.println(x);
//            ++round;
        }
        return list[x];
    }

    public static void main (String args[]) {
        String[] lsit = {"a","b","c","d", "e"};
        int n = 3;
        BusinessTasks b = new BusinessTasks();
        System.out.println(b.getTask(lsit,n));
    }

}
