package com.l1337.l1357;


import java.util.HashMap;
import java.util.Map;

public class Cashier {

    private int acc = 0;
    private int n;
    private double discount;
    private Map<Integer, Integer> productPrices;
    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.acc = 0;
        this.n = n;
        this.discount = (double) (100 - discount) / 100;
        this.productPrices = new HashMap<>();
        for (int i = 0; i < products.length; ++i)
            this.productPrices.put(products[i], prices[i]);
    }

    public double getBill(int[] product, int[] amount) {
        ++this.acc;
        this.acc %= this.n;
        double total = 0;
        for (int i = 0; i < product.length; ++i)
        {
            total += productPrices.get(product[i]) * amount[i];
        }

        if (this.acc == 0)
            total = this.discount * total;
        return total;
    }

    public static void main(String [] args) {
        System.out.println("Hello World");
    }
}
