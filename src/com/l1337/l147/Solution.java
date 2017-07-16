package com.l1337.l147;


import com.l1337.common.ListNode;

public class Solution {

    public ListNode insertionSortList(ListNode head) {
        ListNode ret = null;
        while (head != null)
        {
            ListNode toBeInserted = head;
            head = head.next;

            ListNode pre = null, cur = ret;
            while (cur != null && toBeInserted.val > cur.val)
            {
                pre = cur;
                cur = cur.next;
            }

            if (pre == null)
            {
                //insert at the beginning
                toBeInserted.next = ret;
                ret = toBeInserted;
            }
            else
            {
                pre.next = toBeInserted;
                toBeInserted.next = cur;
            }
        }

        return ret;
    }

    //head and tail are both not null...
    private void sortListHelper(ListNode head, ListNode tail, ListNode ret [])
    {
        if (head == tail) {
            ret[0] = head;
            ret[1] = head;
            return;
        }

        if (head.next == tail)
        {
            //need sort it
            if (head.val > tail.val)
            {
                tail.next = head;
                head.next = null;

                ret[0] = tail;
                ret[1] = head;
            }
            else
            {
                ret[0] = head;
                ret[1] = tail;
            }
            return;
        }

        //at least three nodes
        ListNode slow = head, fast = head;
        while (fast != tail && (fast = fast.next) != tail && (fast = fast.next) != tail)
        {
            slow = slow.next;
        }

        ListNode secondHead = slow.next;
        slow.next = null;

        ListNode ret1 [] = new ListNode[2];
        ListNode ret2 [] = new ListNode[2];

        sortListHelper(head, slow, ret1);
        sortListHelper(secondHead, fast, ret2);

        //merge, we need merge two sorted list here...
        ListNode h1 = ret1[0];
        ListNode h2 = ret2[0];
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (h1 != null && h2 != null)
        {
            if (h1.val < h2.val)
            {
                cur.next = h1;
                cur = cur.next;
                h1 = h1.next;
            }
            else
            {
                cur.next = h2;
                cur = cur.next;
                h2 = h2.next;
            }
        }
        if (h1 != null)
        {
            cur.next = h1;
            ret[0] = dummyHead.next;
            ret[1] = ret1[1];
        }
        else
        {
            cur.next = h2;
            ret[0] = dummyHead.next;
            ret[1] = ret2[1];
        }

    }
    public ListNode sortList(ListNode head) {
        if (head == null)
            return null;
        ListNode tail = head;
        while (tail.next != null)
            tail = tail.next;

        ListNode ret1 [] = new ListNode[2];
        sortListHelper(head, tail, ret1);
        return ret1[0];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");

        ListNode h1 = new ListNode(4);
        ListNode h2 = new ListNode(2);
        ListNode h3 = new ListNode(1);
        ListNode h4 = new ListNode(3);

        h1.next = h2;
        h2.next = h3;
        h3.next = h4;

        ListNode ret = s.sortList(h1);
        while (ret != null)
        {
            System.out.print(ret.val + "\t");
            ret = ret.next;
        }
    }
}
