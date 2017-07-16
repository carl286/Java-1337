package com.l1337.l1361;


import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    // return true if and only if all the given nodes form exactly one valid binary tree. -> it's a tree and not a forest....
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] indegress = new int [n];
        for (int i = 0; i < leftChild.length; ++i)
        {
            if (leftChild[i] >= 0) {
//                if ( i == leftChild[i])
//                    return false;
                ++indegress[leftChild[i]];
            }

            if (rightChild[i] >= 0)
            {
//                if ( i == leftChild[i])
//                    return false;
                ++indegress[rightChild[i]];
            }
        }
        int root = -1;
        for (int i = 0; i < indegress.length; ++i)
        {
            if (indegress[i] == 0)
            {
                if (root < 0)
                    root = i;
                else
                    return false;
            }
            else if (indegress[i] != 1)
                return false;
        }

        if (root < 0)
            return false;
        //start from the root....
        Set<Integer> visited = new HashSet<>();
        ArrayDeque<Integer> toVisit = new ArrayDeque<>();
        toVisit.offer(root);
        while (toVisit.size() != 0)
        {
            Integer cur = toVisit.poll();
            visited.add(cur);

            if (leftChild[cur] >= 0)
                toVisit.add(leftChild[cur]);
            if (rightChild[cur] >= 0)
                toVisit.add(rightChild[cur]);
        }

        return visited.size() == n;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
