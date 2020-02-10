package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        printWellcomeMessage();
    }

    public static void printWellcomeMessage(){
        String[] welcomeMessages = new String[2];
        welcomeMessages[0] = "Welcome to Biblioteca.";
        welcomeMessages[1] = "Your one-stop-shop for great book titles in Bangalore!";
        System.out.println(MassagePrinter.printMessageWithBox(welcomeMessages));
    }
}
