package com.leetcode;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//https://leetcode.com/problems/flatten-nested-list-iterator/
public class NestedIterator implements Iterator<Integer> {

    List<Integer> list;
    Iterator<Integer> iterator;
    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        NestedIteratorHelper(nestedList);
        iterator = list.iterator();
    }
    private void NestedIteratorHelper(List<NestedInteger> nestedList) {
        ListIterator<NestedInteger> it = nestedList.listIterator();
        while (it.hasNext()) {
            NestedIteratorHelper(it.next());
        }
    }
    private void NestedIteratorHelper(NestedInteger nestedInteger) {
        if (nestedInteger.isInteger()) {
            list.add(nestedInteger.getInteger());
        } else {
            NestedIteratorHelper(nestedInteger.getList());
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}
