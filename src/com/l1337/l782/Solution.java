package com.l1337.l782;


public class Solution {

//    https://www.jiuzhang.com/problem/transform-to-chessboard/
//    https://leetcode.com/problems/transform-to-chessboard/discuss/114847/C%2B%2BJavaPython-Solution-with-Explanation


//    https://cloud.tencent.com/developer/article/1434585, I don't think the whole analysis is correct...
//    https://www.pianshen.com/article/7368195759/
//    https://www.cnblogs.com/grandyang/p/9053705.html <- best so far....
//    https://blog.csdn.net/magicbean2/article/details/79722329

    /*
    我们发现对于长度为偶数的棋盘，每一行0和1的个数都是相等的，不管我们如何交换行和列，0和1的个数都是不会变化的，再看看长度为奇数的棋盘，比如3:各行的0和1个数不同，但是还是有规律的，每行的1的个数要么为 n/2，要么为 (n+1)/2，这个规律一定要保证，不然无法形成棋盘。
    如果我们只看行，我们发现只有两种情况 0110 和 1001，如果只看列，只有 0011 和 1100，我们发现不管棋盘有多长，都只有两种情况，而这两种情况上各位上是相反的，只有这样的矩阵才有可能转换为棋盘。那么这个规律可以衍生出一个规律，就是任意一个矩形的四个顶点只有三种情况，要么四个0，要么四个1，要么两个0两个1，不会有其他的情况。那么四个顶点亦或在一起一定是0，所以我们判断只要亦或出了1，一定是不对的，直接返回-1。之后我们来统计首行和首列中的1个数，因为我们要让其满足之前提到的规律。统计完了首行首列1的个数，我们判断如果其小于 n/2 或者大于 (n+1) / 2，那么一定无法转为棋盘。我们还需要算下首行和首列跟棋盘位置的错位的个数，虽然 01010 和 10101 都可以是正确的棋盘，我们先默认跟 10101 比较好了，之后再做优化处理。

     */
    public int movesToChessboard(int[][] board) {
        int n = board.length, rowSum = 0, colSum = 0, rowDiff = 0, colDiff = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if ((board[0][0] ^ board[i][0] ^ board[0][j] ^ board[i][j]) != 0)
                    return -1;
        for (int i = 0; i < n; ++i)
        {
            rowSum += board[0][i];
            colSum += board[i][0];
            rowDiff += ((board[i][0] == i % 2) ? 1 : 0);
            colDiff += ((board[0][i] == i % 2) ? 1 : 0);
        }

        if (n / 2 > rowSum || rowSum > (n + 1) / 2) return -1;
        if (n / 2 > colSum || colSum > (n + 1) / 2) return -1;

        if ((n % 2) != 0){
            if ((rowDiff % 2) != 0) rowDiff = n - rowDiff;
            if ((colDiff % 2) != 0) colDiff = n - colDiff;
        } else {
            rowDiff = Math.min(n - rowDiff, rowDiff);
            colDiff = Math.min(n - colDiff, colDiff);
        }
        return (rowDiff + colDiff) / 2;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
