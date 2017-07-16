package com.examples.Test;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class CustomHashTable {

    static class Node
    {
        String _key;
        int _val;
        public Node(String key, int val)
        {
            this._key = key;
            this._val = val;
        }
    }

    static class HashTable
    {
        private int _defaultTableSize = 3;
        private double _loadFactor = 0.8;
        private ArrayList<ArrayList<Node>> _data;
        private int _distinctKeyCounts;

        public HashTable()
        {
            this._data = new ArrayList<ArrayList<Node>>();
            for(int i = 0; i < this._defaultTableSize; ++i)
                this._data.add(new ArrayList<>());
            this._distinctKeyCounts = 0;
        }

        public Integer get(String key)
        {
            int index = Math.abs(key.hashCode()) % this._data.size();


            for(int i = 0; i < this._data.get(index).size(); ++i)
            {
                if (this._data.get(index).get(i)._key.equals(key))
                {
                    return this._data.get(index).get(i)._val;
                }
            }

            return null;
        }

        public void set(String key, int val)
        {
            int index = Math.abs(key.hashCode()) % this._data.size();

            int i;
            for(i = 0; i < this._data.get(index).size(); ++i)
            {
                if (this._data.get(index).get(i)._key.equals(key))
                {
                    break;
                }
            }
            if (i == this._data.get(index).size())
            {
                this._data.get(index).add(new Node(key, val));
                ++this._distinctKeyCounts;
                rebalaceData();
            }
            else
            {
                this._data.get(index).set(i, new Node(key, val));
            }
        }

        private void rebalaceData()
        {
            if (this._distinctKeyCounts >= this._data.size() * this._loadFactor)
            {

                ArrayList<ArrayList<Node>> newData = new ArrayList<ArrayList<Node>>();
                for(int i = 0; i < this._data.size() * 2; ++i)
                    newData.add(new ArrayList<>());

                for (int i = 0; i < this._data.size(); ++i)
                {
                    for(int j = 0; j < this._data.get(i).size(); ++j)
                    {
                        Node n = this._data.get(i).get(j);
                        int newArrayPos = Math.abs(n._key.hashCode()) % newData.size();
                        newData.get(newArrayPos).add(n);
                    }

                }
                this._data = newData;
            }

        }

    }
    public static void main(String[] args) {
        System.out.println("Hello World");

        HashTable hashtable = new HashTable();

        for(int i = 0; i < 26; ++i)
        {
            char c = (char)('a' + i);
            hashtable.set(new String(new char[]{c}), i);
        }

        for(int i = 0; i < 26; ++i)
        {
            char c = (char)('a' + i);
            System.out.println(hashtable.get(new String(new char[]{c})));
        }
    }
}
