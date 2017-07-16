package com.uber;

public class FindEventNumbers {

    // n 找到1-n中存在0，2，4的数字的个数
    public int findEventNumbers(int n){
        // 2 147 483 647 max signed int has 10 digits
        int data [] = new int [10];
        data[0] = 3;//0.2.4
        int base = 1;
        for (int i = 1; i < data.length; ++i)
        {
            base = base *10;
            data[i] = 7 * data[i-1] + 2 * base;
        }


        //assume n >= 1
        //find how many digits it has
        int countDigits = 0;
        int m = n;
        while (m > 0)
        {
            m /= 10;
            ++countDigits;
        }


        return 0;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
