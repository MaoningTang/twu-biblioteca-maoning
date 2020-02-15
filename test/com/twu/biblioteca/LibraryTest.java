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
        assertThat(4, is(library.getBooks().size()));
    }

    @Test
    public void ShouldGetBooksMatrix() {
        //given
        Library library = Library.getInstance();
        //when
        String[][] booksMatrix = library.toBooksMatrix();
        //then
        assertThat(4, is(library.getBooks().size()));
        assertThat(booksMatrix[0][0], is("Tittle"));
        assertThat(booksMatrix[1][0], is("Water Margin"));
    }

    @Test
    public void ShouldCheckOut() {
        //given
        Library library = Library.getInstance();
        //when
        library.checkOut(1);
        String[][] booksMatrix = library.toBooksMatrix();
        //then
        assertThat(4, is(library.getBooks().size()));
        assertThat(booksMatrix[0][0], is("Tittle"));
        assertThat(booksMatrix[1][0], is("The Journey to the West"));
    }
}
