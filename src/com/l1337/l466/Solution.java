package com.l1337.l466;


import java.util.HashSet;
import java.util.Set;

public class Solution {

    private Set<Character> getStringSet(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            set.add(s.charAt(i));
        }
        return set;
    }

//    https://leetcode.com/problems/count-the-repetitions/description/

    //BF
//    https://leetcode.com/problems/count-the-repetitions/discuss/95401/Ugly-Java-brute-force-solution-but-accepted.-1088ms.

//    https://leetcode.com/problems/count-the-repetitions/discuss/95398/C++-solution-inspired-by-@70664914-with-organized-explanation
//    Fact:
//    If s2 repeats in S1 R times, then S2 must repeats in S1 R / n2 times.
//    Conclusion:
//    We can simply count the repetition of s2 and then divide the count by n2.
//

//    https://leetcode.com/problems/count-the-repetitions/discuss/95397/C++-0ms-O(str1.length*str2.length)
//    https://leetcode.com/problems/count-the-repetitions/discuss/95397/c-0ms-ostr1lengthstr2length
//    https://leetcode.com/problems/count-the-repetitions/discuss/95402/Very-clean-and-short-7ms-Java-solution-based-on-@70664914-'s-idea
    public int getMaxRepetitionsBF(String s1, int n1, String s2, int n2) {

        //s1.length() * n1 can be hold in an int, max is around 10^8
        if (s1.length() * n1 < s2.length() * n2)
            return 0;
        //quick set check
        Set<Character> set1 = getStringSet(s1);
        Set<Character> set2 = getStringSet(s2);
        if (!set1.containsAll(set2))
            return 0;

        int max_potential = (s1.length() * n1) / (s2.length() * n2);
        int loop = 0;
        int i1 = 0, i2 = 0;
        int k1 = 0, k2 = 0;
        while (loop < max_potential && k1 < n1) {
            if (s1.charAt(i1) == s2.charAt(i2)) {
                ++i1;
                ++i2;

                if (i2 == s2.length()) {
                    i2 = 0;
                    ++k2;

                    if (k2 == n2) {
                        k2 = 0;
                        ++loop;
                    }
                }
            } else {
                ++i1;
            }

            if (i1 == s1.length()) {
                i1 = 0;
                ++k1;
            }
        }

        return loop;
    }

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int[] reps = new int[102];
        int[] rests = new int[102];
        int posRest=0, repTime=0;
        int i=0, k=0;
        if(n1 <= 0) return 0;
        while(k==i) {
            i++;
            for(int j=0; j<s1.length(); j++) {
                if(s2.charAt(posRest) == s1.charAt(j)) {
                    posRest++;
                    if(posRest == s2.length()) {
                        repTime++;
                        posRest=0;
                    }
                }
            }
            if(i >= n1)
                return repTime / n2;
            for(k=0; k<i; k++){
                if(posRest == rests[k])
                    break;
            }
            reps[i] = repTime;
            rests[i] = posRest;
        }
        int interval = i-k;
        int repeatCount = (n1-k) / interval;
        int repeatTimes = repeatCount * (reps[i]-reps[k]);
        int remainTimes = reps[(n1-k) % interval + k];
        return (repeatTimes + remainTimes) / n2;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.getMaxRepetitions("caahumeayllfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikeffmznimkkasvwsrenazkycxaa", 1000000, "aac", 100));
    }
}
