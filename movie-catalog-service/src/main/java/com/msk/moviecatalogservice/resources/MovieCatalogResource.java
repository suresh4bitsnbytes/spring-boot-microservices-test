package com.msk.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

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
import com.msk.moviecatalogservice.services.MovieInfo;
import com.msk.moviecatalogservice.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
		
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		UserRating userRating = userRatingInfo.getUserRating(userId);
		
		return userRating.getRatings().stream()
				.map(rating-> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
		
	}

} 
