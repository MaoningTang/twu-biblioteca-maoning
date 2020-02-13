package com.twu.biblioteca;

public class Book extends IntellectualProperty{

    public Book() {
        super();
    }

    public Book(String tittle, String author, int yearPublished) {
      super(tittle, author, yearPublished);
    }

    @Override
    public String[] getDetailArray() {
        return new String[]{tittle,author,String.valueOf(yearPublished)};
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"Tittle","Author","Year Published"};
    }
}
