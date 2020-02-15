package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MovieTest {

    @Test
    public void shouldSetRatingIfValid() {
        //given
        Movie movie = new Movie();
        movie.setRating(10);
        //then
        assertThat(movie.getRating(), is(10));
    }

    @Test
    public void shouldNotSetRatingIfLargerThan10() {
        //given
        Movie movie = new Movie();
        movie.setRating(11);
        //then
        assertThat(movie.getRating(), is(0));
    }

    @Test
    public void shouldNotSetRatingIfNative() {
        //given
        Movie movie = new Movie();
        movie.setRating(-1);
        //then
        assertThat(movie.getRating(), is(0));
    }
}
