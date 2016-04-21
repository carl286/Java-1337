package com.googlePrep;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Ke.Liu on 6/6/16.
 */
public class StringManipulate {
    //    第一题，给定一个string，比如abc，设计add/replace/delete，三个function都会传入一个index，并且同一个index不会被用两次。index还有个特殊的地方，就是它只会是原始数据的index，比如abc，那就只有012。 如果调用 "add(1, a), add(2,c)"会变成aabcc，也就是操作会发生在原始的index上。给的解决方案就是用一个map存<index, iterator>然后对iterator进行操作，写了一些伪代码，

    char [] tmp;
    StringManipulate(String s) {
        tmp = s.toCharArray();
    }
    //assume index are always valid
    void add(int index, int c) {

    }
    void replace(int index, int c) {

    }
    void delete(int index) {

    }
    public void main() {
        String test = "abc";
        StringManipulate s = new StringManipulate(test);
        s.add(1, 'a');
        s.add(2,'c');
    }
}
