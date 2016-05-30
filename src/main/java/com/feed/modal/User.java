package com.feed.modal;

import java.util.ArrayList;
import java.util.List;

public class User {

	List<User> followers=new ArrayList<User>();
	String userid;
	public User(String string) {
		this.userid=string;
	}
	public List<User> getFollowers() {
		return followers;
	}
	public void addFollower(User u)
	{
		followers.add(u);
	}
	public String getUserid() {
		// TODO Auto-generated method stub
		return userid;
	}

}
