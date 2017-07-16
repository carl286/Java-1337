package com.l1337.l157;


public class Solution {


//    http://buttercola.blogspot.com/2014/11/leetcode-read-n-characters-given-read4.html
    /*
    Read N Characters Given Read4
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function will only be called once for each test case.
     */

    /*
    Easy
Given a file and assume that you can only read the file using a given method read4, implement a method to read_n_characters.
Method read4:
The API read4reads 4 consecutive characters from the file, then writes those characters into the buffer arraybuf.
The return value is the number of actual characters read.
Note that read4()has its own file pointer, much likeFILE *fpin C.
Definition of read4:
    Parameter:  char[] buf
    Returns:    int

Note: buf[] is destination not source, the results from read4 will be copied to buf[]
Below is a high level example of howread4works:
File file("abcdefghijk"); // File is "abcdefghijk", initially file pointer (fp) points to 'a'
char[] buf = new char[4]; // Create buffer with enough space to store characters
read4(buf); // read4 returns 4. Now buf = "abcd", fp points to 'e'
read4(buf); // read4 returns 4. Now buf = "efgh", fp points to 'i'
read4(buf); // read4 returns 3. Now buf = "ijk", fp points to end of file
Method read:
By using theread4method, implement the method readthat readsncharacters from the file and store it in the buffer array buf. Consider that you cannot manipulate the file directly.
The return value is the number of actual characters read.
Definition of read:
    Parameters:    char[] buf, int n
    Returns:    int

Note: buf[] is destination not source, you will need to write the results to buf[]
Example 1:
Input:
file = "abc", n = 4

Output:
3

Explanation:
 After calling your read method, buf should contain "abc". We read a total of 3 characters from the file, so return 3. Note that "abc" is the file's content, not buf. buf is the destination buffer that you will have to write the results to.
Example 2:
Input:
file = "abcde", n = 5

Output:
5

Explanation:
After calling your read method, buf should contain "abcde". We read a total of 5 characters from the file, so return 5.
Example 3:
Input:
file = "abcdABCD1234", n = 12

Output:
12

Explanation:
After calling your read method, buf should contain "abcdABCD1234". We read a total of 12 characters from the file, so return 12.
Example 4:
Input:
file = "leetcode", n = 5

Output:
5

Explanation:
After calling your read method, buf should contain "leetc". We read a total of 5 characters from the file, so return 5.
Note:
Consider that you cannot manipulate the file directly, the file is only accesible for read4 but not for read.
The read function will only be called once for each test case.
You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n  characters.

     */
    private int read4(char [] buf)
    {
        return -1;
    }
    private int BUF_SIZE = 4;
//    https://aaronice.gitbook.io/lintcode/data_structure/read-n-characters-given-read4
    public int read(char[] buf, int n) {
        //you still need remember that you would need write data back to buf, hence assume buf has enough capacity.
        char [] tmp = new char [BUF_SIZE];
        int index = 0, cnt = 0;
        while(index < n && (cnt = read4(tmp)) > 0)
        {
            int valsCopy = cnt;
            if (index + cnt >  n)
            {
                valsCopy = n - index;
            }

            System.arraycopy(tmp, 0, buf, index, valsCopy);
            index += valsCopy;

            //add a check to see if cnt < 4 to avoid an extra read4 call
        }

        return index;
    }

//    https://www.lintcode.com/problem/read-n-characters-given-read4-ii-call-multiple-times/description
//    https://wxx5433.gitbooks.io/interview-preparation/content/part_ii_leetcode_lintcode/string/read_n_characters_given_read4_ii.html
    //not too interesting once you know the gap..
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
