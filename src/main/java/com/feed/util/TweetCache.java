package com.feed.util;


import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.feed.modal.Tweet;

public class TweetCache {


	private int size;
	PriorityQueue<Tweet> cache;
	/**
	 * Initializes Cache with size
	 * @param i
	 */
	public TweetCache(int i) {
		size=i;
		cache =new PriorityQueue<Tweet>(size,new Comparator<Tweet>() {
		    public int compare(Tweet lhs, Tweet rhs) {
		       return lhs.compareTo(rhs);
		    }
		});
	}

	
	/**
	 * Removes oldest tweet from the cache if cache is full
	 * 
	 * @param e
	 */
	public void add(Tweet e ) {

		
		if(cache.size()>=size) {
			Tweet head=cache.peek();
			if(e.compareTo(head)>0) {
				cache.poll();
				cache.add(e);
			}
			
		}
		else
			cache.add(e);
	}


	/***
	 * Returns all the tweets in cache
	 * 
	 * @return
	 */
	public List<Tweet> getTweets() {
		List<Tweet> all=new ArrayList<Tweet>(cache);
		return all;
	}
	
	@Override
	public String toString() {
		return "cache:"+cache.toString();
	}

}
