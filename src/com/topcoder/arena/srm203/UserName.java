package com.topcoder.arena.srm203;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class UserName
{
    int stringCompare(String o1, String o2) {

        int i;
        for (i = 0; i < o1.length() && i < o2.length() && o1.charAt(i) == o2.charAt(i); ++i);

        if (i == o1.length()) {
            if (i == o2.length())
                return 0;
            else
                return -1;
        } else {
            if (i == o2.length())
                return +1;
            if (Character.isDigit(o1.charAt(i)) && Character.isDigit(o2.charAt(i))) {
                int r1 = o1.length() - i;
                int r2 = o2.length() - i;
                if (r1 < r2)
                    return -1;
                else if (r1 > r2)
                    return +1;
            }

            if (o1.charAt(i) < o2.charAt(i))
                return -1;
            else
                return +1;
        }
    }
    public String newMember(String[] existingNames, String newName)
    {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return stringCompare(o1,o2);
            }
        };
        Arrays.sort(existingNames, comparator);
        int find = Arrays.binarySearch(existingNames, newName, comparator);
        if (find < 0)
            return newName;
        int counter = 1;
        ++find;
        while (find < existingNames.length) {
            if ((newName + counter).equals(existingNames[find])) {
                ++find;
                ++counter;
            } else {
                break;
            }
        }
        return newName + counter;
    }


    public String newMemberII(String[] existingNames, String newName) {
        int [] arr = new int[51];
        String res = newName;
        Arrays.sort(existingNames);
        int k = 1;
        for (int j = 0; j < 2; ++j)
            for (int i = 0; i < existingNames.length; ++i)
                if (existingNames[i].equals(res))
                    res = newName + String.valueOf(k++);
        return res;
    }

    public static void main(String [] args) {

        String[] existingNames = {"Bart4", "Bart5", "Bart6", "Bart7", "Bart8", "Bart9", "Bart10",
                "Lisa", "Marge", "Homer", "Bart", "Bart1", "Bart2", "Bart3",
                "Bart11", "Bart12","Bart13","Bart14","Bart15","Bart16","Bart17","Bart18","Bart19","Bart20","Bart21","Bart22","Bart23","Bart24","Bart25","Bart26","Bart27","Bart28","Bart29","Bart30","Bart31","Bart32","Bart33","Bart34","Bart35","Bart36","Bart37","Bart38","Bart39","Bart40","Bart41","Bart42","Bart43","Bart44","Bart45","Bart46","Bart47","Bart48","Bart49","Bart50","Bart51","Bart52","Bart53","Bart54","Bart55","Bart56","Bart57","Bart58","Bart59","Bart60","Bart61","Bart62","Bart63","Bart64","Bart65","Bart66","Bart67","Bart68","Bart69","Bart70","Bart71","Bart72","Bart73","Bart74","Bart75","Bart76","Bart77","Bart78","Bart79","Bart80","Bart81","Bart82","Bart83","Bart84","Bart85","Bart86","Bart87","Bart88","Bart89","Bart90","Bart91","Bart92","Bart93","Bart94","Bart95","Bart96","Bart97","Bart98","Bart99","Bart100"
        };
        String newName = "Bart";
//
//        for (int k = 13; k <= 100; ++k)
//            System.out.print("\""+"Bart"+k+"\",");
        UserName u = new UserName();
//        System.out.println(u.newMember(existingNames, newName));
        System.out.println(u.newMemberII(existingNames, newName));

    }
}