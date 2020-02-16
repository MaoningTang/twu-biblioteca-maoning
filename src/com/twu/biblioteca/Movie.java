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
    public String[] getDetailToDisplayArray() {
        return new String[]{String.valueOf(id),tittle,author,String.valueOf(yearPublished),String.valueOf(rating)};
    }

    @Override
    public String[] getFullDetailArray() {
        return new String[]{String.valueOf(id),tittle,author,String.valueOf(yearPublished),super.getCheckOutUserId(),String.valueOf(rating)};
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"Movie Id","Name","Director","Year Published","Rating"};
    }
}
