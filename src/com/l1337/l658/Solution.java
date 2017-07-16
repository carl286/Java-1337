package com.l1337.l658;


import java.util.ArrayList;
import java.util.List;

public class Solution {

//    https://leetcode.com/problems/find-k-closest-elements/discuss/106419/O(log-n)-Java-1-line-O(log(n)-+-k)-Ruby
//    https://leetcode.com/problems/find-k-closest-elements/discuss/106424/Java-4-Liner-and-O(n)-Time-MagicDictionary
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // k >= 1 && k < arr.length
        List<Integer> ret = new ArrayList<>();
        //some quick wins
        int start;
        if (x <= arr[0])
            start = 0;
        else if (x >= arr[arr.length-1])
            start = arr.length - k;
        else {
            // arr[0] < x < arr[arr.length-1]
            //binary search
//            arr[y] <= x, 0<=x<length-1;
            start = -1;
            int end = arr.length;
            while (start + 1 != end) {
                int mid = ((end - start) >> 1) + start;
                if (arr[mid] > x)
                    end = mid;
                else
                    start = mid;
            }

            end = start;
            while (end - start + 1 < k) {
                //needs expand
                if (start == 0)
                    ++end;
                else if (end == arr.length - 1)
                    --start;
                else {
                    int diff1 = Math.abs(arr[start-1] - x);
                    int diff2 = Math.abs(arr[end+1] - x);
                    if (diff1 <= diff2) {
                        --start;
                    } else {
                        ++end;
                    }
                }

            }

        }

        while (ret.size() < k) {
            ret.add(arr[start + ret.size()]);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] arr = new int[]{1,2,3,4,5};
        int k=4, x=3;
        List<Integer> l = s.findClosestElements(arr, k, x);
        for (int i = 0; i < l.size(); ++i)
            System.out.println(l.get(i));
    }
}
