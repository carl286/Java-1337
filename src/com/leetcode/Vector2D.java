package com.leetcode;

//	Flatten 2D Vector, 251

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Implement an iterator to flatten a 2d vector.
//    For example,
//    Given 2d vector =
//    [
//    [1,2],
//    [3],
//    [4,5,6]
//    ]
//    By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
//
//    Hint:
//
//    How many variables do you need to keep track?
//    Two variables is all you need. Try with x and y.
//    Beware of empty rows. It could be the first few rows.
//    To write correct code, think about the invariant to maintain. What is it?
//    The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
//    Not sure? Think about how you would implement hasNext(). Which is more complex?
//    Common logic in two different places should be refactored into a common method.

//You don't have to read by yourself...
//https://segmentfault.com/a/1190000003791233
//http://buttercola.blogspot.com/2015/08/leetcode-flatten-2d-vector.html
public class Vector2D {

    Iterator<Integer> iterator;
    public Vector2D(List<List<Integer>> vec2d) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < vec2d.size(); i++) {
            //This is a edge case... Don't forget it...
//            If the 2d vec contains empty vec, e.g. [ ], [ ] , [1, 2], [ ], [ ], [3], we need to handle it in method hasNext();
            if (vec2d.get(i).size() > 0)
                list.addAll(vec2d.get(i));
        }
        iterator = list.iterator();
    }

    public int next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
}
