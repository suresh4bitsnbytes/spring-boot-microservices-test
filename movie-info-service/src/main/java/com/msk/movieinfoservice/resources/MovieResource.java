package com.msk.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.msk.movieinfoservice.models.Movie;
import com.msk.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${moviedb.api.key}")
	private String apiKey;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable String movieId) {
		MovieSummary movieSummary = restTemplate.getForObject("http://www.omdbapi.com/?i=" + movieId + "&apikey=" +  apiKey, MovieSummary.class);
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getPlot());
	}
}
