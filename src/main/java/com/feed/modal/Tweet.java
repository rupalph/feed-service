package com.feed.modal;

public class Tweet implements Comparable<Tweet>{

	private String userid;
	private String message;
	private long timestamp;
	private String role;
	private String createdByUser;
	
	public Tweet(TweetBuilder tweetBuilder) {
		this.userid=tweetBuilder.userid;
		this.message=tweetBuilder.message;
		this.timestamp=tweetBuilder.timestamp;
		this.role=tweetBuilder.role;
		this.createdByUser=tweetBuilder.createdByUser;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	
	public String getRole() {
		return role;
	}
	
	public String toString()
	{
		return message;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public int compareTo(Tweet o) {
		//System.out.println("compareTo:"+ timestamp + o.getTimestamp());
		return (int) (this.timestamp - o.getTimestamp());
	}

	public static class TweetBuilder{
		private final  String userid;
		private final String message;
		private String role;
		private String createdByUser;
		private long timestamp;
		
		public TweetBuilder(String userid,String message)
		{
			this.userid=userid;
			this.message=message;
		}
		
		public TweetBuilder role(String role)
		{
			this.role=role;
			return this;
			
		}
		public TweetBuilder createdByUser(String createdByUser)
		{
			this.createdByUser=createdByUser;
			return this;
		}
		public TweetBuilder timestamp(long timestamp)
		{
			this.timestamp=timestamp;
			return this;
		}
		public Tweet build()
		{
			return new Tweet(this);
		}
	}

}
