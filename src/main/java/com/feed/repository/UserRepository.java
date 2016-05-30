package com.feed.repository;


import com.feed.modal.User;
import com.feed.util.TweetDAO;

public class UserRepository {

	/**
	 * Finds user by id
	 * @param userid
	 * @return
	 */
	public static User findUserById(String userid) {
		return TweetDAO.findUserById(userid);
	}

}
