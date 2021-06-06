package com.example.microservices.movieCatalogService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.microservices.movieCatalogService.model.Catalog;
import com.example.microservices.movieCatalogService.model.Movie;
import com.example.microservices.movieCatalogService.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfoService {
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem"
	/*
	 * ,commandProperties= {
	 * 
	 * @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
	 * value="2000"),
	 * 
	 * @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
	 * 
	 * @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),
	 * 
	 * @HystrixProperty(name="execution.sleepWindowInMilliseconds",value="5000"),
	
	} */
			)
	public Catalog getMOvieDetails(Rating rating) {
		Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		return new Catalog(movie.getName(),movie.getDescription(),rating.getRatings());
	}
	
	public Catalog getFallbackCatalogItem(Rating rating) {
		return new Catalog("MOvie name not found","Movie desc. not found",rating.getRatings());
	}

}
