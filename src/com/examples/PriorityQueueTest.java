package com.examples;

import java.util.Comparator;
import java.util.PriorityQueue;

//https://examples.javacodegeeks.com/core-java/util/priorityqueue/java-util-priorityqueue-example/
//Before offering elements to a sorted collection like PriorityQueue, confirm if the element is not null. Null elements cannot be sorted and hence, an ugly NullPointerException is thrown.
public class PriorityQueueTest {
    static class PQsort implements Comparator<Integer> {

        //Who is positive, who will go out first...
        public int compare(Integer one, Integer two) {
//            return two - one;
//            return Integer.compare(one, two);
            return Integer.compare(two, one);
        }
    }


    public static void main(String[] args) {
       int[] ia = { 1, 10, 5, 3, 4, 7, 6, 9, 8 };
//        int[] ia = { 1, 1, 1, 1 };
        PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();

        // use offer() method to add elements to the PriorityQueue pq1
        for (int x : ia) {
            pq1.offer(x);
        }

        System.out.println("pq1: " + pq1);
        while (!pq1.isEmpty()) {
            System.out.print(pq1.poll() + "\t");
        }
        System.out.println();


        PQsort pqs = new PQsort();
        PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(10, pqs);
        // In this particular case, we can simply use Collections.reverseOrder()
        // instead of self-defined comparator
        for (int x : ia) {
            pq2.offer(x);
        }

        System.out.println("pq2: " + pq2);
        while (!pq2.isEmpty()) {
            System.out.print(pq2.poll() + "\t");
        }
        System.out.println();

        PriorityQueue<Integer> pq3 = new PriorityQueue<Integer>();
        for (int x : ia) {
            pq3.offer(x);
        }
        pq3.remove(1);
        System.out.println(pq3.size());

//        https://dzone.com/articles/using-lambda-expression-sort
        //how to use sort..
    }
}
