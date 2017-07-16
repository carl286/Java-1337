package com.l1337.l824;


public class Solution {

    private boolean isVowel(int c)
    {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    public String toGoatLatin(String S) {
        String [] ss = S.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ss.length; ++i)
        {
            StringBuilder local = new StringBuilder(ss[i]);
            char c = local.charAt(0);
            if (!isVowel(local.charAt(0)))
            {
                local.deleteCharAt(0);
                local.append(c);
            }

            local.append("ma");

            int k = 0;
            while (k++ < i + 1)
            {
                local.append('a');
            }
            sb.append(local);
            if (i + 1 != ss.length)
                sb.append(' ');
        }

        return sb.toString();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.toGoatLatin("I speak Goat Latin"));
    }
}
