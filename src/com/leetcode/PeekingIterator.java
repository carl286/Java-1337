package com.leetcode;

import java.util.Iterator;

//https://leetcode.com/problems/peeking-iterator/
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private Integer peek;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        peek = null;
        this.iterator=iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (peek == null) {
            if (iterator.hasNext())
                peek = iterator.next();
        }
        return peek;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (peek != null) {
            Integer ret = peek;
            peek = null;
            return ret;
        } else {
            return iterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        return (peek != null) || (iterator.hasNext());
    }
}
