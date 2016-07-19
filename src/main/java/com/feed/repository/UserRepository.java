package com.feed.repository;


import com.feed.modal.User;
import com.feed.util.DBUtil;

public class UserRepository {

	/**
	 * Finds user by id
	 * @param userid
	 * @return
	 */
	public static User findUserById(String userid) {
		return DBUtil.findUserById(userid);
	}
	
	

	public static void addUser(String userid) {
		DBUtil.addUser(userid);
		
	}

}
