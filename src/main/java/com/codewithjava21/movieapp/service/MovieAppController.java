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
	
	private MovieAppRepository repo;
	
	public MovieAppController(MovieAppRepository movieRepo) {
		this.repo = movieRepo;
	}

	@GetMapping("/movie/id")
	public ResponseEntity<Movie> getMovieByMovieId(@PathVariable(value="id") int movieId) {
		
		Optional<Movie> returnVal = repo.findById(movieId);
		
		return ResponseEntity.ok(returnVal.get());
	}
	
	@GetMapping("/recommendations/movie/id")
	public ResponseEntity<List<Movie>> getMovieRecommendationsById(@PathVariable(value="id") int movieId) {
		
		List<Movie> returnVal = new ArrayList<>();
		
		// get original movie by id
		Optional<Movie> origMovie = repo.findById(movieId);
		
		// get list of movies by original movie's vector
		returnVal = repo.findMoviesByVector(origMovie.get().getVector());
		
		return ResponseEntity.ok(returnVal);
	}
}
