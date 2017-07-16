package com.l1337.l380;

import java.util.*;

public class RandomizedSet2 {
    private List<Integer> _list;
    private Map<Integer, Integer> _valToIdx;
    private Random _rand;
    /** Initialize your data structure here. */
    public RandomizedSet2() {
        this._valToIdx = new HashMap<>();
        this._list = new ArrayList<>();
        this._rand = new Random();

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (this._valToIdx.containsKey(val))
            return false;
        if (this._list.size() > this._valToIdx.size())
        {
            this._list.set(this._valToIdx.size(), val);
        }
        else
        {
            this._list.add(val);
        }
        this._valToIdx.put(val, this._valToIdx.size());

        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!this._valToIdx.containsKey(val))
            return false;

        if (this._valToIdx.size() > 1 && this._valToIdx.get(val) != this._valToIdx.size() - 1)
        {
            this._list.set(this._valToIdx.get(val), this._list.get(this._valToIdx.size() - 1));
            this._valToIdx.put(this._list.get(this._valToIdx.size() - 1), this._valToIdx.get(val));
        }

        this._valToIdx.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return this._list.get(this._rand.nextInt(this._valToIdx.size()));
    }

    public static void main(String [] args)
    {
        RandomizedSet2 randomizedSet2 = new RandomizedSet2();
        System.out.println(randomizedSet2.insert(0));
        System.out.println(randomizedSet2.insert(1));
        System.out.println(randomizedSet2.remove(0));
        System.out.println(randomizedSet2.insert(2));
        System.out.println(randomizedSet2.remove(1));
        System.out.println(randomizedSet2.getRandom());

    }
}
