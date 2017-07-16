package com.l1337.l1233;


import java.util.*;

public class Solution {

    public List<String> removeSubfolders(String[] folder) {
        List<String []> folders = new ArrayList<>();
        for (int i = 0; i < folder.length; ++i)
        {
            String [] path = folder[i].split("/");
            if (path.length > 0){
                folders.add(path);
            }
        }

        Collections.sort(folders, new Comparator<String[]>() {
            @Override
            public int compare(String[] t1, String[] t2) {

                int l1 = 0, l2 = 0;
                while (l1 < t1.length && l2 < t2.length)
                {
                    int cmp = t1[l1].compareTo(t2[l2]);
                    if (cmp != 0)
                        return cmp;
                    ++l1;
                    ++l2;
                }

                return t1.length == t2.length ? 0 : (l1 == t1.length ? -1 : +1);
            }
        });

        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < folders.size(); ++i)
        {
            if (ret.size() == 0)
            {
                ret.add(i);
            }
            else if (!isSubPath(folders.get(ret.get(ret.size()-1)), folders.get(i)))
            {
                ret.add(i);
            }
        }
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < ret.size(); ++i)
        {
            tmp.add(String.join("/", folders.get(ret.get(i))));
        }
        return tmp;
    }

    public List<String> removeSubfoldersII(String[] folder) {
        Arrays.sort(folder);

        List<String> tmp = new ArrayList<>();
        if (folder.length <= 0)
            return tmp;
        tmp.add(folder[0]);
        for (int i = 1; i < folder.length; ++i)
        {
            if (!isSubPath(tmp.get(tmp.size()-1), folder[i]))
            {
                tmp.add(folder[i]);
            }
        }
        return tmp;
    }

    private boolean isSubPath(String t1, String t2)
    {
        String [] path1 = t1.split("/");
        String [] path2 = t2.split("/");
        return isSubPath(path1, path2);
    }

    private boolean isSubPath(String [] t1, String []t2)
    {
        int l1 = 0, l2 = 0;
        while (l1 < t1.length && l2 < t2.length)
        {
            int cmp = t1[l1].compareTo(t2[l2]);
            if (cmp != 0)
                return false;
            ++l1;++l2;
        }

        return true;
    }

//    https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/discuss/409028/JavaPython-3-3-methods-from-O(n-*-(logn-%2B-m-2))-to-O(n-*-m)-w-brief-explanation-and-analysis.
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.removeSubfoldersII(new String [] {"/a","/a/b","/c/d","/c/d/e","/c/f"}));
    }
}
