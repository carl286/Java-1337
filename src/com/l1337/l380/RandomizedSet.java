package com.l1337.l380;

//https://leetcode.com/problems/insert-delete-getrandom-o1/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class RandomizedSet {

    private Random randomGenerator;
    private HashMap<Integer, Integer> map;
    private ArrayList<Integer> l;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        randomGenerator = new Random();
        map = new HashMap<>();
        l = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;

        l.add(val);
        map.put(val, l.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;
        int index = map.get(val);
        //there should be at least one element here
        map.put(l.get(l.size()-1), index);
        map.remove(val);
        l.set(index, l.get(l.size()-1));
        l.remove(l.size()-1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return l.get(randomGenerator.nextInt(l.size()));
    }

    public static void main(String [] args) {
        // Init an empty set.
        RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
        System.out.println(randomSet.insert(1));

// Returns false as 2 does not exist in the set.
        System.out.println(randomSet.remove(2));

// Inserts 2 to the set, returns true. Set now contains [1,2].
        System.out.println(randomSet.insert(2));

// getRandom should return either 1 or 2 randomly.
        System.out.println(randomSet.getRandom());

// Removes 1 from the set, returns true. Set now contains [2].
        System.out.println(randomSet.remove(1));

// 2 was already in the set, so return false.
        System.out.println(randomSet.insert(2));

// Since 1 is the only number in the set, getRandom always return 1.
        System.out.println(randomSet.getRandom());
    }
}
