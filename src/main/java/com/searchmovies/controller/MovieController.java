package com.searchmovies.controller;

import com.searchmovies.entity.Movie;
import com.searchmovies.error.MovieNotFoundException;
import com.searchmovies.service.MovieService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    private String API = "https://www.omdbapi.com/?i=tt3896198&apikey=1d1d992&t=Inkaar";
    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/movies")
    public List<Movie> findAllMovies() {
        return service.findAllMovie();
    }

    @GetMapping("/movies/id/{id}")
    public Movie findMovieById(@PathVariable("id") int id) throws MovieNotFoundException {
        return service.findMovieById(id);
    }

    @GetMapping("/movies/{title}")
    public List<Movie> findMovieByTitle(@PathVariable("title") String title) throws MovieNotFoundException {
        return service.findMovieByTitle(title);
    }
}
