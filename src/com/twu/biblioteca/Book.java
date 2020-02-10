package com.twu.biblioteca;

public class Book {
    private String tittle;

    public Book() {
        tittle = "Default Tittle";
    }

    public Book(String tittle) {
        this.tittle = tittle;
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
