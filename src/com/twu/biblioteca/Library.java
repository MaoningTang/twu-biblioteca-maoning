package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Library {
    private static Library library_instance = null;
    private ArrayList<Book> books;

    private Library() {
        books = new ArrayList<Book>(Arrays.asList(new Book("Water Margin","Naian Shi",1999),
                new Book("The Journey to the West","Chengen Wu",1992),
                new Book("A Dream of Red Mansions","Xueqin Cao",1990),
                new Book("The Three Kingdoms Era","Guanzhong Luo",1997)));
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

    public String[][] toBooksMatrix(){
        String[][] result = new String[books.size()+1][];
        String[][] tittleMatrix = new String[][]{{"Tittle","Author","Year Published"}};
        String[][] booksMatrix = books.stream().map(book -> {return new String[]{book.getTittle(),book.getAuthor(),String.valueOf(book.getYearPublished())};}).toArray(String[][]::new);
        System.arraycopy(tittleMatrix, 0, result, 0, tittleMatrix.length);
        System.arraycopy(booksMatrix, 0, result, tittleMatrix.length, booksMatrix.length);
        return result;
    }

    public static Library getInstance() {
        if (library_instance == null)
            library_instance = new Library();
        return library_instance;
    }

}
