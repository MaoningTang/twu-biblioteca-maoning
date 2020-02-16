package com.twu.biblioteca;

public abstract class IntellectualProperty{
    protected long id;
    protected String tittle;
    protected String author;
    protected int yearPublished;
    protected Customer checkOutBy;

    public IntellectualProperty() {
        tittle = "Tittle";
        author = "author";
        yearPublished = 1880;
    }

    public IntellectualProperty(long id, String tittle, String author, int yearPublished, Customer checkOutBy) {
        this.id = id;
        this.tittle = tittle;
        this.author = author;
        this.yearPublished = yearPublished;
        this.checkOutBy = checkOutBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Customer getCheckOutBy() {
        return checkOutBy;
    }
    public String getCheckOutUserId(){
        return checkOutBy == null?"null":String.valueOf(checkOutBy.userId);
    }

    public void setCheckOutBy(Customer checkOutBy) {
        this.checkOutBy = checkOutBy;
    }

    public abstract String[] getDetailToDisplayArray();

    public abstract String[] getFullDetailArray();

    public abstract String[] getColumnNames();
}
