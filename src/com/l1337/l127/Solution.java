package com.l1337.l127;


import java.util.*;

public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (set.isEmpty() || !set.contains(endWord))
            return 0;

        HashSet<String> usedSet = new HashSet<>();
        ArrayList<String> list = new ArrayList();
        list.add(beginWord);
        usedSet.add(beginWord);

        int level = 0;
        while(!list.isEmpty())
        {
            ArrayList<String> nextList = new ArrayList();

            for(String currentWord : list)
            {
                char [] wordsArray = currentWord.toCharArray();
                for(int i = 0; i < wordsArray.length; ++i)
                {
                    char cachedChar = wordsArray[i];
                    for (char n = 'a'; n <= 'z'; ++n)
                    {
                        wordsArray[i] = n;
                        if (n != cachedChar)
                        {
                            String nextWord = new String(wordsArray);
                            if (set.contains(nextWord))
                            {
                                if (endWord.equals(nextWord))
                                    return level + 2;
                                if (!usedSet.contains(nextWord))
                                {
                                    nextList.add(nextWord);
                                    usedSet.add(nextWord);
                                }
                            }
                        }
                    }
                    wordsArray[i] = cachedChar;
                }
            }

            list = nextList;
            ++level;
        }

        return 0;
    }

    private void helper(List<List<String>> ret, List<String> tmp, HashMap<String, Set<String>> map, String beginWord, String current)
    {
        if (current.equals(beginWord))
        {
            List<String> temp = new ArrayList<>(tmp);
            Collections.reverse(temp);
            ret.add(temp);
            return;
        }

        Set<String> parents = map.get(current);

        for (String s : parents)
        {
            tmp.add(s);
            helper(ret, tmp, map, beginWord, s);
            tmp.remove(tmp.size()-1);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ret = new ArrayList<>();

        HashSet<String> set = new HashSet<>(wordList);
        if (set.isEmpty() || !set.contains(endWord))
            return ret;

        HashSet<String> usedSet = new HashSet<>();
        HashSet<String> levelSet = new HashSet();
        levelSet.add(beginWord);
        //usedSet.add(beginWord);

        //next -> to all its parents
        HashMap<String, Set<String>> map = new HashMap<>();

        boolean foundEnding = false;
        while(!levelSet.isEmpty() && !foundEnding)
        {
            usedSet.addAll(levelSet);
            HashSet<String> nextLevelSet = new HashSet();

            for(String currentWord : levelSet)
            {
                //String parent = new String(currentWord);
                char [] wordsArray = currentWord.toCharArray();
                for(int i = 0; i < wordsArray.length; ++i)
                {
                    char cachedChar = wordsArray[i];
                    for (char n = 'a'; n <= 'z'; ++n)
                    {
                        wordsArray[i] = n;
                        if (n != cachedChar)
                        {
                            String nextWord = new String(wordsArray);
                            if (set.contains(nextWord))
                            {
                                if (!usedSet.contains(nextWord))
                                {
                                    nextLevelSet.add(nextWord);
                                    if (map.get(nextWord) != null)
                                    {
                                        map.get(nextWord).add(currentWord);
                                    }
                                    else
                                    {
                                        Set<String> ss = new HashSet<>();
                                        ss.add(currentWord);
                                        map.put(nextWord, ss);
                                    }
                                }

                                if (endWord.equals(nextWord))
                                {
                                    foundEnding = true;
                                }
                            }
                        }
                    }
                    wordsArray[i] = cachedChar;
                }
            }

            levelSet = nextLevelSet;
        }

        if (foundEnding)
        {
            List<String> tmp = new ArrayList<>();
            tmp.add(endWord);
            helper(ret, tmp, map, beginWord, endWord);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        //System.out.println(s.ladderLength(beginWord, endWord, wordList));
        System.out.println(s.findLadders(beginWord, endWord, wordList));
    }
}
