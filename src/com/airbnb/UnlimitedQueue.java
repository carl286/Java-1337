package com.airbnb;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class UnlimitedQueue {
    // Build a queue class with the enqueue and dequeue methods.
// The queue can store an *UNLIMITED* number of elements but you are limited to using arrays that can store up to 5 elements max.
        public static int MAX_ARRAY_SIZE = 5;
        public static class Node {
            int[] data;
            Node next;

            public Node()
            {
                data = new int [MAX_ARRAY_SIZE];
                next = null;
            }
        }

        public static class Queue
        {
            private int headIndex, tailIndex;
            private Node head, tail;

            public Queue()
            {
                headIndex = 0; //the first available index
                tailIndex = 0; //next available index
                head = null;
                tail = null;
            }

            public void enqueue(int element)
            {
                if (tail == null)
                {
                    tail = new Node();
                    head = tail;
                    headIndex = 0;
                    tail.data[tailIndex++] = element;
                }
                else
                {
                    if (tailIndex == MAX_ARRAY_SIZE)
                    {
                        Node next = new Node();
                        tail.next = next;
                        tail = next;

                        tailIndex = 0;
                        tail.data[tailIndex++] = element;
                    }
                    else
                    {
                        //TODO
                        tail.data[tailIndex++] = element;
                    }
                }
            }

            public int dequeue()
            {
                if (head == tail && headIndex >= tailIndex)
                {
                    //System.out.print("Indexes:\t" + headIndex + "\t" + tailIndex);
                    //throw new Exception("not exist");
                    throw new NoSuchElementException();
                }

                int ret = head.data[headIndex++];
                if (headIndex == MAX_ARRAY_SIZE)
                {
                    if (tail == head)
                    {
                        tail = tail.next;
                        tailIndex = 0;
                    }
                    head = head.next;
                    headIndex = 0;
                }

                return ret;
            }
        }


        public static void main(String[] args) {
            Queue queue = new Queue();
//            for (int i = 1; i <= 7; ++i)
//                queue.enqueue(i);
//
//            for (int i = 1; i <= 8; ++i)
//            {
//                System.out.println("value: \t" + queue.dequeue());
//            }

//             for (int i = 1; i <= 7; ++i)
//             {
//                 queue.enqueue(i);
//                 System.out.println(queue.dequeue());
//             }



            // Dequeue 1 time, will throw
            // System.out.println(queue.dequeue());

            // Enqueue 1 time, Dequeue 1 time
//             queue.enqueue(5);
//             System.out.println(queue.dequeue());

            // Enqueue 7 times, Dequeue 7 times

            // Enqueue 7 times, Dequeue 8 times, will throw

        }
}
