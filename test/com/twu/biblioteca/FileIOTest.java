package com.twu.biblioteca;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileIOTest {

    private static String fileName = "bookListTest.txt";
    @AfterClass
    public static void resetTestFile(){
        ArrayList<String[]> listToWtite = new ArrayList<String[]>(Arrays.asList(
                new String[]{"1", "Water Margin", "Naian Shi", "1999", "null"},
                new String[]{"2", "The Journey to the West", "Chengen Wu", "1992", "null"},
                new String[]{"3", "A Dream of Red Mansions", "Xueqin Cao", "1990", "null"},
                new String[]{"4", "The Three Kingdoms Era", "Guanzhong Luo", "1997", "null"}
        )
        );
        FileIO.writeFile(fileName,listToWtite);
    }

    @Test
    public void ShouldGetFile() {
        //given
        String fileName = "bookListTest.txt";
        ArrayList<String[]> expectedArrayList = new ArrayList<String[]>(Arrays.asList(
                new String[]{"1", "Water Margin", "Naian Shi", "1999", "null"},
                new String[]{"2", "The Journey to the West","Chengen Wu", "1992", "null"},
                new String[]{"3", "A Dream of Red Mansions", "Xueqin Cao", "1990", "null"},
                new String[]{"4", "The Three Kingdoms Era", "Guanzhong Luo", "1997", "null"}
        )
        );
        //when
        ArrayList<String[]> bookArrayList = FileIO.readFile(fileName);
        //then
        assertThat(bookArrayList.size(), is(4));
        assertThat(bookArrayList.get(0),is(expectedArrayList.get(0)));
        assertThat(bookArrayList.get(1),is(expectedArrayList.get(1)));
        assertThat(bookArrayList.get(2),is(expectedArrayList.get(2)));
        assertThat(bookArrayList.get(3),is(expectedArrayList.get(3)));
    }
    @Test
    public void ShouldWriteFile() {
        //given
        String fileName = "bookListTest.txt";
        ArrayList<String[]> listToWtite = new ArrayList<String[]>(Arrays.asList(
                new String[]{"1", "Water Margin", "Naian Shi", "1999", "1"},
                new String[]{"2", "The Journey to the West", "Chengen Wu", "1992", "null"},
                new String[]{"3", "A Dream of Red Mansions", "Xueqin Cao", "1990", "null"},
                new String[]{"4", "The Three Kingdoms Era", "Guanzhong Luo", "1997", "null"}
        )
        );
        String[] expectedLines = new String[]{
                "1,Water Margin,Naian Shi,1999,1",
                "2,The Journey to the West,Chengen Wu,1992,null",
                "3,A Dream of Red Mansions,Xueqin Cao,1990,null",
                "4,The Three Kingdoms Era,Guanzhong Luo,1997,null"};
        //when
        FileIO.writeFile(fileName,listToWtite);
        ArrayList<String> lines = readFileToArray(fileName);
        //then
        assertThat(lines.size(), is(4));
        assertThat(lines.get(0),is(expectedLines[0]));
        assertThat(lines.get(1),is(expectedLines[1]));
        assertThat(lines.get(2),is(expectedLines[2]));
        assertThat(lines.get(3),is(expectedLines[3]));
    }

    public static ArrayList<String> readFileToArray(String fileName){
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new FileInputStream(fileName));
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

}
