package com.l1337.l1146;


import java.util.*;

public class SnapshotArray {
    private Map<Integer, TreeMap<Integer, Integer>> data = new HashMap<>();
    private int current_snap_id = 0;

    public SnapshotArray(int length) {

    }

    public void set(int index, int val) {
        if (data.get(index) == null)
            data.put(index, new TreeMap<>());
        data.get(index).put(current_snap_id, val);
    }

    public int snap() {
        ++current_snap_id;
        return current_snap_id - 1;
    }

    public int get(int index, int snap_id) {
        // return data.get(map.get(snap_id))[index];
        if (data.get(index) == null)
            return 0;
        Map.Entry<Integer, Integer> entry = data.get(index).floorEntry(snap_id);
        if (entry != null)
            return entry.getValue();
        else
            return 0;
    }

    public static void main(String [] args) {
//        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
//        snapshotArr.set(0,5);  // Set array[0] = 5
//        System.out.println(snapshotArr.snap());  // Take a snapshot, return snap_id = 0
//        snapshotArr.set(0,6);
//        System.out.println(snapshotArr.get(0,0));  // Get the value of array[0] with snap_id = 0, return 5

        SnapshotArray snapshotArr = new SnapshotArray(2); // set the length to be 3
        System.out.println(snapshotArr.snap());
    }
}
