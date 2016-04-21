package com.googlePrep;

import java.util.Iterator;

/**
 * Created by Ke.Liu on 6/10/16.
 */
public class EvenIterator<E> implements Iterator<E> {
    private final Iterator<E> iter;
    private boolean isEven;
    EvenIterator(Iterator<E> iter) {
        this.iter = iter;
        isEven = false;
    }

    @Override
    public boolean hasNext() {
        if (iter.hasNext()) {
            if (!isEven) {
                isEven = true;
                iter.next();
            }
            return iter.hasNext();
        } else {
            return false;
        }
    }

    @Override
    public E next() {
        if (hasNext()) {
            isEven = false;
            return iter.next();
        } else {
            return null;
        }
    }
    //becareful that their hasnext and next order are not determined.
}
