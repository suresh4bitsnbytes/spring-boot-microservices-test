package com.msk.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.msk.moviecatalogservice.models.CatalogItem;
import com.msk.moviecatalogservice.models.Movie;
import com.msk.moviecatalogservice.models.Rating;
import com.msk.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
		
		return userRating.getRatings().stream().map(rating-> {
			
			//Using RestTemplate
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			
			/*
			//using WebClient
			Movie movie = webClientBuilder.build()
					.get()
					.uri("http://localhost:8082/movies/"+rating.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();
			*/
			return new CatalogItem(movie.getName(), "Movie Desc", rating.getRating());
		}).collect(Collectors.toList());
		
	}
} 
