package com.l1337.l969;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ret = new ArrayList<>();
        int [] second = Arrays.copyOf(arr, arr.length);
        Arrays.sort(second);

        int index = second.length - 1;
        while (index >= 0)
        {
            if (second[index] == arr[index])
            {
                --index;
            }
            else
            {
                //find
                int val = second[index];
                int i = 0;
                while (i < index && arr[i] != val)
                {
                    ++i;
                }
                ret.add(i + 1);
                flip(arr, 0, i);

                ret.add(index+1);
                flip(arr, 0, index);
            }

        }

        return ret;
    }

    private void flip(int [] arr, int i, int j)
    {
        //i < j
        while (i < j)
        {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            ++i;
            --j;
        }
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] arr = new int [] {3,2,4,1};
        List<Integer> ret = s.pancakeSort(arr);
        for(int i = 0; i < ret.size(); ++i)
            System.out.print(ret.get(i) + "\t");
        System.out.println("Hello World");
        for(int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + "\t");
    }
}
