package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        setUpLibrary(libraryInstance, "bookList.txt", "movieList.txt");
        boolean loginSuccess =  login("userInfo.txt");
        if (loginSuccess){
            setUpMenuItems();
            boolean repeat = false;
            do {
                printMenu(menuItems);
                repeat = menuSelection();
            } while (repeat);
        }
    }

    public static void setUpMenuItems(){
        if (menuItems !=null){
            return;
        }
        String menuTitle = "Menu";
        menuItems = new String[]{menuTitle,"List of books","List of movies","Return a book","Personal detail","Exit"};
        for (int i = 1; i < menuItems.length; i++){
            menuItems[i] = String.valueOf(i) + ". " + menuItems[i];
        }
    }

    public static void setUpLibrary(Library libraryInstance, String bookListFileName, String movieListFileName) {
        library = libraryInstance;
        ArrayList<IntellectualProperty> books = FileIO.readFile(bookListFileName).stream().map(line -> {
            long id  = Long.valueOf(line[0]);
            String title = line[1];
            String author = line[2];
            int year = Integer.valueOf(line[3]);
            long userId = line[4].equals("null")?0:Long.valueOf(line[4]);
            Customer customer = userId == 0?null:new Customer(userId);
            return new Book(id, title, author, year,customer);
        }).collect(Collectors.toCollection(ArrayList<IntellectualProperty>::new));
        ArrayList<IntellectualProperty> movies = FileIO.readFile(movieListFileName).stream().map(line -> {
            long id  = Long.valueOf(line[0]);
            String title = line[1];
            String author = line[2];
            int year = Integer.valueOf(line[3]);
            long userId = line[4].equals("null")?0:Long.valueOf(line[4]);
            Customer customer = userId == 0?null:new Customer(userId);
            int rating = Integer.valueOf(line[5]);
            return new Movie(id, title, author, year,customer,rating);
        }).collect(Collectors.toCollection(ArrayList<IntellectualProperty>::new));
        library.setBooks(books);
        library.setMovies(movies);
    }

    public static void  setUpUser(long id ) {
        user = new Customer(id);
    }

    public static void printWellcomeMessage(){
        String welcomeMessages = "Welcome to Biblioteca.Your one-stop-shop for great book titles in Bangalore!";
        System.out.println(welcomeMessages);
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
            checkOutMovie();
        }
    }

    public static void printMenu(String[] menuItems){

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
                showPersonalDetail();
                break;
            case 5:
                saveChanges("movieList.txt","bookList.txt");
                printExitMessage();
                repeat = false;
                break;
            default:
                System.out.println("Please select a valid option!");
                break;
        }
        return repeat;
    }

    public static void showPersonalDetail() {
        String[] messages = new String[]{
            "Your personal detail:",
            "Name: " + user.name,
                "Email: " + user.email,
                "Phone number: " + user.phoneNumber
        };
        System.out.println(MassagePrinter.printMessageWithBox(messages));
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

    public static void checkOutMovie(){
        String[] messages = new String[1];
        messages[0] = "Please insert movie id to checkout a movie:";
        System.out.println(MassagePrinter.printMessageWithBox(messages));
        Scanner scanner = new Scanner(System.in);
        long input = getLong(scanner);
        boolean success = library.checkOutMovie(input,(Customer) user);
        if (success){
            messages[0] = "Thank you! Enjoy the movie.";
            System.out.println(MassagePrinter.printMessageWithBox(messages));
        }else{
            messages[0] = "Sorry, that movie is not available";
            System.out.println(MassagePrinter.printMessageWithBox(messages));
        }
    }

    private static void saveChanges(String movieFileName,String bookFileName){
        FileIO.writeFile(bookFileName,library.getBooks().stream().map(
                book -> book.getFullDetailArray()).collect(Collectors.toCollection(ArrayList<String[]>::new)));
        FileIO.writeFile(movieFileName,library.getMovies().stream().map(
                movie -> movie.getFullDetailArray()).collect(Collectors.toCollection(ArrayList<String[]>::new)));
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

    public static boolean login(String fileName) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String[]> users = FileIO.readFile(fileName);
        System.out.println("Enter user name:");
        String userName = scanner.nextLine();
        System.out.println("Enter password:");
        String passWord = scanner.nextLine();

        Optional<String[]> matchedUser = users.stream().filter(userInfo -> userInfo[0].equals(userName) && userInfo[1].equals(passWord)).findFirst();
        boolean success = false;
        if (matchedUser.isPresent()) {
            Long userId = Long.valueOf(matchedUser.get()[2]);
            String name = matchedUser.get()[3];
            String email = matchedUser.get()[4];
            String phone = matchedUser.get()[5];
            user = new Customer(userId,name,email,phone);
            String[] messages = new String[]{"Successfully login."};
            System.out.println(MassagePrinter.printMessageWithBox(messages));
            success = true;
        }else {
            String[] messages = new String[]{"Please check your password and user name."};
            System.out.println(MassagePrinter.printMessageWithBox(messages));
        }
        return success;
    }

}
