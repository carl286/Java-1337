package com.l1337.l135;


import java.util.Stack;

public class Solution {

    public int candy(int[] ratings) {
        //store value
        Stack<Integer> st = new Stack<>();

        int total = 0;
        int min_req = 0;

        for (int i = 0; i < ratings.length; ++i)
        {
            if (st.isEmpty())
            {
                st.push(ratings[i]);
            }
            else
            {
                if (st.peek() <= ratings[i])
                {
                    boolean isCurved = !(st.size() == 1);
                    int cachedPeek = st.peek();
                    int cur_min = 0;
                    while (!st.isEmpty()) {
                        st.pop();
                        ++cur_min;
                        total += cur_min;
                    }
                    total += Math.max(0, (min_req + 1 - cur_min));
                    min_req = (cachedPeek == ratings[i]) ? 0 : (isCurved ? 1 : (1 + min_req));
                }
                st.push(ratings[i]);
            }

        }

        //end pop
        int cur_min = 0;
        while (!st.isEmpty()) {
            st.pop();
            ++cur_min;
            total += cur_min;
        }
        total += Math.max(0, (min_req + 1 - cur_min));

        return total;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int[] ratings = new int []{1,0,2};
        System.out.println(s.candy(ratings));
    }
}
