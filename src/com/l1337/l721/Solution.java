package com.l1337.l721;


import com.googlePrep.Pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    private boolean isInterSect(Set<String> s1, Set<String> s2) {
        Set<String> s3 = new HashSet<>(s1);
        s3.addAll(s2);
        return s3.size() != s1.size() + s2.size();
    }


//    https://leetcode.com/problems/accounts-merge/discuss/109157/JavaC++-Union-Find
//    https://leetcode.com/problems/accounts-merge/discuss/109158/Java-Solution-(Build-graph-+-DFS-search)
//    https://leetcode.com/problems/accounts-merge/discuss/140978/Easy-to-Understand-Union-Find-in-Java-95
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //TLE for below
//        List<List<String>> ret = new ArrayList<>();
//        Collections.sort(accounts, (l1, l2) -> (l1.get(0).compareTo(l2.get(0))));
//
//        int cur = 0;
//        while (cur < accounts.size()) {
//
//            List<Set<String>> tmp = new LinkedList<>();
//            int start = cur;
//            tmp.add(new HashSet<String>(accounts.get(cur).subList(1, accounts.get(cur).size())));
//            ++cur;
//            while (cur < accounts.size() && accounts.get(cur).get(0).equals(accounts.get(start).get(0))) {
//                tmp.add(new HashSet<String>(accounts.get(cur).subList(1, accounts.get(cur).size())));
//                ++cur;
//            }
//
//            while (true) {
//                boolean madeProgress = false;
//
//                for (int i = 0; i < tmp.size(); ++i) {
//                    int j = i + 1;
//                    while (j < tmp.size()) {
//                        if (isInterSect(tmp.get(i), tmp.get(j))) {
//                            tmp.get(i).addAll(tmp.get(j));
//                            tmp.remove(j);
//                            madeProgress = true;
//                        } else {
//                            ++j;
//                        }
//                    }
//                }
//                if (!madeProgress)
//                    break;
//            }
//
//            for (int i = 0; i < tmp.size(); ++i) {
//                List<String> local = new ArrayList<>(tmp.get(i));
//                Collections.sort(local);
//                local.add(0, accounts.get(start).get(0));
//                ret.add(local);
//            }
//        }
//
//        return ret;

        List<List<String>> ret = new ArrayList<>();
        Collections.sort(accounts, (l1, l2) -> (l1.get(0).compareTo(l2.get(0))));

        int cur = 0;
        while (cur < accounts.size()) {

            //try union find the merges..
            Map<String, Integer> emailToId = new HashMap<>();
            Map<Integer, Integer> idToParentId = new HashMap<>();
            int id = 0;
            int start = cur;
            for (int k = 1; k < accounts.get(cur).size(); ++k) {
                if (!emailToId.containsKey(accounts.get(cur).get(k))) {
                    emailToId.put(accounts.get(cur).get(k), id);
                    idToParentId.put(id, id);
                    ++id;
                }

                //merge k with k - 1
                if (k > 1) {
                    //assign parent k - 1 to parent k
                    merge(idToParentId, emailToId.get(accounts.get(cur).get(k-1)), emailToId.get(accounts.get(cur).get(k)));
                }
            }
            ++cur;

            while (cur < accounts.size() && accounts.get(cur).get(0).equals(accounts.get(start).get(0))) {
                for (int k = 1; k < accounts.get(cur).size(); ++k) {
                    if (!emailToId.containsKey(accounts.get(cur).get(k))) {
                        emailToId.put(accounts.get(cur).get(k), id);
                        idToParentId.put(id, id);
                        ++id;
                    }

                    //merge k with k - 1
                    if (k > 1) {
                        //assign parent k - 1 to parent k
                        merge(idToParentId, emailToId.get(accounts.get(cur).get(k-1)), emailToId.get(accounts.get(cur).get(k)));
                    }
                }
                ++cur;
            }

            //parent id to list
            Map<Integer, List<String>> idToListMap = new HashMap<>();
            for (Map.Entry<String, Integer> entry : emailToId.entrySet()) {
                int parent = getParent(idToParentId, entry.getValue());
                List<String> list = idToListMap.getOrDefault(parent, new ArrayList<>());
                list.add(entry.getKey());
                idToListMap.put(parent, list);
            }

            for (List<String> list : idToListMap.values()) {
                Collections.sort(list);
                list.add(0, accounts.get(start).get(0));
                ret.add(list);
            }
        }

        return ret;
    }


    int getParent(Map<Integer, Integer> idToParentId, int id) {
        while (idToParentId.get(id) != id) {
            int oldParent = idToParentId.get(id);
            idToParentId.put(id, idToParentId.get(oldParent));
            id = oldParent;
        }
//        return idToParentId.get(id);
        return id;
    }

    void merge(Map<Integer, Integer> idToParentId, int idx, int idy) {
        if (idx != idy) {
            int parentX = getParent(idToParentId, idx);
            int parentY = getParent(idToParentId, idy);
//            if (parentX < parentY)
                idToParentId.put(parentY, parentX);
//            else
//                idToParentId.put(parentX, parentY);
        }
    }

























    public class UnionFind
    {
        private int [] parent;
        private int distinct;

        public UnionFind(int n)
        {
            parent =  new int [n];
            for (int i = 0; i < parent.length; ++i)
                parent[i] = i;
            distinct = n;
        }

        public int getParent(int i)
        {
            while (i != parent[i])
            {
                i = parent[i] = parent[parent[i]];
            }
            return i;
        }

        public int count()
        {
            return this.distinct;
        }

        public void connect(int i, int j)
        {
            int parent_i = getParent(i);
            int parent_j = getParent(j);
            if (parent_i != parent_j)
            {
                parent[parent_i] = parent[parent_j];
                --this.distinct;
            }

        }
    }

    public List<List<String>> accountsMergeMarch8_21(List<List<String>> accounts) {
        List<List<String>> ret = new ArrayList<>();
        Collections.sort(accounts, (a, b) -> (a.get(0).compareTo(b.get(0))));

        Map<String, Integer> nodes = new HashMap<>();
        Map<Integer, Set<Integer>> edges = new HashMap<>();

        for (int i = 0; i < accounts.size(); ++i)
        {
            if (i != 0 && !accounts.get(i).get(0).equals(accounts.get(i-1).get(0)))
            {
                UnionFind uf = new UnionFind(nodes.size());
                for(Map.Entry<Integer, Set<Integer>> entry: edges.entrySet())
                {
                    for(Integer v: entry.getValue())
                        uf.connect(entry.getKey(), v);
                }
                Map<Integer, Set<String>> group = new HashMap<>();
                for (Map.Entry<String, Integer> n : nodes.entrySet())
                {
                    Set<String> s = group.getOrDefault(uf.getParent(n.getValue()), new HashSet<>());
                    s.add(n.getKey());
                    group.put(uf.getParent(n.getValue()), s);
                }

                for(Set<String> s: group.values())
                {
                    List<String> l = new ArrayList<>(s);
                    Collections.sort(l);
                    l.add(0, accounts.get(i-1).get(0));
                    ret.add(l);
                }
                nodes.clear();
                edges.clear();
            }

            Integer first = nodes.getOrDefault(accounts.get(i).get(1), null);
            if (first == null)
            {
                first = nodes.size();
                nodes.put(accounts.get(i).get(1), first);
            }

            //continue to be in the same group
            for(int j = 2; j < accounts.get(i).size(); ++j)
            {
                Integer second = nodes.getOrDefault(accounts.get(i).get(j), null);
                if (second == null)
                {
                    second = nodes.size();
                    nodes.put(accounts.get(i).get(j), second);
                }
                Set<Integer> neighbors = edges.getOrDefault(first, new HashSet<>());
                neighbors.add(second);
                edges.put(first, neighbors);
            }
        }

        UnionFind uf = new UnionFind(nodes.size());
        for(Map.Entry<Integer, Set<Integer>> entry: edges.entrySet())
        {
            for(Integer v: entry.getValue())
                uf.connect(entry.getKey(), v);
        }
        Map<Integer, Set<String>> group = new HashMap<>();
        for (Map.Entry<String, Integer> n : nodes.entrySet())
        {
            Set<String> s = group.getOrDefault(uf.getParent(n.getValue()), new HashSet<>());
            s.add(n.getKey());
            group.put(uf.getParent(n.getValue()), s);
        }

        for(Set<String> s: group.values())
        {
            List<String> l = new ArrayList<>(s);
            Collections.sort(l);
            l.add(0, accounts.get(accounts.size()-1).get(0));
            ret.add(l);
        }

        return ret;
    }













    public List<List<String>> accountsMergeApril6_21(List<List<String>> accounts) {
        List<List<String>> ret = new ArrayList<>();
        // Collections.sort(accounts, (a,b)->(a.get(0).compareTo(b.get(0))));

        Map<Integer, String> idToEmail = new HashMap<>();
        Map<String, Integer> emailToId = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        Set<String> emailSet = new HashSet<>();
        int id = 0;
        for(int i = 0; i < accounts.size(); ++i)
        {
            String account = accounts.get(i).get(0);
            for(int j = 1; j < accounts.get(i).size(); ++j)
            {
                String email = accounts.get(i).get(j);
                if (!emailSet.contains(email))
                {
                    emailSet.add(email);
                    emailToName.put(email, account);
                    idToEmail.put(id, email);
                    emailToId.put(email, id);
                    ++id;
                }
            }
        }

        UnionFind uf = new UnionFind(emailSet.size());
        for(int i = 0; i < accounts.size(); ++i)
        {
            String email0 = accounts.get(i).get(1);
            int index0 = emailToId.get(email0);
            for(int j = 2; j < accounts.get(i).size(); ++j)
            {
                String email = accounts.get(i).get(j);
                uf.connect(index0, emailToId.get(email));
            }
        }

        Map<Integer, List<String>> idToList = new HashMap<>();
        for(Integer idx: emailToId.values())
        {
            int parent = uf.getParent(idx);
            idToList.putIfAbsent(parent, new ArrayList<>());
            idToList.get(parent).add(idToEmail.get(idx));
        }

        for(List<String> list: idToList.values())
        {
            Collections.sort(list);
            list.add(0, emailToName.get(list.get(0)));
            ret.add(list);
        }
        return ret;
    }









    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.accountsMergeMarch8_21(Arrays.asList(
//                Arrays.asList("David","David0@m.co","David4@m.co","David3@m.co"),
//                Arrays.asList("David","David5@m.co","David5@m.co","David0@m.co"),
////                Arrays.asList("David","David5@m.co","David0@m.co"),
//                Arrays.asList("David","David1@m.co","David4@m.co","David0@m.co"),
//                Arrays.asList("David","David0@m.co","David1@m.co","David3@m.co"),
//                Arrays.asList("David","David4@m.co","David1@m.co","David3@m.co")
//        )));
        System.out.println(s.accountsMergeApril6_21(Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("Mary", "mary@mail.com")
        )));
    }
}
