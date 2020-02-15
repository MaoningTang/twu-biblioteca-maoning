package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Library {
    private static Library library_instance = null;
    private ArrayList<IntellectualProperty> books;

    private Library() {
        books = new ArrayList<IntellectualProperty>(Arrays.asList(new Book(1,"Water Margin","Naian Shi",1999,null),
                new Book(2,"The Journey to the West","Chengen Wu",1992,null),
                new Book(3,"A Dream of Red Mansions","Xueqin Cao",1990,null),
                new Book(4,"The Three Kingdoms Era","Guanzhong Luo",1997,null)));
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
        List<IntellectualProperty> booksToShow =  books.stream().filter(book -> book.checkOutBy == null).collect(Collectors.toList());
        String[][] result = toMatrix(booksToShow);
        return result;
    }

    private String[][] toMatrix(List<IntellectualProperty> properties) {
        String[][] result = new String[properties.size()+1][];
        String[][] tittleMatrix = new String[][]{properties.get(0).getColumnNames()};
        String[][] booksMatrix = properties.stream().map(property -> {return property.getDetailArray();}).toArray(String[][]::new);
        System.arraycopy(tittleMatrix, 0, result, 0, tittleMatrix.length);
        System.arraycopy(booksMatrix, 0, result, tittleMatrix.length, booksMatrix.length);
        return result;
    }

    public static Library getInstance() {
        if (library_instance == null)
            library_instance = new Library();
        return library_instance;
    }

    public Library(ArrayList<IntellectualProperty> books) {
        this.books = books;
    }

    public void checkOutBook(long bookId, Customer checkOutBy){
        books.stream().filter(book -> book.id == bookId).forEach(b -> b.setCheckOutBy(checkOutBy));
    }

}
