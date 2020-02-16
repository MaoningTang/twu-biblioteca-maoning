package com.twu.biblioteca;

public class Book extends IntellectualProperty{

    public Book() {
        super();
    }

    public Book(long id, String title, String author, int yearPublished, Customer checkOutBy) {
        super(id, title, author, yearPublished, checkOutBy);
    }

    @Override
    public String[] getDetailToDisplayArray() {
        return new String[]{String.valueOf(id),title,author,String.valueOf(yearPublished)};
    }

    @Override
    public String[] getFullDetailArray() {
        return new String[]{String.valueOf(id),title,author,String.valueOf(yearPublished),super.getCheckOutUserId()};
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"Book Id","Title","Author","Year Published"};
    }
}
