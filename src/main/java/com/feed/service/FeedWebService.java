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


	/**
	 * User Logs-in using LDAP
	 * For Demo, using LoginService - Sets a random token, which user can use to get list of feeds.
	 * 
	 * Resource: Tweet { Userid,message, timestamp }
	 * 
	 * Resource URI
	 * http://localhost:8080/FeedService/api/feed/{userid}
	 * 
	 * Response: 
	 * Content/type:Json
	 * {userid:<userid>, message:<message>, time:<timestamp>}
	 * 
	 * 
	 */
	@GET
	@Path("/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findRecentTweets(@PathParam("userid") String userid,@QueryParam("authToken") int token)
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

	/** 
	 * User can post a tweet using this call
	 * @param tweet
	 * @return
	 */
	@POST
	@Path("/{userid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postTweet(@PathParam("userid") String userid,@QueryParam("authToken") int token,TweetDto tweet) {

		//check if the user is logged in
		if(LoginMockHelper.isUserLoggedIn(userid, token)) {
			//if yes, continue, else return

			String result = "Tweet saved : " + tweet.getUserid() + ","+ tweet.getMessage();
			TweetRepository.save(tweet);
			return Response.status(201).entity(result).build();
		}
		else
			return Response.status(401).build();
	}

}