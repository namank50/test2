package com.example.microservices.movieCatalogService.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.microservices.movieCatalogService.model.Rating;
import com.example.microservices.movieCatalogService.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getMovieRatings(@PathVariable String userId) {
		return restTemplate.getForObject("http://movie-ratings-service/ratingsData/users/"+userId,UserRating.class);
	}
	
	public UserRating getFallbackUserRating(@PathVariable String userId) {
		UserRating usr = new UserRating();
		usr.setUserRating(Arrays.asList(new Rating("0",0)));
		return usr;
		
	}

}
