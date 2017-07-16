package com.instacart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Password {
    public List<String> readFileInList(String fileName)
    {

        List<String> lines = Collections.emptyList();
        try
        {
            // lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            lines = Files.readAllLines(Paths.get(fileName));
        }

        catch (IOException e)
        {
            // do something
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String [] args) throws IOException {
        Password pw = new Password();
        String filePath = "C:\\Users\\like\\1337\\Java-1337\\src\\com\\instacart\\password.txt";

        List<String> inputLists = pw.readFileInList(filePath);
//        for(int i = 0; i < inputLists.size(); ++i)
//            System.out.println(inputLists.get(i));

        System.out.println("Parsing");
        //parsing
        int i = 0;
        while (i < inputLists.size())
        {
            int index = Integer.valueOf(inputLists.get(i++));

            // System.out.println("index: " + index);

            String coordinates = inputLists.get(i++);
            coordinates = coordinates.substring(1, coordinates.length()-1);
            // System.out.println("coordinates: " + coordinates);
            String [] ss = coordinates.split(",\\s+");
            int x = Integer.valueOf(ss[0]);
            int y = Integer.valueOf(ss[1]);
            // System.out.println("x + y: " + (x+y));

            List<String> data = new ArrayList<>();
            while (i < inputLists.size() && inputLists.get(i).length() > 0)
            {
                data.add(inputLists.get(i++));
            }

//            System.out.println("data");
//            for(int k = 0; k < data.size(); ++k)
//            {
//                System.out.println(data.get(k));
//            }

            System.out.println("pass: " + data.get(data.size() - y - 1).charAt(x));

            //skip the empty line
            if (i < inputLists.size()) inputLists.get(i++);
        }
    }


}
