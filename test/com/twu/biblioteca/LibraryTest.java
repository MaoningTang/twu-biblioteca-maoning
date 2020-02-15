package com.twu.biblioteca;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {

    private Library library;
    @Before
    //given
    public void setUpLibrary() {
        library = Library.getInstance();
        ArrayList<IntellectualProperty> books = new ArrayList<IntellectualProperty>(Arrays.asList(new Book(1,"Water Margin","Naian Shi",1999,null),
                new Book(2,"The Journey to the West","Chengen Wu",1992,null),
                new Book(3,"A Dream of Red Mansions","Xueqin Cao",1990,null),
                new Book(4,"The Three Kingdoms Era","Guanzhong Luo",1997,null)));
        library.setBooks(books);
    }

    @Test
    public void ShouldPrintMessageInBoxFormat() {
        //then
        assertThat(library.getBooks().size(), is(4));
    }

    @Test
    public void ShouldGetBooksMatrix() {
        //when
        String[][] booksMatrix = library.toBooksMatrix();
        //then
        assertThat(library.getBooks().size(), is(4));
        assertThat(booksMatrix[1].length,is(4));
        assertThat(booksMatrix[0][1], is("Tittle"));
        assertThat(booksMatrix[1][1], is("Water Margin"));
    }

    @Test
    public void ShouldCheckOut() {
        //when
        Customer customer = new Customer(1);
        library.checkOutBook(1,customer);
        String[][] booksMatrix = library.toBooksMatrix();
        //then
        Object[] books = library.getBooks().stream().filter(book -> book.checkOutBy == null).toArray();
        assertThat(books.length,is(3));
        assertThat(library.getBooks().size(), is(4));
        assertThat(library.getBooks().size(), is(4));
        assertThat(booksMatrix[1].length,is(4));
        assertThat(booksMatrix[0][1], is("Tittle"));
        assertThat(booksMatrix[1][1], is("The Journey to the West"));
    }

    @Test
    public void ShouldGetCheckedBooks() {
        //given
        Customer customer = new Customer(1);
        library.checkOutBook(1,customer);
        //when
        String[][] booksMatrix = library.getCheckOutedBooks(customer);
        //then
        assertThat(booksMatrix.length,is(1));
        assertThat(booksMatrix[1][0], is("1"));
        assertThat(booksMatrix[0][1], is("Tittle"));
        assertThat(booksMatrix[1][1], is("Water Margin"));
    }

    @Test
    public void ShouldReturn() {
        //given
        Customer customer = new Customer(1);
        library.checkOutBook(1,customer);
        String[][] booksMatrix = library.toBooksMatrix();
        //when
        library.returnBook(1,customer);
        //then
        Object[] books = library.getBooks().stream().filter(book -> book.checkOutBy == null).toArray();
        assertThat(books.length,is(4));
        assertThat(library.getBooks().size(), is(4));
        assertThat(library.getBooks().size(), is(4));
        assertThat(booksMatrix[1].length,is(4));
        assertThat(booksMatrix[1][0], is("1"));
        assertThat(booksMatrix[0][1], is("Tittle"));
        assertThat(booksMatrix[1][1], is("Water Margin"));
    }
}
