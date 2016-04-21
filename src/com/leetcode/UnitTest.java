package com.leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Ke.Liu on 5/21/16.
 */
public class UnitTest {
    @BeforeClass
    public static void testSetup() {
//        tester = new MyClass();
    }

    @AfterClass
    public static void testCleanup() {
        // Do your cleanup here like close URL connection , releasing resources etc
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionIsThrown() {
//        tester.divide(1000, 0);
    }

    @Test
    public void testMultiply() {
        assertEquals(3, 1 + 2);
    }
}
