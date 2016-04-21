package com.l1337.l381;

//https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedCollection {

    private Random randomGenerator;
    private HashMap<Integer, Integer> countMap;//val -> count
    private HashMap<String, Integer> indexMap;//key(val#count) -> array index
    private HashMap<Integer, String> reverseMap;//array index -> key(val#count), this could be replaced by a arraylist...
    private ArrayList<Integer> l;
    private final String DELIMITER = "#";

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        randomGenerator = new Random();
        l = new ArrayList<>();
        countMap = new HashMap<>();
        indexMap = new HashMap<>();
        reverseMap = new HashMap<>();
    }

    private String getKey(int val) {
        return Integer.toString(val) + DELIMITER + Integer.toString(countMap.get(val));
    }
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean ret = true;
        if (countMap.containsKey(val)) {
            ret = false;
            countMap.put(val, countMap.get(val)+1);
        } else {
            countMap.put(val, 1);
        }

        String key = getKey(val);
        l.add(val);
        indexMap.put(key, l.size()-1);
        reverseMap.put(l.size()-1, key);

        return ret;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!countMap.containsKey(val))
            return false;

        String key = getKey(val);
        String lastKey = reverseMap.get(l.size()-1);
        if (!key.equals(lastKey)) {
            l.set(indexMap.get(key), l.get(l.size()-1));
            reverseMap.put(indexMap.get(key), lastKey);
            indexMap.put(lastKey, indexMap.get(key));
        }
        indexMap.remove(key);
        reverseMap.remove(l.size()-1);
        if (countMap.get(val) == 1) {
            countMap.remove(val);
        } else {
            countMap.put(val, countMap.get(val)-1);
        }
        l.remove(l.size()-1);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return l.get(randomGenerator.nextInt(l.size()));
    }

    public static void main(String [] args) {
        RandomizedCollection s = new RandomizedCollection();
        s.insert(1);
        s.insert(1);
        s.insert(2);
        s.insert(2);
        s.insert(2);
        s.remove(1);
        s.remove(1);
        s.remove(2);
        s.insert(1);
        s.remove(2);

    }
}
