package com.msk.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msk.ratingsdataservice.models.Rating;
import com.msk.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId, 5);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating("tt4154796", 4),
				new Rating("tt6320628", 3)
				);
		UserRating userRating = new UserRating();
		userRating.setRatings(ratings);
		return userRating;
	}
}
