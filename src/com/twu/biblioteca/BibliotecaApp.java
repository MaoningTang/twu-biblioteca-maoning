package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BibliotecaApp {

    private static AppUser user;
    private static Library library;
    private static String[] menuItems;

    public static void main(String[] args) {
        start();
    }

    public static String[] getMenuItems() {
        return menuItems;
    }

    public static void start(){
        printWellcomeMessage();
        Library libraryInstance = Library.getInstance();
        setUpLibrary(libraryInstance);
        setUpUser();
        setUpMenuItems();
        boolean repeat = false;
        do {
            printMenu(menuItems);
            repeat = menuSelection();
        } while (repeat);
    }

    public static void setUpMenuItems(){
        String menuTittle = "Menu   *Hint: Enter menu item number to select.*";
        menuItems = new String[]{menuTittle,"List of books","List of movies","Return a book","Exit"};
    }

    public static void setUpLibrary(Library libraryInstance) {
        library = libraryInstance;
        ArrayList<IntellectualProperty> books = new ArrayList<IntellectualProperty>(Arrays.asList(new Book(1,"Water Margin","Naian Shi",1999,null),
                new Book(2,"The Journey to the West","Chengen Wu",1992,null),
                new Book(3,"A Dream of Red Mansions","Xueqin Cao",1990,null),
                new Book(4,"The Three Kingdoms Era","Guanzhong Luo",1997,null)));
        ArrayList<IntellectualProperty> movies = new ArrayList<IntellectualProperty>(Arrays.asList(new Movie(1,"Spring in a Small Town","Fei Mu",1948,null,9),
                new Movie(2,"Farewell My Concubine","Chen Kaige",1993,null,9),
                new Movie(3,"A Better Tomorrow","John Woo",1986,null,8),
                new Movie(4,"Days of Being Wild","Wong Kar-wai",1990,null,8),
                new Movie(4,"In the Mood for Love","Wong Kar-wai",2000,null,7)));
        library.setBooks(books);
        library.setMovies(movies);
    }

    public static void  setUpUser() {
        user = new Customer(1);
    }

    public static void printWellcomeMessage(){
        String[] welcomeMessages = new String[2];
        welcomeMessages[0] = "Welcome to Biblioteca.";
        welcomeMessages[1] = "Your one-stop-shop for great book titles in Bangalore!";
        System.out.println(MassagePrinter.printMessageWithBox(welcomeMessages));
    }

    public static void checkOutInBookList(){
        String[][] booksToBePrinted = library.toBooksMatrix();
        if (booksToBePrinted != null){
            String formattedList = MassagePrinter.getBoxFormatMessageForArrayList(booksToBePrinted);
            System.out.println(formattedList);
            checkOutBook();
        }
    }

    public static void checkOutInMovieList(){
        String[][] moviesToBePrinted = library.toMoviesMatrix();
        if (moviesToBePrinted != null){
            String formattedList = MassagePrinter.getBoxFormatMessageForArrayList(moviesToBePrinted);
            System.out.println(formattedList);
        }
    }

    public static void printMenu(String[] menuItems){
        for (int i = 1; i < menuItems.length; i++){
            menuItems[i] = String.valueOf(i) + ". " + menuItems[i];
        }
        String[][] menuItemMatrix = Arrays.stream(menuItems).map(item -> {return new String[]{item};}).toArray(String[][]::new);
        String formattedList = MassagePrinter.getBoxFormatMessageForArrayList(menuItemMatrix);
        System.out.println(formattedList);
    }

    public static boolean menuSelection(){
        Scanner scanner = new Scanner(System.in);
        int choice =  getInt(scanner);
        boolean repeat = true;
        switch (choice) {
            case 1:
                checkOutInBookList();
                break;
            case 2:
                checkOutInMovieList();
                break;
            case 3:
                toReturnBookList();
                break;
            case 4:
                printExitMessage();
                repeat = false;
                break;
            default:
                System.out.println("Please select a valid option!");
                break;
        }
        return repeat;
    }

     public static void checkOutBook(){
        String[] messages = new String[1];
        messages[0] = "Please insert book id to checkout a book:";
        System.out.println(MassagePrinter.printMessageWithBox(messages));
        Scanner scanner = new Scanner(System.in);
        long input = getLong(scanner);
        boolean success = library.checkOutBook(input,(Customer) user);
        if (success){
            messages[0] = "Thank you! Enjoy the book.";
            System.out.println(MassagePrinter.printMessageWithBox(messages));
        }else{
            messages[0] = "Sorry, that book is not available";
            System.out.println(MassagePrinter.printMessageWithBox(messages));
        }
    }

    private static void printExitMessage() {
        String[] welcomeMessages = new String[1];
        welcomeMessages[0] = "Best wishes!";
        System.out.println(MassagePrinter.printMessageWithBox(welcomeMessages));
    }

    private static int getInt(Scanner scanner) {
        int value = 0;
        try {
            value = scanner.nextInt();
        }
        catch (Exception e) {

        }
        return value;
    }

    private static long getLong(Scanner scanner) {
        long value = 0;
        try {
            value = scanner.nextLong();
        }
        catch (Exception e) {

        }
        return value;
    }

    public static void toReturnBookList() {
        String[][] booksToBePrinted = library.getCheckOutedBooks((Customer) user);
        if (booksToBePrinted != null){
            String formattedList = MassagePrinter.getBoxFormatMessageForArrayList(booksToBePrinted);
            System.out.println(formattedList);
            returnBook();
        }
    }

    public static void returnBook(){
        String[] messages = new String[1];
        messages[0] = "Please insert book id to return a book:";
        System.out.println(MassagePrinter.printMessageWithBox(messages));
        Scanner scanner = new Scanner(System.in);
        long input = getLong(scanner);
        boolean success = library.returnBook(input,(Customer) user);
        if (success){
            messages[0] = "Thank you for returning the book.";
            System.out.println(MassagePrinter.printMessageWithBox(messages));
        }else {
            messages[0] = "That is not a valid book to return.";
            System.out.println(MassagePrinter.printMessageWithBox(messages));
        }
    }
}
