package com.searchmovies.service;

import com.searchmovies.entity.Movie;
import com.searchmovies.error.MovieNotFoundException;

import java.util.List;

public interface MovieService {
    Movie saveMovie(Movie movie);

    List<Movie> findAllMovie();

    List<Movie> findMovieByTitle(String title) throws MovieNotFoundException;

    Movie findMovieById(int id) throws MovieNotFoundException;
}
