package com.msk.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.msk.moviecatalogservice.models.CatalogItem;
import com.msk.moviecatalogservice.models.Movie;
import com.msk.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfo {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem" /*, 
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
			} */
			)
	public CatalogItem getCatalogItem(Rating rating) {
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
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "Movie desc not found", rating.getRating());
	}
}
