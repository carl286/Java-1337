package com.l1337.l1453;


public class Solution {


    //Good learning, no body says the center needs to be of coordinate of integer...
    public int numPoints(int[][] points, int r) {
        if (r <= 0 || points.length <= 0)
            return 0;
        int x_min = points[0][0], x_max = points[0][0], y_min = points[0][1], y_max = points[0][1];
        for (int i = 1; i < points.length; ++i)
        {
            if (x_min > points[i][0])
                x_min = points[i][0];
            else if (x_max < points[i][0])
                x_max = points[i][0];

            if (y_min > points[i][1])
                y_min = points[i][1];
            else if (y_max < points[i][1])
                y_max = points[i][1];
        }

        int [] base_data = new int [points.length];
        int [] doubleX = new int [points.length];
        int [] doubleY = new int [points.length];
        int r_square = r * r;
        for (int i = 0; i < base_data.length; ++i)
        {
            doubleX[i] = 2 * points[i][0];
            doubleY[i] = 2 * points[i][1];
            int a = points[i][0] - x_min;
            int b = points[i][1] - y_min;
            base_data[i] = a * a + b * b - r_square;
        }

        long [] current = new long [base_data.length];

        int ret = 0;
        for (int x = x_min; x <= x_max; ++x)
        {
            //base shiftment
            int baseScale = x - x_min;
            int plusScale = x + x_min;
            int local = 0;
            for (int k = 0; k < base_data.length; ++k)
            {
                current[k] = base_data[k] - (doubleX[k] - plusScale) * baseScale;
                if (current[k] <= 0)
                    ++local;
            }

            System.out.println("Current X:" + x + "Current Y:" + y_min);
            System.out.println("Current Value:");
            for (int k = 0; k < base_data.length; ++k)
            {
                System.out.print(current[k] + "\t");
            }
            System.out.println("Real Value:");

            for (int k = 0; k < points.length; ++k)
            {
                System.out.print((points[k][0] - x)*(points[k][0] - x) + (points[k][1] - y_min)*(points[k][1] - y_min) - r_square + "\t");
            }
            System.out.println("End");

            ret = Math.max(local, ret);

            for (int y = y_min + 1; y <= y_max; ++y)
            {
                local = 0;
                plusScale = y + y - 1;
                for (int k = 0; k < base_data.length; ++k)
                {
                    current[k] += (plusScale - doubleY[k]);
                    if (current[k] <= 0)
                        ++local;
                }
                ret = Math.max(local, ret);


                System.out.println("Current X:" + x + "Current Y:" + y);
                System.out.println("Current Value:");
                for (int k = 0; k < base_data.length; ++k)
                {
                    System.out.print(current[k] + "\t");
                }
                System.out.println("Real Value:");

                for (int k = 0; k < points.length; ++k)
                {
                    System.out.print((points[k][0] - x)*(points[k][0] - x) + (points[k][1] - y)*(points[k][1] - y) - r_square + "\t");
                }
                System.out.println("End");

            }
        }

        return ret;
    }

//    https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/discuss/636345/Python-O(n3)-and-O(n2logn)-solution-explained-in-detail-with-pictures
//    https://www.geeksforgeeks.org/angular-sweep-maximum-points-can-enclosed-circle-given-radius/
//    https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/discuss/636444/Python-O(N3)-with-explanation
//    https://www.xarg.org/2016/07/calculate-the-intersection-points-of-two-circles/
//    https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/discuss/636416/c%2B%2B-O(n2logn)-angular-sweep-(with-picture)
//    https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/discuss/636372/JavaC%2B%2BPython-POJ-1981
//    https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/discuss/636345/Python-O(n3)-and-O(n2logn)-solution-explained-in-detail-with-pictures
    public static void main(String [] args) {
        Solution s = new Solution();
        //int[][] points = new int [][] {{-2, 0}, {2, 0}, {0, 2}, {0, -2}};
        // int r = 2;
        int[][] points = new int [][] {{1, 2}, {3, 5}, {1, -1}, {2, 3}, {4,1}, {1,3}};
        int r = 2;
        System.out.println(s.numPoints(points, r));
    }
}
