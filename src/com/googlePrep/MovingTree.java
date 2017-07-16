package com.googlePrep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MovingTree {
//    http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=440666&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
    //貌似是高频面经题，一个数组表示树，每个元素对应一个节点，数组元素的值表示该节点的父节点的索引。然后问给你一个节点的索引，删除该节点及其子树，然后还要把剩余数组元素挪到一起不留空隙。先写brute force。然后讨论优化的解，因为没准备过这题，给了解法但非最优，面试官百般提示，最后找到最优，没时间了代码没写完
    void moveTree(int [] data, int remove) {
        //assum to be -1
        //build map from parent to child
        int root = -1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < data.length; ++i) {
            if (data[i] >= 0) {
                //-1 reprents root node
                List<Integer> list = map.getOrDefault(data[i], new ArrayList<>());
                list.add(i);
                map.put(data[i], list);
            } else {
                root = i;
            }
        }

        List<Integer> removedIndex = new ArrayList<>();
        LinkedList<Integer> q = new LinkedList<>();
        q.add(remove);

        while (!q.isEmpty()) {
            Integer cur = q.removeFirst();
            q.addAll(map.getOrDefault(cur, new ArrayList<>()));
            removedIndex.add(cur);
            map.remove(cur);
            data[cur] = -100;
        }

        int remaining_size = data.length - removedIndex.size();
        //all valid index 0<=x<data.length
        if (remaining_size > 0) {
//            Collections.sort(removedIndex); //left cotains ava sport
            int i = 0, j = data.length - 1;
            while (i < removedIndex.size()) {
                if (removedIndex.get(i) < remaining_size) {
                    //we need fill it.
                    while (j >= remaining_size && data[j] == -100)
                        --j;

                    //j should always >= remaining_size here.
                    data[removedIndex.get(i)] = data[j];
                    data[j] = -100;
                    List<Integer> children = map.getOrDefault(j, new ArrayList<>());
                    for (int k = 0; k < children.size(); ++k)
                        if (data[children.get(k)] != -100)
                            data[children.get(k)] = removedIndex.get(i);
                    map.remove(j);
                    map.put(removedIndex.get(i), children);
                    --j;
                }
                ++i;
            }
        }
    }


    public static void main(String [] args) {
        MovingTree movetree = new MovingTree();
//        int [] data = new int [] {6,2,3,-1,6,2,3};
//        int remove = 2;
//        int [] data = new int [] {6, -1, 7, 7, 8, 8, 1,1,1};
//        int remove = 7;
        int [] data = new int [] {4,4,5,5,6,6,-1};
        int remove = 5;
        movetree.moveTree(data, remove);
        for(int i = 0; i < data.length; ++i)
            System.out.println(data[i]);
    }
}
