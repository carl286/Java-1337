package com.l1337.l379;

//Design a Phone Directory which supports the following operations:
//
//        get: Provide a number which is not assigned to anyone.
//        check: Check if a number is available or not.
//        release: Recycle or release a number.
//        Example:
//
//// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
//        PhoneDirectory directory = new PhoneDirectory(3);
//
//// It can return any available phone number. Here we assume it returns 0.
//        directory.get();
//
//// Assume it returns 1.
//        directory.get();
//
//// The number 2 is available, so return true.
//        directory.check(2);
//
//// It returns 2, the only number that is left.
//        directory.get();
//
//// The number 2 is no longer available, so return false.
//        directory.check(2);
//
//// Release number 2 back to the pool.
//        directory.release(2);
//
//// Number 2 is available again, return true.
//        directory.check(2);

//All operations are O(1) expected
//http://www.cnblogs.com/grandyang/p/5735205.html

import java.util.HashSet;
import java.util.Stack;

public class PhoneDirectory {

    private final Stack<Integer> st;
    private final HashSet<Integer> set;
    private int top;
    private final int MAX;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        top = 0;
        st = new Stack<>();
        set = new HashSet<>();
        MAX = maxNumbers;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int ret;
        if (st.isEmpty()) {
            if (top >= MAX)
                return -1;
            else
                ret = top++;
        } else {
            ret = st.pop();
        }
        set.add(ret);
        return ret;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !set.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (set.contains(number)) {
            set.remove(number);
            st.push(number);
        }
    }


    public static void main(String [] args) {
        PhoneDirectory directory = new PhoneDirectory(3);
        System.out.println(directory.get());
        System.out.println(directory.check(1));
        System.out.println(directory.get());
        System.out.println(directory.check(1));
        directory.release(1);
        System.out.println(directory.check(1));
    }
}
