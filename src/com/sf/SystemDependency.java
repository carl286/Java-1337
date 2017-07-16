package com.sf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemDependency {

    public static void main(String [] args) {

        BufferedReader reader = null;
        CommandHandler commandHandler = new CommandHandler();
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = reader.readLine();
                String [] splitInputs = input.split("\\s+");
                commandHandler.handleCommand(splitInputs);
                if (commandHandler.isStoped())
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
