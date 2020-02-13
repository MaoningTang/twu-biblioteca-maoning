package com.twu.biblioteca;

public abstract class IntellectualProperty{
    protected String tittle;
    protected String author;
    protected int yearPublished;

    public IntellectualProperty() {
        tittle = "Tittle";
        author = "author";
        yearPublished = 1880;
    }

    public IntellectualProperty(String tittle, String author, int yearPublished) {
        this.tittle = tittle;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public abstract String[] getDetailArray();

    public abstract String[] getColumnNames();
}
