package com.l1337.l352;

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class SummaryRanges {
    /*
    List<Interval> list;
    public SummaryRanges() {
        list = new ArrayList<>();
    }

    private boolean canMerge(Interval a, Interval b){
        return !(a.end < b.start-1 || b.end < a.start-1);
    }
    public void addNum(int val) {
        List<Interval> newList = new ArrayList<>();
        Interval added = new Interval(val,val);
        //linear add?
        int i = 0;
        while (i < list.size() && list.get(i).end < val-1) {
            newList.add(list.get(i));
            ++i;
        }
        //merge
        while (i < list.size() && canMerge(list.get(i), added)) {
            added.start = Math.min(added.start, list.get(i).start);
            added.end = Math.max(added.end, list.get(i).end);
            ++i;
        }
        newList.add(added);
        while (i < list.size()) {
            newList.add(list.get(i));
            ++i;
        }
        list = newList;
    }

    public List<Interval> getIntervals() {
        return list;
    }
    */
    private TreeSet<Interval> intervalSet;

    public SummaryRanges() {
        intervalSet = new TreeSet<Interval>(new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
    }

    public void addNum(int val) {
        Interval valInterval = new Interval(val, val);
        Interval floor = intervalSet.floor(valInterval);
        if (floor != null) {
            if (floor.end >= val) {
                return;
            } else if (floor.end + 1 == val) {
                valInterval.start = floor.start;
                intervalSet.remove(floor);
            }
        }
        Interval higher = intervalSet.higher(valInterval);
        if (higher != null && higher.start == val + 1) {
            valInterval.end = higher.end;
            intervalSet.remove(higher);
        }
        intervalSet.add(valInterval);
    }

    public List<Interval> getIntervals() {
        //will return a default one if empty...
        return Arrays.asList(intervalSet.toArray(new Interval[0]));
    }

    public static void main(String [] args) {
        SummaryRanges obj = new SummaryRanges();
        obj.addNum(1);
        obj.addNum(3);
        obj.addNum(2);
        for (Interval interval : obj.getIntervals()) {
            System.out.println(interval.start + "\t" + interval.end);
        }
    }
}
