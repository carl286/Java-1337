package com.topcoder.arena.srm208;

import java.util.Comparator;
import java.util.PriorityQueue;

//https://community.topcoder.com/stat?c=problem_statement&pm=2923&rd=5854
public class TallPeople {
    public int[] getPeople(String[] people) {
        int [][] data = new int [people.length][];
        for (int i = 0; i < people.length; ++i) {
            String[] splits = people[i].split("\\s+");
            data[i] = new int [splits.length];
            for (int j = 0; j < data[i].length; ++j)
                data[i][j] = Integer.parseInt(splits[j]);
        }

        PriorityQueue<Integer> small = new PriorityQueue<>(new Comparator<Integer> (){
            public int compare(Integer a, Integer b) {
                return Integer.compare(b, a);
            }
        });//big at top

        PriorityQueue<Integer> big = new PriorityQueue<>();//small at top

        for (int i = 0; i < data.length; ++i) {
            int smallest = Integer.MAX_VALUE;
            for (int j = 0; j < data[i].length; ++j) {
                if (data[i][j] < smallest) {
                    smallest = data[i][j];
                }
            }
            small.add(smallest);
        }
        for (int j = 0; j < data[0].length; ++j) {
            int biggest = Integer.MIN_VALUE;
            for (int i = 0; i < data.length; ++i) {
                if (biggest < data[i][j])
                    biggest = data[i][j];
            }
            big.add(biggest);
        }
        int [] ret = new int [] {small.peek(), big.peek()};

        return ret;
    }

    public static void main(String [] args) {
        TallPeople t = new TallPeople();
        String [] people = {"9 2 3", "4 8 7"};
        for (int i : t.getPeople(people))
            System.out.println(i);

    }
}
