package com.example.microservices.movieCatalogService.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.microservices.movieCatalogService.model.Catalog;
import com.example.microservices.movieCatalogService.model.Rating;
import com.example.microservices.movieCatalogService.model.UserRating;
import com.example.microservices.movieCatalogService.service.MovieInfoService;
import com.example.microservices.movieCatalogService.service.UserRatingService;

@RestController
@RequestMapping("/catalog")
public class movieCatalogResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MovieInfoService movieInfoService;
	
	@Autowired
	UserRatingService userRatingService;
	
	@GetMapping("/{userId}")
	  public List<Catalog> getCatalog(@PathVariable String userId){
		List<Rating> rate = Arrays.asList(new Rating("4",0));
		UserRating ratings=userRatingService.getMovieRatings(userId);
		return ratings.getUserRating().stream().map(rating->movieInfoService.getMOvieDetails(rating)
			).collect(Collectors.toList());
	
	}
 
	
}
