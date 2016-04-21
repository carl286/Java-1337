package com.l1337.l354;

import java.util.*;

//https://leetcode.com/problems/russian-doll-envelopes/
//354. Russian Doll Envelopes
public class Solution {

    boolean canCompete(List<List<int []>> tmp, int index, int[][] envelopes, int i) {
        if (index == 0)
            return true;
        for (int k = 0; k < tmp.get(index-1).size(); ++k)
            if (tmp.get(index-1).get(k)[0] < envelopes[i][0] && tmp.get(index-1).get(k)[1]< envelopes[i][1]) {
                return true;
            }
        return false;
    }
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length <= 0 || envelopes[0].length <= 0)
            return 0;
        Arrays.sort(envelopes, new Comparator<int []>() {
            public int compare(int [] a, int [] b) {
                if (a[0] != b[0])
                    return Integer.compare(a[0],b[0]);
                else
                    return Integer.compare(a[1],b[1]);
            }
        });

        List<List<int []>> tmp = new ArrayList<>();
        tmp.add(new ArrayList<int[]>(Arrays.asList(envelopes[0])));

        for (int i = 1; i < envelopes.length; ++i) {
            int index = Collections.binarySearch(tmp, Arrays.asList(envelopes[i]), new Comparator<List<int []>>() {
                public int compare(List<int []> a, List<int []> b) {
                    return Integer.compare(a.get(a.size()-1)[1], b.get(b.size()-1)[1]);
                }
            });
            if (index < 0) {
                index = -(1 + index);
                if (index == tmp.size()) {
                    //envelopes[i][1] should be large enough now.
//                    if (tmp.get(index-1).get(0)[0] < envelopes[i][0])
//                        tmp.add(Arrays.asList(envelopes[i]));
                    //manual check one item before you is less than you???

                    if (canCompete(tmp, index, envelopes, i))
                        tmp.add(new ArrayList<int[]>(Arrays.asList(envelopes[i])));
                } else {
                    //compete with current row
                    if (tmp.get(index).get(tmp.get(index).size()-1)[1] > envelopes[i][1] && canCompete(tmp, index, envelopes, i)) {
                        tmp.get(index).add(envelopes[i]);
                    }
                }
            }
        }

        return tmp.size();
    }

    public int maxEnvelopesDp1(int[][] envelopes) {
        if (envelopes == null || envelopes.length <= 0 || envelopes[0].length <= 0)
            return 0;
        Arrays.sort(envelopes, new Comparator<int []>() {
            public int compare(int [] a, int [] b) {
                if (a[0] != b[0])
                    return Integer.compare(a[0],b[0]);
                else
                    return Integer.compare(a[1],b[1]);
            }
        });

        int ret = 0;
        int [] tmp = new int [envelopes.length];
        for (int i = 0; i < envelopes.length; ++i) {
            tmp[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (envelopes[i][1] > envelopes[j][1] && envelopes[i][0] > envelopes[j][0])
                    tmp[i] = Math.max(tmp[i], 1 + tmp[j]);
            }

            ret = Math.max(tmp[i], ret);
        }
        return ret;
    }

    //An intutive way to explain why below works....
//    Since the width is increasing, we only need to consider height.
//            [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]

    //Think why this trick works here?????
//    我们还可以使用二分查找法来优化速度，我们首先要做的还是给信封排序，但是这次排序和上面有些不同，信封的宽度还是从小到大排，但是宽度相等时，我们让高度大的在前面。那么现在问题就简化了成了找高度数字中的LIS，完全就和之前那道Longest Increasing Subsequence一样了，所以我们还是使用之前那题解法来做
//    思路：一个最长升序子串的变形，先对数组以width进行升序排序，如果width相等就以height降序排序．因为这样可以保证依次遍历数组的时候后面的width始终比前面的大并且如果高度也大于前面的就一定可以包含前面的．如果不对height降序排列形如[6,4], [6,7]前面是不能包含后面一个的．
    //You can skip some nodes if they
    public int maxEnvelopesIII(int[][] envelopes) {
        if (envelopes == null)
            return 0;
        Arrays.sort(envelopes, new Comparator<int []>() {
            public int compare(int [] a, int [] b) {
                if (a[0] != b[0])
                    return Integer.compare(a[0],b[0]);
                else
                    return Integer.compare(b[1], a[1]);
            }
        });

        List<int []> tmp = new ArrayList<>();

        for (int i = 0; i < envelopes.length; ++i) {
            int index = Collections.binarySearch(tmp, envelopes[i],  new Comparator<int []>() {
                public int compare(int[] a, int[] b) {
                    return Integer.compare(a[1], b[1]);
                }
            });
            if (index < 0) {
                index = -(1 + index);
                if (index == tmp.size())
                    tmp.add(envelopes[i]);
                else
                    tmp.set(index, envelopes[i]);
            }
        }

        return tmp.size();
    }

//    The first glance at this problem seems that it is not that easy. After diving into it, I found that this problem is the "Longest Common SubArray" problem.
//    Let me give some analysis:
//    There are two conditions to ensure that a smaller envelope can be wrapped into another: smaller width and smaller height. So let's first satisfy one condition:
//    Sort the envelopes with their width to get Array1: a1, a2, a3, a4, a5, a6, a7, a8 with O(n*logn)
//    Sort the envelopes with their height to get Array2: b1, b2, b3, b4, b5, b6, b7, b8 with O(n*logn)
//    Any list that satisfy the "Russian doll" must be the subarray of both Array1 and Array2. So our task here is to find the Longest Common SubArray of Array1 and Array2 which can be accomplished with DP in O(n*n)
//    So the total time complexity is O(n*n).


    //You can set array size to avoid using collections
//    public int maxEnvelopes(int[][] envelopes) {
//        if(envelopes == null || envelopes.length == 0
//                || envelopes[0] == null || envelopes[0].length != 2)
//            return 0;
//        Arrays.sort(envelopes, new Comparator<int[]>(){
//            public int compare(int[] arr1, int[] arr2){
//                if(arr1[0] == arr2[0])
//                    return arr2[1] - arr1[1];
//                else
//                    return arr1[0] - arr2[0];
//            }
//        });
//        int dp[] = new int[envelopes.length];
//        int len = 0;
//        for(int[] envelope : envelopes){
//            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
//            if(index < 0)
//                index = -(index + 1);
//            dp[index] = envelope[1];
//            if(index == len)
//                len++;
//        }
//        return len;
//    }


    public static void main(String [] args) {
//        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
//        int[][] envelopes = {{4,5},{4,6},{6,7},{2,3},{1,1}};
//        int[][] envelopes = {{1,10},{6,1},{7,2},{8,3},{1,1}};
//        int[][] envelopes = {{30,50},{12,2},{3,4},{12,15}};
        int[][] envelopes = {{1,15},{7,18},{7,6},{7,100},{2,200},{17,30},{17,45},{3,5},{7,8},{3,6},{3,10},{7,20},{17,3},{17,45}};
        Solution s = new Solution();
//        System.out.println(s.maxEnvelopes(envelopes));
//        System.out.println(s.maxEnvelopesDp1(envelopes));
        System.out.println(s.maxEnvelopesIII(envelopes));

    }
}
