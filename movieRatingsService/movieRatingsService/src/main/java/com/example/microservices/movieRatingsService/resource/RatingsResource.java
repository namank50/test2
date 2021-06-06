package com.example.microservices.movieRatingsService.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.movieRatingsService.model.Rating;
import com.example.microservices.movieRatingsService.model.UserRating;

@RestController
@RequestMapping("/ratingsData")
public class RatingsResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getRatings(@PathVariable String userId) {
		List<Rating> ratings=Arrays.asList(
				new Rating("100",4),
				new Rating("200",3)
				);
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}

}
