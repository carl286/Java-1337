package com.l1337.l1352;


import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/product-of-the-last-k-numbers/discuss/510265/C%2B%2B-Prefix-Array
public class ProductOfNumbers {

    private List<Integer> _list;
    public ProductOfNumbers() {
        _list = new ArrayList<>();
    }

    public void add(int num) {
        _list.add(num);
    }

    public int getProduct(int k) {
        int ret = 1;
        for(int i = _list.size() - 1; k > 0 && ret != 0; --k,--i)
        {
            ret *= _list.get(i);
        }

        return ret;
    }

    public static void main(String [] args) {
        ProductOfNumbers s = new ProductOfNumbers();
        System.out.println("Hello World");
    }
}
