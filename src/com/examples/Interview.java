package com.examples;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

public class Interview {

//	Write a function to reverse a string.
	public static String reverse ( String s ) {
//		StringBuilder sb = new StringBuilder(s);
//		int length = s.length();
//		for (int i = 0, j = length - 1; i < j; i++, j--) {
//			char tmp = sb.charAt(i);
//			sb.setCharAt(i, sb.charAt(j));
//			sb.setCharAt(j, tmp);
//		}
//			
//		return sb.toString();
		
		//Suggested Answer
		int length = s.length(), last = length - 1;
        char[] chars = s.toCharArray();
        for ( int i = 0; i < length/2; i++ ) {
            char c = chars[i];
            chars[i] = chars[last - i];
            chars[last - i] = c;
        }
        return new String(chars);
	}
	
	@Test
	public void testRevese() {
		String s = "Madam, I'm Adam";
		String s2 = "madA m'I ,madaM";
		assertEquals(reverse(s), s2);
	}
	
	public static void multTables ( int max )
    {
        for ( int i = 1; i <= max; i++ ) {
            for ( int j = 1; j <= max; j++ ) {
                System.out.print ( String.format ( "%4d", j * i ));
            }
            System.out.println();
        }
    }
	
	//Write a function that sums up integers from a text file, one int per line.
	public static void sumFile ( String name ) {
        try {
            int total = 0;
            BufferedReader in = new BufferedReader ( new FileReader ( name ));
            for ( String s = in.readLine(); s != null; s = in.readLine() ) {
                total += Integer.parseInt ( s );
            }
            System.out.println ( total );
            in.close();
        }
        catch ( Exception xc ) {
            xc.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
