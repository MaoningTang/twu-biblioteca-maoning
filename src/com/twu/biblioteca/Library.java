package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Library {
    private static Library library_instance = null;
    private ArrayList<IntellectualProperty> books;

    private Library() {
        books = new ArrayList<IntellectualProperty>(Arrays.asList(new Book("Water Margin","Naian Shi",1999),
                new Book("The Journey to the West","Chengen Wu",1992),
                new Book("A Dream of Red Mansions","Xueqin Cao",1990),
                new Book("The Three Kingdoms Era","Guanzhong Luo",1997)));
    }

    public ArrayList<IntellectualProperty> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<IntellectualProperty> books) {
        this.books = books;
    }

    public String[] toBooksStringArray(){
        return books.stream().map(book -> {return book.toString();}).toArray(String[]::new);
    }

    public String[][] toBooksMatrix(){
        String[][] result = toMatrix(books);
        return result;
    }

    private String[][] toMatrix(ArrayList<IntellectualProperty> properties) {
        String[][] result = new String[properties.size()+1][];
        String[][] tittleMatrix = new String[][]{properties.get(0).getColumnNames()};
        String[][] booksMatrix = properties.stream().map(propertie -> {return propertie.getDetailArray();}).toArray(String[][]::new);
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
