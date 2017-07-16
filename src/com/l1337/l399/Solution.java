package com.l1337.l399;


import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {

    static class Node {
        String a;
        String b;

        Node (String a, String b) {
            this.a = a;
            this.b = b;
        }

        public int hashCode() {
            String c = a + b;
            int hash = 7;
            for (int i = 0; i < c.length(); i++) {
                hash = hash*31 + c.charAt(i);
            }
            return hash;
        }

        public boolean equals(Object obj) {
            Node that = (Node) obj;
            return a.equals(that.a) && b.equals(that.b);
        }
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        //assign index might be easier
        Map<String, Integer> mapper = new HashMap<>();
        for (int i = 0; i < equations.length; ++i) {
            if (!mapper.containsKey(equations[i][0])) {
                mapper.put(equations[i][0], mapper.size());
            }
            if (!mapper.containsKey(equations[i][1])) {
                mapper.put(equations[i][1], mapper.size());
            }
        }

        Double [][] data = new Double [mapper.size()][mapper.size()];
        for (int i = 0; i < data.length; ++i)
            for (int j = 0; j < data.length; ++j)
                if (i != j)
                    data[i][j] = null;
                else
                    data[i][j] = 1.0;
        for (int i = 0; i < equations.length; ++i) {
            data[mapper.get(equations[i][0])][mapper.get(equations[i][1])] = values[i];
            data[mapper.get(equations[i][1])][mapper.get(equations[i][0])] = 1.0 / values[i];
        }

        //Floyd–Warshall algorithm
        for (int k = 0; k < data.length; ++k) {
            for (int i = 0; i < data.length; ++i)
                for (int j = 0; j < data.length; ++j)
                    if (data[i][j] == null && data[i][k] != null && data[k][j] != null)
                        data[i][j] = data[i][k] * data[k][j];
        }

        double [] ret = new double[queries.length];
        for (int i = 0; i < queries.length; ++i)
            if (mapper.get(queries[i][0]) == null || mapper.get(queries[i][1]) == null || data[mapper.get(queries[i][0])][mapper.get(queries[i][1])] == null)
                ret[i] = -1.0;
            else
                ret[i] = data[mapper.get(queries[i][0])][mapper.get(queries[i][1])];

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String[][] equations = {{"a", "b"}, {"c", "d"}};
        double[] values = {1.0, 1.0};
        String[][] queries = {{"a","c"},{"b","d"},{"b","a"},{"d","c"}};
        for (double b : s.calcEquation(equations, values, queries))
            System.out.println(b);
    }
}
