package com.leetcode;

//	158, Read N Characters Given Read4 II - Call multiple times
//The API: int read4(char *buf) reads 4 characters at a time from a file.
//	The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
//	By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
//	Note:
//	The read function may be called multiple times.

public class Reader4 {
    private final char[] buffer = new char[4];
    private int remaining = 0;

    //So actual file is some where else....
    int read4(char[]buf) {
        return 0;
    }

    public int read(char[] buf, int n) {
        if (n <= 0)
            return n;

        int i = 0;
        if (remaining > 0) {
            int bytesToCopy = Math.min(n, remaining);
            System.arraycopy(buffer /*src*/, 4 - bytesToCopy /*srcPos*/, buf /*dest*/, i /*destPos*/, bytesToCopy /*length*/);
            n -= bytesToCopy;
            remaining -= bytesToCopy;
            i += bytesToCopy;
        }

        int actualBytesRead;
		while ((actualBytesRead=read4(buf)) != 0 && n > 0) {
            int bytesToCopy = Math.min(n, actualBytesRead);
			System.arraycopy(buffer /*src*/, 0 /*srcPos*/, buf /*dest*/, i /*destPos*/, bytesToCopy /*length*/);
            remaining -= bytesToCopy;
            n -= bytesToCopy;
            i += bytesToCopy;
		}
		return i;
    }
}
