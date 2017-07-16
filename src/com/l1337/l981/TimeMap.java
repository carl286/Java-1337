package com.l1337.l981;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeMap {

    /** Initialize your data structure here. */
    private Map<String, TreeMap<Integer, String>> map;
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> local = map.get(key);
        if (local == null)
            return null;
        Map.Entry<Integer, String> entry = local.floorEntry(timestamp);
        if (entry == null)
            return "";
        return entry.getValue();
    }

    public static void main(String [] args) {
        TimeMap s = new TimeMap();
        s.set("love", "high", 10);
        s.set("love", "low", 20);
        s.get("love", 5);
        s.get("love", 10);
        System.out.println("Hello World");
    }
}
