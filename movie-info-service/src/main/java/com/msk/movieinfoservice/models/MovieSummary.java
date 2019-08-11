package com.msk.movieinfoservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieSummary {
	
	private String imdbID;
	
	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("Plot")
	private String plot;
	
	
	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MovieSummary() {
		
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	

}
