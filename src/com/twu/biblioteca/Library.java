package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    protected static Library library_instance = null;
    protected static ArrayList<IntellectualProperty> items;

    protected Library() {
    }

    public ArrayList<IntellectualProperty> getBooks() {
        return items.stream().filter(item -> item.getClass() == Book.class).collect(Collectors.toCollection(ArrayList::new));
    }

    public void setItems(ArrayList<IntellectualProperty> items) {
        this.items = items;
    }

    public static ArrayList<IntellectualProperty> getMovies() {
        return items.stream().filter(item -> item.getClass() == Movie.class).collect(Collectors.toCollection(ArrayList::new));
    }

    public static String[][] toBooksMatrix(){
        List<IntellectualProperty> booksToShow =  items.stream().filter(item -> item.getClass() == Book.class && item.checkOutBy == null).collect(Collectors.toList());
        String[][] result = toMatrix(booksToShow);
        return result;
    }

    public static String[][] toMoviesMatrix(){
        List<IntellectualProperty> moviesToShow =  items.stream().filter(item -> item.getClass() == Movie.class && item.checkOutBy == null).collect(Collectors.toList());
        String[][] result = toMatrix(moviesToShow);
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

    public static boolean checkOut(long id, Customer checkOutBy,String type){
        if (checkOutBy == null){
            return false;
        }
        Optional<IntellectualProperty> selectedItem = null;
        switch (type){
            case "book":
                selectedItem = items.stream().filter(item -> item.getClass() == Book.class && item.checkOutBy == null && item.id == id).findFirst();
                break;
            case "movie":
                selectedItem = items.stream().filter(item -> item.getClass() == Movie.class && item.checkOutBy == null && item.id == id).findFirst();
                break;
        }
        boolean success = false;
        if(selectedItem.isPresent()){
            selectedItem.get().setCheckOutBy(checkOutBy);
            success = true;
        }
        return success;
    }

    public static String[][] getCheckOutedBooks(Customer checkOutBy){
        List<IntellectualProperty> booksToShow = items.stream().filter(item -> item.getClass() == Book.class && item.checkOutBy != null && item.checkOutBy.equals(checkOutBy)).collect(Collectors.toList());
        String[][] result = toMatrix(booksToShow);
        return result;
    }

    public static boolean returnBook(long bookId, Customer returnBy){
        if (returnBy == null){
            return false;
        }
        Optional<IntellectualProperty> selectedBook = items.stream().filter(item ->  item.getClass() == Book.class && item.checkOutBy != null && item.id == bookId).findFirst();
        boolean success = false;
        if(selectedBook.isPresent()){
            selectedBook.get().setCheckOutBy(null);
            success = true;
        }
        return success;
    }

}
