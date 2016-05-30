package com.feed.service;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.feed.dto.JsonResponse;
import com.feed.dto.TweetDto;
import com.feed.repository.TweetRepository;
import com.feed.util.LoginMockHelper;
@Path("/feed")
public class FeedWebService {

	@GET
	@Path("/test/{param}")
	public Response getMsg(@PathParam("param") String msg) {

		String output = "Jersey say : " + msg;

		return Response.status(200).entity(output).build();

	}

	/**
	 * Login service - mock
	 * User  - follow - many users 
	 * 100 recent tweets from their immediate connections
	 * 
	 * logged in user
	 * user id
	 * users he/she following
	 * each user tweets
	 * 
	 * lookup 100 most recent tweets from list of n users
	 * 
	 * Tweet class - tweetid, userid, timestamp, message, createdByUser
	 * 
	 * first look up users current user is following
	 * list each users most recent 100 tweets
	 * sort all of the tweets
	 * find top 100
	 * 
	 */
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findRecentTweets(@PathParam("param") String userid,@QueryParam("authToken") int token)
	{
		//check if the user is logged in
		if(LoginMockHelper.isUserLoggedIn(userid, token)) {
			//if yes, continue, else return
			List<TweetDto> userTweets = TweetRepository.fetchTweets(userid);
			JsonResponse resp=new JsonResponse();
			resp.setResult(userTweets);
			return Response.status(200).entity(resp).build();
		}
		else
			return Response.status(401).build();
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postTweet(TweetDto tweet) {

		String result = "Tweet saved : " + tweet;
		TweetRepository.save(tweet);
		return Response.status(201).entity(result).build();

	}

}