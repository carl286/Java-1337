package com.l1337.l839;


public class Solution {

    public boolean isSimilar(String a, String b)
    {
        int indexa = -1, indexb = -1;
        if (a.length() != b.length())
            return false;
        for(int i = 0; i < a.length(); ++i)
        {
            if(a.charAt(i) != b.charAt(i))
            {
                if (indexa < 0)
                    indexa = i;
                else if (indexb < 0)
                {
                    indexb = i;
                    if (!(a.charAt(indexa) == b.charAt(indexb) && a.charAt(indexb) == b.charAt(indexa)))
                    {
                        return false;
                    }
                }
                else
                    return false;
            }
        }
        if (indexa >= 0 && indexb < 0)
            return false;
        return true;
    }

    static class UnionFind
    {
        //distinct >= 0;
        private int distinct;
        private int data[];
        public UnionFind(int n)
        {
            this.distinct = n;
            this.data = new int [n];
            for(int i = 0; i < data.length; ++i)
                data[i] = i;
        }

        public int getParent(int x)
        {
            while(data[x] != data[data[x]])
            {
                x = data[x] = data[data[x]];
            }

            return data[x];
        }

        public void union(int x, int y)
        {
            int parentX = getParent(x);
            int parentY = getParent(y);
            if (parentX != parentY)
            {
                data[parentX] = data[parentY];
                --this.distinct;
            }
        }

        public int getDistinct()
        {
            return this.distinct;
        }
    }
    public int numSimilarGroups(String[] strs) {
        UnionFind uf = new UnionFind(strs.length);
        for(int i = 0; i < strs.length; ++i)
        {
            for(int j = i + 1; j < strs.length; ++j)
            {
                if (isSimilar(strs[i], strs[j]))
                    uf.union(i,j);
            }
        }

        return uf.getDistinct();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        String [] strs = new String [] {"tars","rats","arts","star"};
        String [] strs = new String [] {"omv","ovm"};

        System.out.println(s.numSimilarGroups(strs));
    }
}
