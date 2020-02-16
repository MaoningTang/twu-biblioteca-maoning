package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    public static ArrayList<String[]> readFile(String fileName){
        ArrayList<String[]> lines = new ArrayList<String[]>();
        try {
            Scanner fileScanner = new Scanner(new FileInputStream(fileName));
            while(fileScanner.hasNextLine()) {
                String[] line = fileScanner.nextLine().split(",");
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

     public static void writeFile(String fileName, ArrayList<String[]> lines){
        try {
            FileWriter writer = new FileWriter("fileName", false);
            lines.forEach(line -> {
                try {
                    writer.write(String.join(",", line) + "/n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
