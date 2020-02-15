package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BibliotecaApp {

    private static AppUser user;
    private static Library library;

    public static void main(String[] args) {
        start();
    }

    public static void start(){
        printWellcomeMessage();
        Library libraryInstance = Library.getInstance();
        setUpLibrary(libraryInstance);
        setUpUser();
        boolean repeat = false;
        do {
            String menuTittle = "Menu   *Hint: Enter menu item number to select.*";
            printMenu(new String[]{menuTittle,"List of books","Return a book","Exit"});
            repeat = menuSelection();
        } while (repeat);
    }

    public static void setUpLibrary(Library libraryInstance) {
        library = libraryInstance;
        ArrayList<IntellectualProperty> books = new ArrayList<IntellectualProperty>(Arrays.asList(new Book(1,"Water Margin","Naian Shi",1999,null),
                new Book(2,"The Journey to the West","Chengen Wu",1992,null),
                new Book(3,"A Dream of Red Mansions","Xueqin Cao",1990,null),
                new Book(4,"The Three Kingdoms Era","Guanzhong Luo",1997,null)));
        library.setBooks(books);
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
                toReturnBookList();
                break;
            case 3:
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
