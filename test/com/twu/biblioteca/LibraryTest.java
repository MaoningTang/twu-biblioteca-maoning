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
}
