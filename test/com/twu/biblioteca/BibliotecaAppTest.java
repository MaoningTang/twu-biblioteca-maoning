package com.twu.biblioteca;

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
    private Library library;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @Before
    public void setUpLibrary(){
        library = Library.getInstance();
        BibliotecaApp.setUpLibrary(library);
    }

    @Before
    public void  setUpUser(){
        BibliotecaApp.setUpUser();
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
        String menuTittle = "Menu   *Hint: Enter menu item number to select.*";
        BibliotecaApp.printMenu(new String[]{menuTittle,"List of books","Return a book","Exit"});
        String menu = "======================================================\n" +
                "|| Menu   *Hint: Enter menu item number to select.* ||\n" +
                "======================================================\n" +
                "|| 1. List of books                                 ||\n" +
                "======================================================\n" +
                "|| 2. Return a book                                 ||\n" +
                "======================================================\n" +
                "|| 3. Exit                                          ||\n" +
                "======================================================";
        // Then
        assertThat(testOut.toString().contains(menu), is(true));
    }

    @Test
    public void shouldHaveCorrectMenuItems() {
        //given
        BibliotecaApp.setUpMenuItems();
        String menuTittle = "Menu   *Hint: Enter menu item number to select.*";
        String[] menuItems = new String[]{menuTittle,"List of books","List of movies","Return a book","Exit"};
        // Then
        assertThat(BibliotecaApp.getMenuItems(), is(menuItems));
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
        boolean repeat = BibliotecaApp.menuSelection();
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
        assertThat(repeat, is(true));
    }

    @Test
    public void shouldPrintMovieListAfterSelectMenuItem1() {
        //given
        String testString = "2";
        //when
        provideInput(testString);
        boolean repeat = BibliotecaApp.menuSelection();
        //then
        assertThat(testOut.toString().contains("====================================================================================\n" +
                "|| Movie Id || Name                   || Director     || Year Published || Rating ||\n" +
                "====================================================================================\n" +
                "|| 1        || Spring in a Small Town || Fei Mu       || 1948           || 9      ||\n" +
                "====================================================================================\n" +
                "|| 2        || Farewell My Concubine  || Chen Kaige   || 1993           || 9      ||\n" +
                "====================================================================================\n" +
                "|| 3        || A Better Tomorrow      || John Woo     || 1986           || 8      ||\n" +
                "====================================================================================\n" +
                "|| 4        || Days of Being Wild     || Wong Kar-wai || 1990           || 8      ||\n" +
                "====================================================================================\n" +
                "|| 4        || In the Mood for Love   || Wong Kar-wai || 2000           || 7      ||\n" +
                "====================================================================================\n"), is(true));
        assertThat(repeat, is(true));
    }

    @Test
    public void shouldExit() {
        //given
        String testString = "4";
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
        BibliotecaApp.checkOut();
        // Then
        String checkoutMessage = "*******************************************\n" +
                             "*Please insert book id to checkout a book:*\n" +
                             "*******************************************";
        assertThat(testOut.toString().contains(checkoutMessage),is(true));
    }

    @Test
    public void shouldPrintCheckOutSuccessMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        //when
        BibliotecaApp.checkOut();
        // Then
        String successMessage = "Thank you! Enjoy the book.";
        assertThat(testOut.toString().contains(successMessage),is(true));
    }

    @Test
    public void shouldPrintCheckOutUnSuccessMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        BibliotecaApp.checkOut();
        //when
        provideInput(testString);
        BibliotecaApp.checkOut();
        // Then
        String unSuccessMessage = "Sorry, that book is not available";
        assertThat(testOut.toString().contains(unSuccessMessage),is(true));
    }


    @Test
    public void shouldNotPrintCheckedBookInReturnList() {
        //given
        String bookList = "|| Book Id || Tittle                  || Author        || Year Published ||";
        //when
        BibliotecaApp.toReturnBookList();
        //then
        assertThat(testOut.toString().contains(bookList),is(false));
    }

    @Test
    public void shouldPrintReturnSuccessMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        BibliotecaApp.checkOut();
        //when
        provideInput(testString);
        BibliotecaApp.returnBook();
        // Then
        String successMessage = "Thank you for returning the book";
        assertThat(testOut.toString().contains(successMessage),is(true));
    }

    @Test
    public void shouldPrintUnSuccessReturnMessage() {
        //given
        String checkoutId = "2";
        String returnId = "3";
        provideInput(checkoutId);
        BibliotecaApp.checkOut();
        //when
        provideInput(returnId);
        BibliotecaApp.returnBook();
        // Then
        String unSuccessMessage = "That is not a valid book to return.";
        assertThat(testOut.toString().contains(unSuccessMessage),is(true));
    }
}
