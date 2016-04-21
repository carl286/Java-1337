package com.l1337.common;

public class Interval {
    public int start;
    public int end;
    public Interval() { start = 0; end = 0; }
    public Interval(int s, int e) { start = s; end = e; }

    public boolean equals(Object obj) {
        Interval that = (Interval) obj;
        return this.start == that.start && this.end == that.end;
    }
}
