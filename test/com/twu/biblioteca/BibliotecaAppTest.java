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
        BibliotecaApp.setUpLibrary(library, "bookListTest.txt", "movieListTest.txt");
    }

    @Before
    public void setUpUser(){
        BibliotecaApp.setUpUser(0);
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
        BibliotecaApp.printMenu(new String[]{menuTittle,"1. List of books","2. Return a book","3. Exit"});
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
        String[] menuItems = new String[]{"Menu   *Hint: Enter menu item number to select.*", "1. List of books", "2. List of movies", "3. Return a book","4. Personal detail", "5 .Exit"};
        // Then
        assertThat(BibliotecaApp.getMenuItems().length, is(menuItems.length));
        assertThat(BibliotecaApp.getMenuItems()[0], is(menuItems[0]));
        assertThat(BibliotecaApp.getMenuItems()[1], is(menuItems[1]));
        assertThat(BibliotecaApp.getMenuItems()[2], is(menuItems[2]));
        assertThat(BibliotecaApp.getMenuItems()[3], is(menuItems[3]));
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
                "|| 5        || In the Mood for Love   || Wong Kar-wai || 2000           || 7      ||\n" +
                "====================================================================================\n"), is(true));
        assertThat(repeat, is(true));
    }

    @Test
    public void shouldSaveAfterExit() {
        //given
        String testString = "5";
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
    public void shouldPrintCheckOutBookMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        //when
        BibliotecaApp.checkOutBook();
        // Then
        String checkoutMessage = "*******************************************\n" +
                             "*Please insert book id to checkout a book:*\n" +
                             "*******************************************";
        assertThat(testOut.toString().contains(checkoutMessage),is(true));
    }

    @Test
    public void shouldPrintCheckOutBookSuccessMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        //when
        BibliotecaApp.checkOutBook();
        // Then
        String successMessage = "Thank you! Enjoy the book.";
        assertThat(testOut.toString().contains(successMessage),is(true));
    }

    @Test
    public void shouldPrintCheckOutBookUnSuccessMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        BibliotecaApp.checkOutBook();
        //when
        provideInput(testString);
        BibliotecaApp.checkOutBook();
        // Then
        String unSuccessMessage = "Sorry, that book is not available";
        assertThat(testOut.toString().contains(unSuccessMessage),is(true));
    }

    @Test
    public void shouldPrintCheckOutMovieMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        //when
        BibliotecaApp.checkOutMovie();
        // Then
        String checkoutMessage = "*******************************************\n" +
                "*Please insert movie id to checkout a movie:*\n" +
                "*******************************************";
        assertThat(testOut.toString().contains(checkoutMessage),is(true));
    }

    @Test
    public void shouldPrintCheckOutMovieSuccessMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        //when
        BibliotecaApp.checkOutMovie();
        // Then
        String successMessage = "Thank you! Enjoy the movie.";
        assertThat(testOut.toString().contains(successMessage),is(true));
    }

    @Test
    public void shouldPrintCheckOutMovieUnSuccessMessage() {
        //given
        String testString = "2";
        provideInput(testString);
        BibliotecaApp.checkOutMovie();
        //when
        provideInput(testString);
        BibliotecaApp.checkOutMovie();
        // Then
        String unSuccessMessage = "Sorry, that movie is not available";
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
        BibliotecaApp.checkOutBook();
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
        BibliotecaApp.checkOutBook();
        //when
        provideInput(returnId);
        BibliotecaApp.returnBook();
        // Then
        String unSuccessMessage = "That is not a valid book to return.";
        assertThat(testOut.toString().contains(unSuccessMessage),is(true));
    }

    @Test
    public void shouldLogin(){
        //given
        String userName = "123-4567"+ "\r\n";
        String password = "abcd";
        String fileName = "testUserInfo.txt";
        String successMessage =  "Successfully login.";
        String unsuccessMessage =  "Please check your password and user name.";
        //when
        provideInput(userName + password);
        boolean success = BibliotecaApp.login(fileName);
        //then
        assertThat(success,is(true));
        assertThat(testOut.toString().contains(successMessage),is(true));
        assertThat(testOut.toString().contains(unsuccessMessage),is(false));
    }

    @Test
    public void shouldNotLogin(){
        //given
        String userName = "123-4567"+ "\r\n";
        String password = "abc";
        String fileName = "testUserInfo.txt";
        String successMessage =  "Successfully login.";
        String unsuccessMessage =  "Please check your password and user name.";
        //when
        provideInput(userName + password);
        boolean success = BibliotecaApp.login(fileName);
        //then
        assertThat(success,is(false));
        assertThat(testOut.toString().contains(successMessage),is(false));
        assertThat(testOut.toString().contains(unsuccessMessage),is(true));
    }

    @Test
    public void shouldPrintPersonalDetail(){
        //given
        String userName = "123-4567"+ "\r\n";
        String password = "abcd";
        String fileName = "testUserInfo.txt";
        String personalDetail = "*********************************\n" +
                "*Your personal detail:          *\n" +
                "*Name: Maoning                  *\n" +
                "*Email: maoning.thoughtworks.com*\n" +
                "*Phone number: 11111111111      *\n" +
                "*********************************";
        provideInput(userName + password );
        boolean success = BibliotecaApp.login(fileName);
        //when
        BibliotecaApp.showPersonalDetail();
        //then
        assertThat(testOut.toString().contains(personalDetail),is(true));
    }
}
