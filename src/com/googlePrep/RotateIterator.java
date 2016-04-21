package com.googlePrep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ke.Liu on 6/10/16.
 */
public class RotateIterator<E> implements Iterator<E> {
    private final List<Iterator<E>> l;
    private int index;
    RotateIterator(List<Iterator<E>> l) {
        this.l = new ArrayList<>();
        for (int i = 0; i < l.size(); ++i)
            if (l.get(i).hasNext())
                this.l.add(l.get(i));
        index = 0;
    }
    @Override
    public boolean hasNext() {
        //let this sole api to update index
        return (l.size() > 0);
    }

    @Override
    public E next() {
        if (hasNext()) {
            E ret = l.get(index).next();
            if (l.get(index).hasNext()) {
                ++index;
            } else {
                l.remove(index);
            }
            if (l.size() > 0)
                index %= l.size();
            return ret;
        } else {
            return null;
        }
    }
    public static void main(String [] args) {
        Integer [] a1 = {1,2,3};
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);
        List<Integer> list3 = Arrays.asList(7,8);
        List<Iterator<Integer>> l = Arrays.asList(list1.iterator(), list2.iterator(), list3.iterator());
        RotateIterator<Integer> rotateIterator = new RotateIterator(l);
        while (rotateIterator.hasNext()) {
            System.out.print(rotateIterator.next() + "\t");
        }
    }
}
