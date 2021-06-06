package com.example.microservices.movieRatingsService.model;

public class Rating {
	
	private String movieId;
	private int ratings;
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public Rating(String movieId, int ratings) {
		super();
		this.movieId = movieId;
		this.ratings = ratings;
	}
	
	

}
