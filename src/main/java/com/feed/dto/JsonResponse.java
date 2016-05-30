package com.feed.dto;

import java.util.List;

public class JsonResponse {

	private List<TweetDto> tweets;

	public List<TweetDto> getResult() {
		return tweets;
	}

	public void setResult(List<TweetDto> result) {
		this.tweets = result;
	}
	
}
