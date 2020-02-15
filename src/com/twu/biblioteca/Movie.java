package com.twu.biblioteca;

public class Movie extends IntellectualProperty{
    private int rating;

    public Movie() {
        super();
        this.rating = 0;
    }

    public Movie(long id, String tittle, String author, int yearPublished, Customer checkOutBy, int rating) {
        super(id, tittle, author, yearPublished, checkOutBy);
        this.rating = (rating <= 10 && rating >=0) ? rating :  0;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = (rating <= 10 && rating >=0) ? rating :  0;
    }

    @Override
    public String[] getDetailArray() {
        return new String[0];
    }

    @Override
    public String[] getColumnNames() {
        return new String[0];
    }
}
