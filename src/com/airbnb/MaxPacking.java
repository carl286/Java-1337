package com.airbnb;

import javafx.util.Pair;

import java.util.*;

public class MaxPacking {

    /**
     Q: You are given tiles with random letters in an N by N grid and a dictionary
     We want to find the maximum number of words that can be packed on the board from the given dictionary.

     * A word is considered to be able to be packed on the board if
     * It can be found in the dictionary
     * It can be constructed from letters *untaken* by other words found so far on board
     * The coordinates of the adjacent letters in the word on board are within Manhattan distance of 1.
     Manhattan distance of point (x1, y1) and (x2, y2) is defined as |x1-x2| + |y1-y2| (no diagnoal moves)
     * Each tile can be visited only once by any word

     char[][] board = {
     {'A', 'N', 'E'},
     {'G', 'S', 'G'},
     {'O', 'N', 'O'}
     };

     String[] dictionary = {
     "EGO", "EGOS", "EGONS", "SNO", "SONGS", "NC", "SNS", "P"
     };

     public static int getMaxPacking(char[][] board, String[] dictionary) {
     }
     */

    private List<String> ret;
        private int [][] directions = new int [][]{{+1,0}, {-1,0}, {0,+1}, {0,-1}};

        private void dfs(int i, int j, Set<Pair<Integer, Integer>> visited, Set<Pair<Integer, Integer>> started, char[][] board, String[] dictionary, StringBuilder sb, List<String> localResult)
        {
//            if (sb.length() == 0)
//            {
//                started.add(new Pair<>(i,j));
//            }
            //let caller mark it.

            visited.add(new Pair<>(i, j));
            sb.append(board[i][j]);
            boolean foundExactMacth = false, foundPrefix = false;
            for(int k = 0; k < dictionary.length; ++k)
            {
                if (dictionary[k].startsWith(sb.toString()))
                {
                    foundPrefix = true;
                }

                if (dictionary[k].equals(sb.toString()))
                {
                    foundExactMacth = true;
                }
            }
            if (foundExactMacth)
            {
                localResult.add(sb.toString());
                //update ret if necessary
                if (localResult.size() > this.ret.size())
                {
                    ret = new ArrayList<>(localResult);
                }

//                for(int m = 0; m < board.length; ++m)
//                {
//                    for(int n = 0; n < board[0].length; ++n)
//                    {
//                        if (!visited.contains(new Pair<Integer, Integer>(m,n)) && !started.contains(new Pair<Integer, Integer>(m,n)))
//                        {
//                            dfs(m,n, visited, started, board, dictionary, new StringBuilder(), localResult);
//                        }
//                    }
//                }
            }

            if (foundPrefix)
            {
                for(int k = 0; k < directions.length; ++k)
                {
                    int next_i = i + directions[k][0];
                    int next_j = j + directions[k][1];
                    if (next_i >= 0 && next_j >= 0 && next_i < board.length && next_j < board[0].length && !visited.contains(new Pair<Integer, Integer>(next_i, next_j)))
                    {
                        dfs(next_i, next_j, visited, started, board, dictionary, sb, localResult);
                    }
                }
            }

            for(int m = 0; m < board.length; ++m)
            {
                for(int n = 0; n < board[0].length; ++n)
                {
                    if (!visited.contains(new Pair<Integer, Integer>(m,n)) && !started.contains(new Pair<Integer, Integer>(m,n)))
                    {
                        dfs(m,n, visited, started, board, dictionary, new StringBuilder(), localResult);
                    }
                }
            }

            if (foundExactMacth)
            {
                localResult.remove(localResult.size()-1);
            }
            visited.remove(new Pair<Integer,Integer>(i, j));
            sb.deleteCharAt(sb.length() - 1);
        }

        int getHash(int i, int j, int N)
        {
            return i * N + j;
        }

    private void getMaxPackingHelper(int i, int j, List<List<Integer>> parsed, StringBuilder sb, List<Integer> tmp, Set<Integer> visited, char[][] board, String[] dictionary)
    {
        visited.add(getHash(i,j, board.length));
        tmp.add(getHash(i,j, board.length));
        sb.append(board[i][j]);
        boolean foundExactMacth = false, foundPrefix = false;
        for(int k = 0; k < dictionary.length; ++k)
        {
            if (dictionary[k].startsWith(sb.toString()))
            {
                foundPrefix = true;
            }

            if (dictionary[k].equals(sb.toString()))
            {
                foundExactMacth = true;
            }
        }
        if (foundExactMacth)
        {
            System.out.println("add: " + sb);
            for(int k = 0; k < tmp.size(); ++k)
            {
                System.out.print(tmp.get(k) + ": (" + tmp.get(k) / board.length + ",)(" + tmp.get(k) % board.length + ")\t**");
            }
            System.out.println();
            List<Integer> tmps = new ArrayList<>(tmp);
            Collections.sort(tmps);
            parsed.add(tmps);
        }
        if (foundPrefix)
        {
            for(int k = 0; k < directions.length; ++k)
            {
                int next_i = i + directions[k][0];
                int next_j = j + directions[k][1];
                if (next_i >= 0 && next_j >= 0 && next_i < board.length && next_j < board[0].length && !visited.contains(getHash(next_i, next_j, board.length)))
                {
                    getMaxPackingHelper(next_i, next_j, parsed, sb, tmp, visited, board, dictionary);
                }
            }
        }

        visited.remove(getHash(i,j, board.length));
        tmp.remove(tmp.size()-1);
        sb.deleteCharAt(sb.length()-1);
    }

    private boolean isConflict(List<Integer> a, List<Integer> b)
    {
        for(int i = 0, j = 0; i < a.size() && j < b.size();)
        {
            if (a.get(i) == b.get(j))
            {
                return true;
            }
            else if (a.get(i) < b.get(j))
                ++i;
            else
                ++j;
        }
        return false;
    }
        public List<String> getMaxPacking(char[][] board, String[] dictionary) {
//            this.ret = new ArrayList<>();
//            for(int i = 0; i < board.length; ++i)
//            {
//                for(int j = 0; j < board[i].length; ++j)
//                {
//                    Set<Pair<Integer, Integer>> started =new HashSet<>();
//                    //considering first started with (i,j)...
//                    dfs(i, j, new HashSet<>(), started, board, dictionary, new StringBuilder(), new ArrayList<>());
//                }
//            }
//
//            return ret;

            //found all list of coordinates that matches
            List<List<Integer>> parsed = new ArrayList<>();

            for(int i = 0; i < board.length; ++i)
            {
                for(int j = 0; j < board[i].length; ++j)
                {
                    //considering first started with (i,j)...
                    getMaxPackingHelper(i, j, parsed, new StringBuilder(), new ArrayList<>(), new HashSet<>(), board, dictionary);
                }
            }

            boolean [][] checks = new boolean[parsed.size()][parsed.size()];

            for(int i = 0; i < checks.length; ++i)
            {
                for(int j = i + 1; j < checks.length; ++j)
                {
                    if (isConflict(parsed.get(i), parsed.get(j)))
                    {
                        System.out.println("i :" + i + "\t j: " + j);
                        checks[i][j] = true;
                        checks[j][i] = true;
                    }
                }
            }

            List<Integer> indexes = new ArrayList<>();
            backPacks(checks, new ArrayList<>(), indexes, 0);
            System.out.println("finals:");
            for(int i = 0; i < indexes.size(); ++i)
            {
                System.out.println(indexes.get(i));
            }
            return null;
        }


        private void backPacks(boolean [][] checks, List<Integer> tmp, List<Integer> ret, int index)
        {
            if (index >= checks.length)
                return;

            //take it
            int i = 0;
            while(i < tmp.size() && !checks[tmp.get(i)][index])
            {
                ++i;
            }
            if (i >= tmp.size())
            {
                tmp.add(index);
                if (tmp.size() > ret.size())
                {
                    ret.clear();
                    ret.addAll(tmp);
                }
                backPacks(checks, tmp, ret, index + 1);
                tmp.remove(tmp.size()-1);
            }

            //not take it.
            backPacks(checks, tmp, ret, index + 1);
        }
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'N', 'P'},
                {'G', 'S', 'G'},
                {'O', 'N', 'S'}
        };

        String[] dictionary = {
                "GO", "EGOS", "EGONS", "SNO", "SONGS", "NC", "SNS", "P"
        };

        MaxPacking s = new MaxPacking();
        System.out.println(s.getMaxPacking(board, dictionary));
    }
}
