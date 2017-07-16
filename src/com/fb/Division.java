package com.fb;

public class Division {

    // calculate a div by b, needs the reminder as well.
    public void div(int a, int b)
    {
        if (b == 0)
        {
            System.out.println("INF");
            return;
        }

        if (a == 0)
        {
            System.out.println("ans: 0, reminder: 0");
            return;
        }
        boolean isPositive = (a > 0 && b > 0) || (a < 0 && b < 0);
        long la = a > 0 ? a : (0l - a);
        long lb = b > 0 ? b : (0l - b);

        long ans = 0l, reminder = 0;
        //find the highest bits
        long llb = lb;
        int shift = 0;
        while (llb <= la)
        {
            llb <<= 1;
            shift++;
        }

        llb >>= 1;
        shift--;

        while (llb >= lb)
        {
            if (la >= llb)
            {
                la -= llb;
                ans += (1L << shift);
            }
            llb >>= 1;
            --shift;
        }

        System.out.println("forget the sing: " + isPositive);
        System.out.println("ans: " + ans + "\t reminder: \t" + la);
    }
    public static void main(String [] args)
    {
//        System.out.println(-7/3);
//        System.out.println(-7%3);

        Division division = new Division();
        division.div(Integer.MIN_VALUE,-1);
    }
}
