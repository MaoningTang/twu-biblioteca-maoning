package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    protected static Library library_instance = null;
    protected static ArrayList<IntellectualProperty> books;

    protected Library() {
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

    private static String[][] toMatrix(List<IntellectualProperty> properties) {
        if (properties.size() == 0){
            return null;
        }
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

    protected Library(ArrayList<IntellectualProperty> books) {
        this.books = books;
    }

    public static boolean checkOutBook(long bookId, Customer checkOutBy){
        if (checkOutBy == null){
            return false;
        }
        Optional<IntellectualProperty> selectedBook = books.stream().filter(book -> book.id == bookId).findFirst();
        boolean success = false;
        if(selectedBook.isPresent()){
            selectedBook.get().setCheckOutBy(checkOutBy);
            success = true;
        }
        return success;
    }

    public static String[][] getCheckOutedBooks(Customer checkOutBy){
        List<IntellectualProperty> booksToShow = books.stream().filter(book -> book.checkOutBy != null && book.checkOutBy.equals(checkOutBy)).collect(Collectors.toList());
        String[][] result = toMatrix(booksToShow);
        return result;
    }

    public static boolean returnBook(long bookId, Customer returnBy){
        if (returnBy == null){
            return false;
        }
        Optional<IntellectualProperty> selectedBook = books.stream().filter(book -> book.id == bookId).findFirst();
        boolean success = false;
        if(selectedBook.isPresent()){
            selectedBook.get().setCheckOutBy(null);
            success = true;
        }
        return success;
    }

}
