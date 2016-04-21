package com.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Ke.Liu on 7/9/16.
 */
public class FileReading2 {

//    If you want to read a binary file, or a text file containing 'weird' characters (ones that your system doesn't deal with by default), you need to use FileInputStream instead of FileReader. Instead of wrapping FileInputStream in a buffer, FileInputStream defines a method called read() that lets you fill a buffer with data, automatically reading just enough bytes to fill the buffer (or less if there aren't that many bytes left to read).
//    https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html

    public static void main(String [] args) {

        // The name of the file to open.
        String fileName = "/Users/Ke.Liu/Downloads/Java-1337/src/com/examples/Test/temp.txt";

        try {
            // Use this for reading the data.
            byte[] buffer = new byte[1000];

            FileInputStream inputStream =
                    new FileInputStream(fileName);

            // read fills buffer with data and returns
            // the number of bytes read (which of course
            // may be less than the buffer size, but
            // it will never be more).
            int total = 0;
            int nRead = 0;
            while((nRead = inputStream.read(buffer)) != -1) {
                // Convert to String so we can display it.
                // Of course you wouldn't want to do this with
                // a 'real' binary file.
                System.out.println(new String(buffer));
                total += nRead;
            }

            // Always close files.
            inputStream.close();

            System.out.println("Read " + total + " bytes");
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

}
