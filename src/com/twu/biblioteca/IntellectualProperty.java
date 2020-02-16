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
