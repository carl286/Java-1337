package com.l1337.l341;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Ke.Liu on 6/12/16.
 */
//compare with the other submission, https://leetcode.com/submissions/detail/64125822/
public class NestedIterator implements Iterator<Integer> {

    /*
    private List<NestedInteger> list;
    private int index;

    public NestedIterator(List<NestedInteger> nestedList) {
        index = 0;
        list = nestedList;
    }

    public boolean hasNext() {
        if (index >= list.size())
            return false;
        if (list.get(index).isInteger())
            return true;
        else
            return expandNextInteger();
    }

    private boolean  expandNextInteger() {
        if (index >= list.size())
            return false;

        if (list.get(index).isInteger()) {
            return true;
        } else {
            List<NestedInteger> nestedList = list.get(index).getList();
            list.remove(index);
            if (nestedList.size() > 0)
                list.addAll(index, nestedList);
            return expandNextInteger();
        }
    }

    public Integer next() {
        if (hasNext()) {
            Integer ret = list.get(index).getInteger();
            list.remove(index);
            return ret;
        } else {
            return null;
        }
    }
    */


    //Use stack...
    private Stack<NestedInteger> st;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; --i)
            st.push(nestedList.get(i));
    }

    //assume cur is null when come to here.
    private void expand() {
        while (!st.isEmpty() && !st.peek().isInteger()) {
            NestedInteger ni = st.pop();
            List<NestedInteger> local = ni.getList();
            for (int i = local.size() - 1; i >= 0; --i)
                st.push(local.get(i));
        }
    }
    public boolean hasNext() {
        if (!st.isEmpty() && st.peek().isInteger())
            return true;
        expand();
        return !st.isEmpty();
    }

    public Integer next() {
        if (hasNext()) {
            return st.pop().getInteger();
        } else {
            return null;
        }
    }
}
