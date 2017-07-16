package com.l1337.l1108;


public class Solution {

    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.defangIPaddr("1.1.1.1"));
    }
}
