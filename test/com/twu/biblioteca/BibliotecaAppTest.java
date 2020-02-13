package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }


    @Test
    public void shouldPrintMenu() {
        //given
        BibliotecaApp.printMenu();
        String menu = "======================================================\n" +
                      "|| Menu   *Hint: Enter menu item number to select.* ||\n" +
                      "======================================================\n" +
                      "|| 1. List of books                                 ||\n" +
                      "======================================================\n";
        // Then
        assertThat(testOut.toString().contains(menu), is(true));
    }

    @Test
    public void shouldNotPrintBookList() {
        //given
        BibliotecaApp.printMenu();
        String bookList = "Author";
        //when
        BibliotecaApp.start();
        // Then
        assertThat(testOut.toString().contains(bookList), is(false));
    }

    @Test
    public void shouldPrintBookListAfterSelectMenuItem1() {
        //given
        BibliotecaApp.printMenu();
        String testString = "1";
        BibliotecaApp.start();
        //when
        provideInput(testString);
        //then
        assertThat(testOut.toString().contains("================================================================\n" +
                "|| Tittle                  || Author        || Year Published ||\n" +
                "================================================================\n" +
                "|| Water Margin            || Naian Shi     || 1999           ||\n" +
                "================================================================\n" +
                "|| The Journey to the West || Chengen Wu    || 1992           ||\n" +
                "================================================================\n" +
                "|| A Dream of Red Mansions || Xueqin Cao    || 1990           ||\n" +
                "================================================================\n" +
                "|| The Three Kingdoms Era  || Guanzhong Luo || 1997           ||\n" +
                "================================================================"), is(false));
    }

}
