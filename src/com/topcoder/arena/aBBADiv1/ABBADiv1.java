package com.topcoder.arena.aBBADiv1;

/**
 * Created by Ke.Liu on 7/24/16.
 */
public class ABBADiv1 {
    private static final String FOUND = "Possible";
    private static final String UNFOUND = "Impossible";
    private static final char A = 'A';
    private static final char B = 'B';

    //for a string has at least one charater, s < e, if s == e, it's empty string.
    private boolean canObtainHelper(StringBuilder sb1, int start1, int end1, StringBuilder sb2, int start2, int end2) {
        int l1 = end1 - start1;
        int l2 = end2 - start2;
        if (l1 < l2) {
            //rule 1, add A at tail
            if (sb2.charAt(end2-1) == A) {
                if (canObtainHelper(sb1, start1, end1, sb2, start2, end2-1))
                    return true;
            }
            //rule 2, add B at tail and reverse
            if (sb2.charAt(start2) == B) {
                return canObtainHelper(sb1, start1, end1, new StringBuilder(sb2.substring(start2+1, end2)).reverse(), 0, end2 - start2 - 1);
            }

            return false;
        } else if (l1 > l2) {
            return false;
        } else {
            while (start1 < end1 && sb1.charAt(start1) == sb2.charAt(start2)) {
                ++start1;
                ++start2;
            }
            return start1 == end1;
        }
    }

    //we ensure len(initial) in  [1,49] and len(target) in [2,50], len(target) > len(initial)
    //also, only A and B will be there.
    public  String canObtain(String initial, String target) {
        return canObtainHelper(new StringBuilder(initial), 0, initial.length(), new StringBuilder(target), 0, target.length()) ? FOUND : UNFOUND;
    }


    public static void main(String [] args) {
        ABBADiv1 abbadiv1 = new ABBADiv1();
//        System.out.println(abbadiv1.canObtain("BAAAAABAA", "BAABAAAAAB"));
        System.out.println(abbadiv1.canObtain("A", "ABBA"));
//        System.out.println(abbadiv1.canObtain("AAABBAABB", "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"));
//        System.out.println(abbadiv1.canObtain("AAABAAABB", "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"));
    }
}
