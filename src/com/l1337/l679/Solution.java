package com.l1337.l679;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Solution {

    private Set<Double> getPermutation(int a, Set<Double> set) {
        Set<Double> ret = new HashSet<>();

        Iterator<Double> iter = set.iterator();
        while (iter.hasNext()) {
            double b = iter.next();
            ret.add(a+b);
            ret.add(a*b);
            ret.add(a-b);
//            ret.add(b-a);
            if (b != 0) ret.add(a/b);
//            if (a != 0) ret.add(b/a);
        }

        return ret;
    }

    private void listPeromutation(Set<Double> set) {
//        Iterator<Double> iter = set.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
//        System.out.println("*********");
    }

    private boolean helper(int[] nums) {
        Set<Double> set = new HashSet<>();
        set.add((double)nums[0]);

        for (int i = 1; i < nums.length; ++i) {
            set = getPermutation(nums[i], set);
            listPeromutation(set);
        }

        return set.contains(24.0);
    }

    private void swap(int [] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    private void swap(String [] nums, int i, int j) {
        if (i != j) {
            String tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    //can not handle cases like 1,9,1,2
    private boolean helper(int[] nums, int level) {
        if (level == nums.length)
            return helper(nums);

        for (int k = level; k < nums.length; ++k) {
            swap(nums, level, k);
            if (helper(nums, level + 1))
                return true;

            swap(nums, level, k);
        }
        return false;
    }


    public static boolean isNumeric(String strNum) {
        return strNum.matches("[-+]?\\d+(\\.\\d+)?");
    }

    private boolean meetGoal(String [] data) {
        Stack<Double> st = new Stack<>();
        for(int i = 0; i < data.length; ++i) {
            if (isNumeric(data[i])) {
                st.push(Double.parseDouble(data[i]));
            } else {
                if (st.size() < 2)
                    return false;

                double op1 = st.pop();
                double op2 = st.pop();

                if (data[i].equals("+")) {
                    st.push(op2 + op1);
                } else if (data[i].equals("-")) {
                    st.push(op2 - op1);
                } else if (data[i].equals("*")) {
                    st.push(op2 * op1);
                } else if (data[i].equals("/")) {
                    if (Double.compare(op1, 0.0) == 0)
                        return false;
                    st.push(op2 / op1);
                } else {
                    return false;
                }
            }
        }

        boolean ret = st.size() == 1 && Math.abs(st.peek()-24.0) < 0.0000001;
        if (ret) {
            for (int k = 0; k < data.length; ++k)
                System.out.println(data[k] + "\t");
        }
        return ret;
    }

    private static String [] ops = new String[] {"+", "-", "*", "/"};
    private boolean generateSequence(String [] nums, int i, int opCount, String [] buf, int bufindex) {
        if (bufindex == buf.length) {
            return meetGoal(buf);
        }

        //what to put on bufindex
        if (opCount < 3) {
            for (int opi = 0; opi < ops.length; ++opi) {
                buf[bufindex] = ops[opi];
                if (generateSequence(nums, i, opCount + 1, buf, bufindex + 1))
                    return true;
            }
        }

        //put numbers
        for (int t = i; t < nums.length; ++t) {
            swap(nums, i, t);
            buf[bufindex] = nums[i];
            if (generateSequence(nums, i + 1, opCount, buf, bufindex + 1))
                return true;
            swap(nums, i, t);
        }

        return false;
    }
    public boolean judgePoint24(int[] nums) {

//        Think about why below code is wrong....

//        return helper(nums, 0);

//        char op [] = {'+', '-', '*', '/'};

        String [] nums2 = new String[nums.length];
        for (int i = 0; i < nums.length; ++i)
            nums2[i] = Integer.toString(nums[i]);

        return generateSequence(nums2, 0, 0, new String [7], 0);
    }

//    https://leetcode.com/problems/24-game/discuss/107670/topic
//    https://leetcode.com/problems/24-game/discuss/107673/JAVA-Easy-to-understand.-Backtracking.
//    https://leetcode.com/problems/24-game/discuss/107695/java-recursive-solution
    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.judgePoint24(new int []{5,5,5,1}));
        System.out.println(s.judgePoint24(new int []{3,3,8,8}));
//        System.out.println(s.isNumeric("0"));
//        System.out.println(s.isNumeric("-0"));
//        System.out.println(s.isNumeric("+0"));
//        System.out.println(s.isNumeric("10"));
//        System.out.println(s.isNumeric("-1.0"));
//        System.out.println(s.isNumeric("+0.2"));
//        System.out.println(s.isNumeric("-0.2"));
//        System.out.println(s.isNumeric("-"));
//        System.out.println(s.isNumeric("+"));
//        System.out.println(s.isNumeric("*"));
//        System.out.println(s.isNumeric("/"));
    }
}
