package com.l1337.l85;


import java.util.Stack;

public class Solution {

    public int largestRectangleArea(int[] heights) {
        int ret = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for (int i = 0; i < heights.length; ++i)
        {
            if (st.peek() < 0)
            {
                st.push(i);
            } else if (heights[st.peek()] <= heights[i])
            {
                st.push(i);
            } else
            {
                //right end use index i, how to find left end
                while (st.peek() != -1 && heights[st.peek()] > heights[i])
                {
                    int index_h = st.pop();
                    int left_index = st.peek();
                    int width = i - left_index - 1;
                    int area = width * heights[index_h];
                    ret = Math.max(area, ret);
                }

                st.push(i);
            }
        }

        //keep popping
        while (st.peek() != -1)
        {
            int index_h = st.pop();
            int left_index = st.peek();
            int width = heights.length - left_index - 1;
            int area = width * heights[index_h];
            ret = Math.max(area, ret);
        }

        return ret;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length <= 0 || matrix[0].length <= 0)
            return 0;
        int ret = 0;
        int [] heights = new int [matrix[0].length];
        for (int i = 0; i < matrix.length; ++i)
        {
            for (int j = 0; j < matrix[i].length; ++j){
                if (matrix[i][j] == '0')
                    heights[j] = 0;
                else
                    heights[j] = 1 + heights[j];
            }
            ret = Math.max(ret, largestRectangleArea(heights));
        }

        return ret;
    }

    // https://zhuanlan.zhihu.com/p/69229392
    
    public static void main(String [] args) {
        Solution s = new Solution();
        // int [] heights = new int []{2,1,5,6,2,3};
        //int [] heights = new int []{2,1,2};
        //int [] heights = new int []{0,1,0,1};
        // int [] heights = new int []{0,0,1};
        int [] heights = new int [] {0,0,0,0,2147483647};
        System.out.println(s.largestRectangleArea(heights));
    }
}
