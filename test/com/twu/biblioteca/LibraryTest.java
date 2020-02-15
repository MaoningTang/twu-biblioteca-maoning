package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {
    @Test
    public void ShouldPrintMessageInBoxFormat() {
        //given
        Library library = Library.getInstance();
        //then
        assertThat(library.getBooks().size(), is(4));
    }

    @Test
    public void ShouldGetBooksMatrix() {
        //given
        Library library = Library.getInstance();
        //when
        String[][] booksMatrix = library.toBooksMatrix();
        //then
        assertThat(library.getBooks().size(), is(4));
        assertThat(booksMatrix[0][0], is("Tittle"));
        assertThat(booksMatrix[1][0], is("Water Margin"));
    }

    @Test
    public void ShouldCheckOut() {
        //given
        Library library = Library.getInstance();
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
}
