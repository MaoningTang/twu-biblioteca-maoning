package com.twu.biblioteca;

import java.util.Arrays;

public class BibliotecaApp {

    public static void main(String[] args) {
        start();
    }

    public static void start(){
        printWellcomeMessage();
        printMenu();
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
        String menuTittle = "Menu   *Hint: Enter menu item number to select.*";
        String[] menuItems = new String[]{menuTittle,"List of books"};
        for (int i = 1; i < menuItems.length; i++){
            menuItems[i] = String.valueOf(i) + ". " + menuItems[i];
        }
        String[][] menuItemMatrix = Arrays.stream(menuItems).map(item -> {return new String[]{item};}).toArray(String[][]::new);
        String formattedList = MassagePrinter.getBoxFormatMessageForArrayList(menuItemMatrix);
        System.out.println(formattedList);
        System.out.println(formattedList);
    }

    public static void MenuLoop(){

    }


}
