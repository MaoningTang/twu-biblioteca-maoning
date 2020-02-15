package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private Library library;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @Before
    public void setUpLibrary(){
        library = Library.getInstance();
        ArrayList<IntellectualProperty> books = new ArrayList<IntellectualProperty>(Arrays.asList(new Book(1,"Water Margin","Naian Shi",1999,null),
                new Book(2,"The Journey to the West","Chengen Wu",1992,null),
                new Book(3,"A Dream of Red Mansions","Xueqin Cao",1990,null),
                new Book(4,"The Three Kingdoms Era","Guanzhong Luo",1997,null)));
        library.setBooks(books);
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void shouldPrintMenu() {
        //given
        BibliotecaApp.printMenu();
        String menu = "======================================================\n" +
                "|| Menu   *Hint: Enter menu item number to select.* ||\n" +
                "======================================================\n" +
                "|| 1. List of books                                 ||\n" +
                "======================================================\n" +
                "|| 2. Exit                                          ||\n" +
                "======================================================\n";
        // Then
        assertThat(testOut.toString().contains(menu), is(true));
    }

    @Test
    public void shouldNotPrintBookList() {
        //given

        String testString = "";
        String bookList = "Author";
        provideInput(testString);
        //when
        boolean error = BibliotecaApp.menuSelection();
        // Then
        assertThat(testOut.toString().contains(bookList), is(false));
        assertThat(error, is(true));
    }

    @Test
    public void shouldPrintErrorMessage() {
        //given
        String testString = "x";
        provideInput(testString);
        //when
        boolean error = BibliotecaApp.menuSelection();
        // Then
        assertThat(testOut.toString().contains("Please select a valid option!"), is(true));
        assertThat(error, is(true));
    }

    @Test
    public void shouldPrintBookListAfterSelectMenuItem1() {
        //given
        String testString = "1";
        //when
        provideInput(testString);
        BibliotecaApp.start();
        //then
        assertThat(testOut.toString().contains("===========================================================================\n" +
                "|| Book Id || Tittle                  || Author        || Year Published ||\n" +
                "===========================================================================\n" +
                "|| 1       || Water Margin            || Naian Shi     || 1999           ||\n" +
                "===========================================================================\n" +
                "|| 2       || The Journey to the West || Chengen Wu    || 1992           ||\n" +
                "===========================================================================\n" +
                "|| 3       || A Dream of Red Mansions || Xueqin Cao    || 1990           ||\n" +
                "===========================================================================\n" +
                "|| 4       || The Three Kingdoms Era  || Guanzhong Luo || 1997           ||\n" +
                "==========================================================================="), is(true));
    }

    @Test
    public void shouldExit() {
        //given
        String testString = "2";
        provideInput(testString);
        //when
        boolean stop = BibliotecaApp.menuSelection();
        // Then
        String exitMessage = "**************\n" +
                             "*Best wishes!*\n" +
                             "**************";
        assertThat(testOut.toString().contains(exitMessage),is(true));
        assertThat(stop, is(false));
    }

    @Test
    public void shouldPrintCheckOutMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        //when
        BibliotecaApp.checkOutBook();
        // Then
        String exitMessage = "*******************************************\n" +
                             "*Please insert book id to checkout a book:*\n" +
                             "*******************************************";
        assertThat(testOut.toString().contains(exitMessage),is(true));
    }

}
