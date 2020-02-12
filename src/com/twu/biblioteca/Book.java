package com.twu.biblioteca;

public class Book {
    private String tittle;
    private String author;
    private int yearPublished;

    public Book() {
        tittle = "Default Tittle";
    }

    public Book(String tittle, String author, int yearPublished) {
        this.tittle = tittle;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String toString()
    {
        return "Tittle: " + tittle;
    }
}
