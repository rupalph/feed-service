package com.feed.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.feed.modal.Tweet;

public class AppCache {
	private static final int DEFAULT_CACHE_SIZE = 3;
	private static ConcurrentMap<String,TweetCache> appCache=new ConcurrentHashMap<String, TweetCache>();

	/**
	 * Gets recent tweets from cache
	 * 
	 * @param userid
	 * @return
	 */


	/**
	 * helper method to add the tweets to app level cache
	 * 
	 * @param userid
	 * @param tweets
	 * @return
	 */
	public static List<Tweet> addToCache(String userid,List<Tweet> tweets)
	{
		TweetCache tc=getUserTweetCache(userid);
		
		for(Tweet e:tweets)
		{
			
			tc.add(e);
		}
		appCache.put(userid,tc);
		return tc.getTweets();
	}

	/**
	 * checks,if appcache has userid
	 * @param userid
	 * @return
	 */
	public static boolean hasUserId(String userid) {
		return appCache.containsKey(userid);
	}

	/**
	 * Gets tweets from appcache
	 * 
	 * @param userid
	 * @return
	 */
	public static List<Tweet> getTweets(String userid) {
		List<Tweet> result=new ArrayList<>();
		if(appCache.get(userid)!=null)
			result= appCache.get(userid).getTweets();
		return result;
	}

	/**
	 * Adds single tweet to cache
	 * 
	 * @param userid
	 * @param tweet
	 */
	public static void addToCache(String userid, Tweet tweet) {
		TweetCache tc=getUserTweetCache(userid);
		tc.add(tweet);
		appCache.put(userid,tc);
	}

	/**
	 * Gets User level tweet cache
	 * 
	 * @param userid
	 * @param tweet
	 * @return
	 */
	private static TweetCache getUserTweetCache(String userid) {
		
		if(appCache.get(userid)==null)
		{
			return new TweetCache(DEFAULT_CACHE_SIZE);
		}
		else
		{
			return appCache.get(userid);
		}
		
	}

	public static void clear() {
		appCache.clear();
		
	}
}
