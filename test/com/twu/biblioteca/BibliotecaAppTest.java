package com.twu.biblioteca;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {


    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;

    @Before
    public void redirectSystemOutStream() {

        originalSystemOut = System.out;

        // given
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
    }

    @After
    public void restoreSystemOutStream() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void shouldPrintMenu() {
        //given
        BibliotecaApp.printMenu();
        String menu = "=============================================================\n" +
                      "|| Menu *Hint: Enter menu item number to select.           ||\n" +
                      "=============================================================\n" +
                      "|| 1. List of books                                        ||\n" +
                      "=============================================================\n";
        // when
        System.out.println("example");
        assertThat(systemOutContent.toString(), Is.is(menu));
    }

}
