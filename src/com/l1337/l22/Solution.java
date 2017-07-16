package com.l1337.l22;


import com.l1337.common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private void helper(List<String> ret, char [] tmp, int left, int right, int n) {
        if (left == n) {
            if (right == n) {
                ret.add(new String(tmp));
            } else {
                tmp[left + right] = ')';
                helper(ret, tmp, left, right + 1, n);
            }
        } else if (left > right) {
                tmp[left + right] = '(';
                helper(ret, tmp, left + 1, right, n);
                tmp[left + right] = ')';
                helper(ret, tmp, left, right + 1, n);
        } else if (left == right) {
            tmp[left + right] = '(';
            helper(ret, tmp, left + 1, right, n);
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        if (n > 0) {
            helper(ret, new char [2*n], 0, 0, n);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
