package com.examples.Test;


public class Test {
    public static void main(String [] args) {
        String s1 = new String("sdfsdf sdf\n" +
                "sdfsdfsdf\n" +
                "chimera_frontend.authn_enabled\n" +
                "sdfsdfsdf");
        String s2 = new String("chimera_frontend.authn_enabled");
        System.out.println("Hello World");
        System.out.println(s1.contains(s2));
    }
}
