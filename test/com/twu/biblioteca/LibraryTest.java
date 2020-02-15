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
        ArrayList<IntellectualProperty> movies = new ArrayList<IntellectualProperty>(Arrays.asList(new Movie(1,"Spring in a Small Town","Fei Mu",1948,null,9),
                new Movie(2,"Farewell My Concubine","Chen Kaige",1993,null,9),
                new Movie(3,"A Better Tomorrow","John Woo",1986,null,8),
                new Movie(4,"Days of Being Wild","Wong Kar-wai",1990,null,8),
                new Movie(4,"In the Mood for Love","Wong Kar-wai",2000,null,7)));
        library.setBooks(books);
        library.setMovies(movies);
    }

    @Test
    public void ShouldHaveBooks() {
        //then
        assertThat(library.getBooks().size(), is(4));
    }

    @Test
    public void ShouldHaveMovies() {
        //then
        assertThat(library.getMovies().size(), is(5));
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
    public void ShouldGetMoviesMatrix() {
        //when
        String[][] moviesMatrix = library.toMoviesMatrix();
        //then
        int numOfRows = moviesMatrix[1].length;
        int numOfColumns = moviesMatrix.length;
        assertThat(library.getMovies().size(), is(5));
        assertThat(numOfRows,is(5));
        assertThat(numOfColumns,is(6));
        assertThat(moviesMatrix[0][1], is("Name"));
        assertThat(moviesMatrix[1][1], is("Spring in a Small Town"));
    }

    @Test
    public void ShouldCheckOutBook() {
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
    public void ShouldCheckOutMovie() {
        //when
        Customer customer = new Customer(1);
        library.checkOutMovie(1,customer);
        String[][] moviesMatrix = library.toMoviesMatrix();
        //then
        Object[] movies = library.getMovies().stream().filter(movie -> movie.checkOutBy == null).toArray();
        assertThat(movies.length,is(4));
        assertThat(library.getMovies().size(), is(5));
        assertThat(library.getMovies().size(), is(5));
        assertThat(moviesMatrix[1].length,is(5));
        assertThat(moviesMatrix[0][1], is("Name"));
        assertThat(moviesMatrix[1][1], is("Farewell My Concubine"));
    }

    @Test
    public void ShouldGetCheckedBooks() {
        //given
        Customer customer = new Customer(1);
        library.checkOutBook(1,customer);
        //when
        String[][] booksMatrix = library.getCheckOutedBooks(customer);
        //then
        assertThat(booksMatrix.length,is(2));
        assertThat(booksMatrix[1][0], is("1"));
        assertThat(booksMatrix[0][1], is("Tittle"));
        assertThat(booksMatrix[1][1], is("Water Margin"));
    }

    @Test
    public void ShouldReturn() {
        //given
        Customer customer = new Customer(1);
        library.checkOutBook(1,customer);
        //when
        library.returnBook(1,customer);
        String[][] booksMatrix = library.toBooksMatrix();
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
