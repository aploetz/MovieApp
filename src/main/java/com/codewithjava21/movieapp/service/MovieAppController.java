package com.codewithjava21.movieapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/movieapp")
@RestController
public class MovieAppController {
	
	private MovieRepository movieRepo;
	private MovieByTitleRepository movieTitleRepo;
	
	public MovieAppController(MovieRepository movieRepo, MovieByTitleRepository movieTitleRepo) {
		this.movieRepo = movieRepo;
		this.movieTitleRepo = movieTitleRepo;
	}

	@GetMapping("/movie/id")
	public ResponseEntity<Movie> getMovieByMovieId(@PathVariable(value="id") int movieId) {
		
		Optional<Movie> returnVal = movieRepo.findById(movieId);
		
		return ResponseEntity.ok(returnVal.get());
	}
	
	@GetMapping("/movie/title")
	public ResponseEntity<Movie> getMovieByTitle(@PathVariable(value="title") String movieTitle) {
		Optional<Movie> returnVal = Optional.ofNullable(new Movie());
		Optional<MovieByTitle> movieByTitle = movieTitleRepo.findById(movieTitle.toLowerCase());
		
		if (movieByTitle.isEmpty()) {
			// try one more time with "the " on the front"
			movieByTitle = movieTitleRepo.findById("the " + movieTitle.toLowerCase());
		}

		if (movieByTitle.isPresent()) {
			int movieId = movieByTitle.get().getMovieId();
			returnVal = movieRepo.findById(movieId);
		}
		
		return ResponseEntity.ok(returnVal.get());
	}
	
	@GetMapping("/recommendations/movie/id")
	public ResponseEntity<List<Movie>> getMovieRecommendationsById(@PathVariable(value="id") int movieId) {
		
		List<Movie> returnVal = new ArrayList<>();
		
		// get original movie by id
		Optional<Movie> origMovie = movieRepo.findById(movieId);
		
		// get list of movies by original movie's vector
		returnVal = movieRepo.findMoviesByVector(origMovie.get().getVector());
		// The first item in the list will be the original movie, so REMOVE
		returnVal.remove(0);
		
		return ResponseEntity.ok(returnVal);
	}
}
