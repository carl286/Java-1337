package com.l1337.l609;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> ret = new ArrayList<>();

        //content -> file paths
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < paths.length; ++i) {
            String [] splitPaths = paths[i].split(" ");

            for (int j = 1; j < splitPaths.length; ++j) {
                String fileName = splitPaths[j].substring(0, splitPaths[j].indexOf("("));
                String fileContent = splitPaths[j].substring(splitPaths[j].indexOf("(")+1, splitPaths[j].length()-1);
                List<String> list = map.get(fileContent);
                if (list != null) {
                    list.add(splitPaths[0] + "/" + fileName);
                } else {
                    list = new ArrayList<>();
                    list.add(splitPaths[0] + "/" + fileName);
                    map.put(fileContent, list);
                }
            }
        }

        for (List<String> l : map.values()) {
            if (l.size() > 1)
                ret.add(l);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findDuplicate(new String[] {
            "root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"
        }));
    }
}
