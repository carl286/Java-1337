package com.l1337.l284;


import java.util.Arrays;
import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {

    private Integer peek;
    private final Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.peek = null;
        this.iterator = iterator;
    }
    public Integer peek() {
        if (peek == null) {
            if (iterator.hasNext())
                peek = iterator.next();
        }
        return peek;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            if (peek != null) {
                Integer ret = peek;
                peek = null;
                return ret;
            } else {
                return iterator.next();
            }
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return peek != null || iterator.hasNext();
    }
    public static void main(String [] args) {
        Integer [] nums = {1, 2, 3};
        PeekingIterator iterator = new PeekingIterator(Arrays.asList(nums).iterator());
        while (iterator.hasNext()) {
            System.out.println(iterator.peek());
            System.out.println(iterator.next());
            System.out.println(iterator.peek());
        }
        System.out.println("Hello World");
    }
}
