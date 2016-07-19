package com.feed.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.feed.dto.TweetDto;
import com.feed.modal.Tweet;
import com.feed.modal.User;
import com.feed.util.AppCache;
import com.feed.util.DBUtil;

public class TweetRepository {

	/**
	 * Fetches tweets for user id from cache/db
	 * 
	 * @param userid
	 * @return
	 */
	public static List<TweetDto> fetchTweets(String userid) {
		List<Tweet> tweets= getRecentTweets(userid);
		System.out.println("TweetRepository:"+tweets);
		List<TweetDto> result=new ArrayList<TweetDto>();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		for(Tweet t:tweets){
			TweetDto tj=new TweetDto();
			tj.setUserid(t.getUserid());
			tj.setMessage(t.getMessage());
			Date dt=new Date();
			dt.setTime(t.getTimestamp());
			tj.setTimestamp(sdf.format(dt));
			result.add(tj);
		}
		System.out.println(result);
		return result;
	}

	/**
	 * Get recent tweets of the users (traverse the friends graph and fetch recent tweets)
	 * 
	 * @param userid
	 * @return
	 */
	public static List<Tweet> getRecentTweets(String userid)
	{
		System.out.println("TweetDAO:getRecentTweets");
		List<Tweet> list = new ArrayList<Tweet>();
		list = DBUtil.getRecentTweetsFromFriends(userid);
		System.out.println("TweetDAO getRecentTWeets:"+list);
		return list;
	}
	/**
	 * Saves the tweet
	 * @param e
	 */
	public static void save(TweetDto e) {

		Tweet tweet=new Tweet.TweetBuilder(e.getUserid(), e.getMessage()).
				createdByUser(e.getUserid()).
				timestamp(Calendar.getInstance().getTimeInMillis()).build();
		
		DBUtil.saveTweet(tweet);
	}
	
	

}
