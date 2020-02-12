package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Library {
    private static Library library_instance = null;
    private ArrayList<Book> books;

    private Library() {
        books = new ArrayList<Book>(Arrays.asList(new Book("Water Margin"),
                new Book("The Journey to the West"),
                new Book("A Dream of Red Mansions"),
                new Book("Romance of the Three KingdomsThe Three Kingdoms Era")));
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String[] toBooksStringArray(){
        return books.stream().map(book -> {return book.toString();}).toArray(String[]::new);
    }

    public static Library getInstance() {
        if (library_instance == null)
            library_instance = new Library();

        return library_instance;
    }

}
