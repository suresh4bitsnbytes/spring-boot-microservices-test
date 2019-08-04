package com.msk.ratingsdataservice.models;

import java.util.List;

public class UserRating {
	
	List<Rating> ratings;
	
	public UserRating() {
		
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	
	
}
