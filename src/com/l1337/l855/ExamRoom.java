package com.l1337.l855;


import java.util.Iterator;
import java.util.TreeSet;

public class ExamRoom {

    private TreeSet<Integer> treeSet;
    private int leftEnd;
    private int rightEnd;
    public ExamRoom(int N) {
        this.treeSet = new TreeSet<>();
        rightEnd = N - 1;
        leftEnd = 0;
    }

    public int seat() {
        switch (treeSet.size())
        {
            case 0:
                treeSet.add(0);
                return 0;
            case 1:
                int leftDistance = treeSet.first() - leftEnd;
                int rightDistance = rightEnd - treeSet.last();
                int diff = leftDistance - rightDistance;
                if (diff >= 0)
                {
                    treeSet.add(0);
                    return 0;
                } else
                {
                    treeSet.add(rightEnd);
                    return rightEnd;
                }
            default:
                //find a position
                int preSeat = -1;
                int ret = -1;
                int minDistance = 0;
                //remember to check left and right
                Iterator<Integer> iterator = treeSet.iterator();
                while (iterator.hasNext())
                {
                    int curSeat = iterator.next();
                    if (preSeat >= 0)
                    {
                        if (curSeat != 1 + preSeat)
                        {
                            int mid = ((curSeat - preSeat) >> 1) + preSeat;
                            int localDistance = mid - preSeat;
                            if (localDistance > minDistance)
                            {
                                minDistance = localDistance;
                                ret = mid;
                            }
                        }
                    }

                    preSeat = curSeat;
                }

                //check both end
                if (!treeSet.contains(leftEnd))
                {
                    leftDistance = treeSet.first();
                    if (leftDistance >= minDistance)
                    {
                        ret = leftEnd;
                        minDistance = leftDistance;
                    }
                }

                if (!treeSet.contains(rightEnd))
                {
                    rightDistance = rightEnd - treeSet.last();
                    if (rightDistance > minDistance)
                    {
                        ret = rightEnd;
                        minDistance = rightDistance;
                    }
                }

                treeSet.add(ret);
                return ret;
        }
    }

    public void leave(int p) {
        treeSet.remove(p);
    }

//    https://leetcode.com/problems/exam-room/discuss/139862/C%2B%2BJavaPython-Straight-Forward
    public static void main(String [] args) {
        ExamRoom s = new ExamRoom(10);
        System.out.println(s.seat());
        System.out.println(s.seat());
        System.out.println(s.seat());
        System.out.println(s.seat());
        s.leave(4);
        System.out.println(s.seat());
        System.out.println("Hello World");
    }
}
