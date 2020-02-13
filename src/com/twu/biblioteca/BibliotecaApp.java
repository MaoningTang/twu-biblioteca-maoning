package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        start();
    }

    public static void start(){
        printWellcomeMessage();
        printBookList();
    }
    public static void printWellcomeMessage(){
        String[] welcomeMessages = new String[2];
        welcomeMessages[0] = "Welcome to Biblioteca.";
        welcomeMessages[1] = "Your one-stop-shop for great book titles in Bangalore!";
        System.out.println(MassagePrinter.printMessageWithBox(welcomeMessages));
        printBookList();
    }

    public static void printBookList(){
        Library library = Library.getInstance();
        String[][] booksToBePrinted = library.toBooksMatrix();
        String formattedList = MassagePrinter.getBoxFormatMessageForArrayList(booksToBePrinted);
        System.out.println(formattedList);
    }

    public static void printMenu(){
        System.out.println("ToDo");
    }





}
