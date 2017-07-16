package com.l1337.l1776;


import java.util.*;

public class Solution {

    public double[] getCollisionTimes(int[][] cars) {
        /*
        int n = cars.length;
        double [] ret = new double[n];
        Stack<Integer> dq = new Stack<>();
        for(int i = 0; i < n; ++i)
        {
            if (dq.isEmpty() || cars[dq.peek()][1] > cars[i][1])
            {
                dq.push(i);
            }
            else
            {
                //clear the queue
                ret[dq.peek()] = -1;
                while (dq.size() >= 2)
                {
                    int slow = dq.pop();
                    int fast = dq.peek();
                    ret[fast] = (cars[fast][0] - cars[slow][0]) / (double)(cars[slow][1] - cars[fast][1]);
//                    cars[fast][0] = cars[fast][0] + (int) (ret[fast] * cars[fast][1]);
//                    cars[fast][1] = cars[slow][1];
                }
                if (!dq.isEmpty())
                {
                    dq.pop();
                }

                dq.push(i);
            }
        }

        ret[dq.peek()] = -1;
        while (dq.size() >= 2)
        {
            int slow = dq.pop();
            int fast = dq.peek();
            ret[fast] = (cars[fast][0] - cars[slow][0]) / (double)(cars[slow][1] - cars[fast][1]);
            cars[fast][0] = cars[fast][0] + (int) (ret[fast] * cars[fast][1]);
            cars[fast][1] = cars[slow][1];
        }
        if (!dq.isEmpty())
        {
            dq.pop();
        }
*/
//        Stack<Integer> dq = new Stack<>();
//        int effectiveSpeed = Integer.MAX_VALUE;
//        List<Integer> groups = new ArrayList<>();
//
//        for (int i = n - 1; i >= 0; --i)
//        {
//            if (cars[i][1] <= effectiveSpeed)
//            {
//                //never able to catch up, clean up the group
//                groups.clear();
//                groups.add(i);
//                effectiveSpeed = cars[i][1];
//            }
//            else
//            {
//                groups.add(i);
//            }
//        }


        int n = cars.length;
        Deque<Integer> stack = new LinkedList<>();
        double[] res = new double[n];
        for (int i = n - 1; i >= 0; --i) {
            res[i] = -1.0;
            int p = cars[i][0], s = cars[i][1];
            while (stack.size() > 0) {
                int j = stack.peekLast(), p2 = cars[j][0], s2 = cars[j][1];
                if (s <= s2 || 1.0 * (p2 - p) / (s - s2) >= res[j] && res[j] > 0)
                    stack.pollLast();
                else
                    break;
            }
            if (stack.size() > 0) {
                int j = stack.peekLast(), p2 = cars[j][0], s2 = cars[j][1];
                res[i] = 1.0 * (p2 - p) / (s - s2);
            }
            stack.add(i);
        }
        /*
        Process cars right-to-left, and maintain a stack s of cars with monotonically increasing speeds:

If the current car is slower than the slowest car up ahead - it won't collide, and the stack becomes empty.
If there are cars in the stack, the current car sure would collide. Also, all cars in the stack - except the first one - have collided at some point res[i].

So, we go from the top of the stack (fastest car) and check if we collide with it before it collides with cars up ahead. If we collide with that car after it collided, we can ignore that car and also remote it from the stack.
         */
        return res;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        // double ret [] = s.getCollisionTimes(new int [][] {{1,2}, {2,1}, {4,3}, {7,2}});
        double ret [] = s.getCollisionTimes(new int [][] {{3,4}, {5,4}, {6,3}, {9,1}});
        for (double v : ret)
        System.out.println(v);
    }
}
