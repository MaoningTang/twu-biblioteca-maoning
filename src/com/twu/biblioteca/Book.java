package com.twu.biblioteca;

public class Book extends IntellectualProperty{

    public Book() {
        super();
    }

    public Book(long id, String tittle, String author, int yearPublished, Customer checkOutBy) {
        super(id, tittle, author, yearPublished, checkOutBy);
    }

    @Override
    public String[] getDetailArray() {
        return new String[]{String.valueOf(id),tittle,author,String.valueOf(yearPublished)};
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"Book Id","Tittle","Author","Year Published"};
    }
}
