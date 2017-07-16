package com.l1337.l158;


public class Solution {

    /*
    https://www.lintcode.com/problem/read-n-characters-given-read4-ii-call-multiple-times/

     */

    private char [] _internalBuf;
    private int _unreadStart;
    private int _unreadCnt;
    public Solution() {
        this._internalBuf = new char[4];
        this._unreadStart = 0;
        this._unreadCnt = 0;
    }
    /* The read4 API is defined in the parent class Reader4. */
      int read4(char[] buf)
      {
          return -1;
      }
    /**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
    public int read(char[] buf, int n) {
        int totalReads = 0;
        while (totalReads < n && this._unreadCnt > 0)
        {
            buf[totalReads++] = this._internalBuf[this._unreadStart++];
            --this._unreadCnt;
        }

        int cnt;
        while (totalReads < n && (cnt = read4(this._internalBuf)) > 0)
        {
            int cntToCopy = cnt;
            if (totalReads + cnt > n)
            {
                cntToCopy = n - totalReads;
                this._unreadCnt = cnt - cntToCopy;
                this._unreadStart = cntToCopy;
            }

            for(int k = 0; k < cntToCopy; ++k)
                buf[totalReads++] = this._internalBuf[k];

            //add a check to see if cnt < 4 to avoid an extra read4 call
            //we may consider to add a flag to indicate if the buf is emtpy now...
        }

        return totalReads;
    }

//    https://github.com/grandyang/leetcode/issues/158
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
