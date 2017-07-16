package com.l1337.l1574;


public class Solution {

    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        if (n <= 1)
            return 0;

//        // below implementation is wrong 1,2,3,10,0,7,8,9
////        //the result should either start from beginning or end at last, can be both
////        int index = 1;
////        while (index < n && arr[index-1] <= arr[index])
////            ++index;
////        //breaks either at n or arr[index] > arr[index+1]
////        int lastIndex = n;
////        while (lastIndex > index)
////        {
////            if (arr[lastIndex-1] >= arr[index-1])
////            {
////                if (lastIndex == n || arr[lastIndex] >= arr[lastIndex-1])
////                    --lastIndex;
////                else
////                    break;
////            }
////            else
////            {
////                break;
////            }
////
////        }
////        int ret = index + (n - lastIndex);
////
////        //another round, doing in reverse
////        lastIndex = n - 1;
////        while (lastIndex > 0 && arr[lastIndex] >= arr[lastIndex-1])
////        {
////            --lastIndex;
////        }
////
////        index = -1;
////        while (index < lastIndex - 1)
////        {
////            if (arr[index + 1] <= arr[lastIndex])
////            {
////                if (index == -1 || arr[index] <= arr[index+1])
////                {
////                    ++index;
////                }
////                else
////                    break;
////            }
////            else
////            {
////                break;
////            }
////        }
////        ret = Math.max(ret, n - lastIndex + index + 1);
////        return n - ret;
        int i = 1;
        while (i < n && arr[i-1] <= arr[i])
            ++i;
        int ret = i; // at least

        int bound = i;
        int end = n - 1;
        while (end > bound)
        {
            if (end + 1 != n && arr[end] > arr[end + 1])
                break; // violates requirement
            while (i > 0 && arr[end] < arr[i-1])
                --i;
            ret = Math.max(i + n - end, ret);
            --end;
        }

        return n - ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findLengthOfShortestSubarray(new int [] {1,2,3,10,0,7,8,9}));
    }
}
