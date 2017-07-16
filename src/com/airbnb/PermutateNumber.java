package com.airbnb;

import javafx.util.Pair;

import java.util.Arrays;

public class PermutateNumber {

    /*
    warm up:
Given input of digit array, e.g. [4,1,8] return the smallest number can be created using all digits。 For example [7,3,0,1] --> "1037"
限定条件：输入digit可以有0， 结果可能overflow。输出结果可以是string
解法把input digits sort in ascend order, concanate 成number。如果有0，把所有0放在第一个digit后面就行。

follow up 1:
what's the time complexity, can you do in O(N) time.  --> bucket sort input digitis.

follow up 2:
Similar to warmup question, input 多加一个lower bound. 要求输出最小的number, 但是要大于或等于lower bound.
解法其实就是find all permutations, 找出大于或等于lower bound的最小值。
     */
    public String permutateI(int [] data)
    {
        Arrays.sort(data);
        StringBuilder sb = new StringBuilder();

        int countZero = 0;
        while (countZero < data.length && data[countZero] == 0)
            countZero++;
        int start = countZero;
        while (start < data.length)
        {
            sb.append(data[start]);
            ++start;
            if (start < data.length && data[start] != data[start+1])
                break;
        }
        while (countZero-- > 0)
            sb.append(0);
        while (start < data.length)
        {
            sb.append(data[start++]);
        }
        return sb.toString();
    }

    public String permutateI_On(int [] data)
    {
        StringBuilder sb = new StringBuilder();
        int sorted [] = new int [10];
        for(int i = 0; i < data.length; ++i)
            ++sorted[data[i]];

        int first = 1;
        while (first < sorted.length && sorted[first] == 0)
            ++first;
        if (first < sorted.length)
        {
            while (sorted[first]-- > 0)
                sb.append(first);
        }
        //fill zeros and others
        for(int i = 0; i <sorted.length; ++i)
            while (sorted[i]-- > 0)
                sb.append(i);
        return sb.toString();
    }

    private int compare(int [] tmp, int [] lowerBound)
    {
        //assume all positive, no leading zeros...
        if (tmp.length < lowerBound.length)
            return -1;
        else if (tmp.length > lowerBound.length)
            return +1;
        for(int k = 0; k < tmp.length; ++k)
        {
            int diff = tmp[k] - lowerBound[k];
            if (diff != 0)
                return diff > 0 ? +1 : -1;
        }
        return 0;
    }
    private Pair<Boolean, String> permutate_No_Less_Than_Lower_BoundHelper(int [] sorted, int [] lowerBound, int [] tmp, int index)
    {
        if (index == tmp.length)
        {
            int cmp = compare(tmp, lowerBound);
            if (cmp >= 0)
            {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < tmp.length; ++i)
                    sb.append(tmp[i]);
                return new Pair<Boolean, String>(true, sb.toString());
            }
            else
            {
                return new Pair<>(false, null);
            }
        }
        else
        {
            //get the max for sorted to confirm if we can proceed.
            //skip for now
            //find first unused
            for (int k = 0; k < sorted.length; ++k)
            {
                if (sorted[k] > 0 && ((k != 0) || (k == 0 && index > 0)))
                {
                    --sorted[k];
                    tmp[index] = k;
                    Pair<Boolean, String> lower = permutate_No_Less_Than_Lower_BoundHelper(sorted, lowerBound, tmp, index+1);
                    ++sorted[k];
                    if (lower.getKey())
                    {
                        return lower;
                    }
                }
            }
        }


        return new Pair<>(false, null);
    }
    public String permutate_No_Less_Than_Lower_Bound(int [] data, String lowerBound)
    {
        int sorted [] = new int [10];
        for(int i = 0; i < data.length; ++i)
            ++sorted[data[i]];

        int [] lower = new int [lowerBound.length()];
        for (int i = 0; i < lower.length; ++i)
            lower[i] = lowerBound.charAt(i) - '0';
        Pair<Boolean, String> ret = permutate_No_Less_Than_Lower_BoundHelper(sorted, lower, new int [data.length], 0);
        return ret.getValue();
    }
    public static void main(String [] args) {
        PermutateNumber permutateNumber = new PermutateNumber();
//        System.out.println(permutateNumber.permutateI(new int [] {4,1,8}));
//        System.out.println(permutateNumber.permutateI(new int [] {7,3,0,1}));
//
//        System.out.println(permutateNumber.permutateI_On(new int [] {4,1,8}));
//        System.out.println(permutateNumber.permutateI_On(new int [] {7,3,0,1}));

        System.out.println(permutateNumber.permutate_No_Less_Than_Lower_Bound(new int [] {4,1,3,9}, "200"));
//        System.out.println(permutateNumber.permutate_No_Less_Than_Lower_Bound(new int [] {7,2}, "28"));
    }
}
