package com.codewithjava21.movieapp.service;

import java.time.LocalDate;
import java.util.Map;

import com.datastax.oss.driver.api.core.data.CqlVector;

//import com.datastax.oss.driver.api.core.data.CqlVector;

public class Movie {
    private int movieId;
    private String imdbId;
    private String title;
    private String description;
    private float runtime;
    private String tagline;
    private String originalLanguage;
    private String image;
    private Map<Integer,String> genres;
    private String website;
    private LocalDate releaseDate;
    private Long budget;
    private Long revenue;
    private int year;
    private CqlVector<Float> vector;
    
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public String getImdbId() {
		return imdbId;
	}
	
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getRuntime() {
		return runtime;
	}

	public void setRuntime(float runtime) {
		this.runtime = runtime;
	}
	
	public String getTagline() {
		return tagline;
	}
	
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	
	public String getOriginalLanguage() {
		return originalLanguage;
	}
	
	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public Map<Integer, String> getGenres() {
		return genres;
	}
	
	public void setGenres(Map<Integer, String> genres) {
		this.genres = genres;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public Long getBudget() {
		return budget;
	}
	
	public void setBudget(Long budget) {
		this.budget = budget;
	}  
	
	public Long getRevenue() {
		return revenue;
	}
	
	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}
	
	public CqlVector<Float> getVector() {
		return vector;
	}
	
	public void setVector(CqlVector<Float> vector) {
		this.vector = vector;
	}
}
