package com.l1337.l641;


public class MyCircularDeque {
    private int start;
    private int size;
    private int [] data;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        data = new int [k];
        start = 0;
        size = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (!isFull()) {
            start = (data.length - 1 + start) % data.length;
            data[start] = value;
            size++;
            return true;
        } else {
            return false;
        }
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (!isFull()) {
            ++size;
            data[(start + size - 1) % data.length] = value;
            return true;
        } else {
            return false;
        }
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        } else {
            --size;
            start = (start + 1) % data.length;
            return true;
        }
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        } else {
            --size;
            return true;
        }
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty())
            return -1;
        else
            return data[start];

    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty())
            return -1;
        else
            return data[(start + size - 1) % data.length];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == data.length;
    }

    public static void main(String [] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3); // set the size to be 3
        System.out.println(circularDeque.insertLast(1));			// return true
        System.out.println(circularDeque.insertLast(2));			// return true
        System.out.println(circularDeque.insertFront(3));		// return true
        System.out.println(circularDeque.insertFront(4));			// return false, the queue is full
        System.out.println(circularDeque.getRear()); 			// return 2
        System.out.println(circularDeque.isFull());			// return true
        System.out.println(circularDeque.deleteLast());			// return true
        System.out.println(circularDeque.insertFront(4));			// return true
        System.out.println(circularDeque.getFront());			// return 4
    }
}
