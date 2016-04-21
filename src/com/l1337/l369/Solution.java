package com.l1337.l369;

/*
Given a non-negative number represented as a singly linked list of digits, plus one to the number.
The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4

Have to do it in O(n) time and O(1) space.
 */


import com.l1337.common.ListNode;

class ComplexNode {
    ListNode node;
    int carryOn;
    public ComplexNode(ListNode node, int carryOn) {
        this.node = node;
        this.carryOn = carryOn;
    }
}
public class Solution {

    private ComplexNode plusOneHelper(ListNode head) {
        if (head == null) {
            return new ComplexNode(head, 1);
        }
        ComplexNode next = plusOneHelper(head.next);
        int sum = head.val + next.carryOn;
        int carryOn;
        if (sum >= 10) {
            head.val = sum - 10;
            carryOn = 1;
        } else  {
            head.val = sum;
            carryOn = 0;
        }
        return new ComplexNode(head, carryOn);
    }
    public ListNode plusOne(ListNode head) {
        if (head == null)
            return new ListNode(1);

        //add the stupid dummy node by myself
        ComplexNode node = plusOneHelper(head);
        if (node.carryOn != 0) {
            ListNode newNode = new ListNode(1);
            newNode.next = node.node;
            return newNode;
        } else {
            return node.node;
        }
    }

    ListNode plusOneHelperII(ListNode head) {
        ListNode ret = new ListNode(head.val);
        int carryOn = 0;
        if (head.next == null) {
            carryOn = 1;
        } else {
            ListNode node = plusOneHelperII(head.next);
            if (node.val >= 10) {
                carryOn = 1;
                node.val -= 10;
            }
            ret.next = node;
        }

        ret.val += carryOn;
        return ret;
    }


//    https://all4win78.wordpress.com/2016/06/29/leetcode-369-plus-one-linked-list/
//    http://www.cnblogs.com/grandyang/p/5626389.html
    public ListNode plusOneII(ListNode head) {
        if (head == null) {
            return new ListNode(1);
        }

        //you can carry it by yourselves
        ListNode node = plusOneHelperII(head);
        if (node.val >= 0) {
            ListNode newNode = new ListNode(1);
            node.val -= 10;
            newNode.next = node;
            node = newNode;
        }
        return node;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");

        ListNode n1 = new ListNode(9);
        ListNode n2 = new ListNode(9);
        ListNode n3 = new ListNode(9);
        n1.next = n2;
        n2.next = n3;
        ListNode head = s.plusOneII(n1);
        while (head != null) {
            System.out.print(head.val + "\t");
            head = head.next;
        }
        System.out.println();

    }
}



//方法：两次反转链表。