package com.l1337.l409;


public class Solution {

    private int toIndex(char c) {
        if (Character.isLowerCase(c))
            return c - 'a';
        else
            return c - 'A' + 26;
    }
    public int longestPalindrome(String s) {
        if (s.length() <= 1)
            return s.length();
        int data [] = new int [52];
        for (char c : s.toCharArray())
            ++data[toIndex(c)];
        int ret = 0;
        int max_single = 0;
        int total = 0;
        for (int i = 0; i < data.length; ++i) {
            if ((data[i] & 0x01) == 0)
                ret += data[i];
            else {
                System.out.println("Singles:" + data[i]);
                max_single = Math.max(max_single, data[i]);
            }
            System.out.println(i + "\t" + data[i]);
            total += data[i];
        }
        System.out.println(total);
        return ret + max_single;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String ss = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        System.out.println(s.longestPalindrome(ss));
    }
}
