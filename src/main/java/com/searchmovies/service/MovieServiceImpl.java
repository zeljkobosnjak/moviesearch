package com.searchmovies.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchmovies.entity.Movie;
import com.searchmovies.error.MovieNotFoundException;
import com.searchmovies.repository.MovieRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;

    @Override
    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public List<Movie> findAllMovie() {
        return repository.findAll();
    }

    @Override
    public List<Movie> findMovieByTitle(String title) throws MovieNotFoundException {
        List<Movie> movies = repository.findByNameContaining(title);
        if (movies.size() == 0) {
            try {

                RestTemplate restTemplate = new RestTemplate();
                Movie movie = restTemplate.getForObject("https://api.tvmaze.com/singlesearch/shows?q=" + title, Movie.class);
                Movie movie1 = saveMovie(movie);
                movies.add(movie1);

            } catch (Exception e) {
                throw new MovieNotFoundException("Movie Not Found");
            }
        }
        return movies;
    }

    @Override
    public Movie findMovieById(int id) throws MovieNotFoundException {
        Optional<Movie> movie = repository.findById(id);
        if (!movie.isPresent()) {
            throw new MovieNotFoundException("Movie Not Found");
        }
        return movie.get();
    }
}
