package com.l1337.l731.l729;

import java.util.Comparator;
import java.util.TreeSet;

class Node {
    int start;
    int end;
    Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    boolean isOverlap(Node that) {
        return !(this.start >= that.end || this.end <= that.start);
    }
}

class NodeComparator implements Comparator<Node> {
    public int compare(Node e1, Node e2) {
        return e1.end - e2.end;
    }
}

public class MyCalendarTwo {

    private TreeSet<Node> data;

    public MyCalendarTwo() {
        data = new TreeSet<>(new NodeComparator());
    }

    public boolean book(int start, int end) {
        //try to search first
        Node newNode = new Node(start, end);

        Node lower = data.lower(newNode);
        if (lower != null && lower.isOverlap(newNode))
            return false;

        Node ceiling = data.ceiling(newNode);
        if (ceiling != null && ceiling.isOverlap(newNode))
            return false;


        return data.add(newNode);
    }

    public static void main(String [] args) {
        MyCalendarTwo myCalendar= new MyCalendarTwo();
        System.out.println(myCalendar.book(10, 20)); // returns true
        System.out.println(myCalendar.book(15, 25)); // returns false
        System.out.println(myCalendar.book(20, 30)); // returns true
    }
}
