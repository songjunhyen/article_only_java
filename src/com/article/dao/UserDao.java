package com.article.dao;

import java.util.List;

import com.article.container.Container;
import com.article.dto.User;
import com.article.Util;

public class UserDao {
	private List<User> users;
	private int lastId;
	
	public UserDao() {
		this.lastId = 1;
		this.users = Container.users;
	}
	
	public void joinUser(String loginId, String loginPw, String name) {
		users.add(new User(lastId, Util.getDateStr(), loginId, loginPw, name));
		lastId++;
	}
	
	private User getUserByLoginId(String loginId) {
		for (User user : users) {
			if (user.getId().equals(loginId)) {
				return user;
			}
		}
		return null;
	}
	
	public boolean loginIdDupChk(String loginId) {
		User user = getUserByLoginId(loginId);
		
		if (user != null) {
			return false;
		}
		return true;
	}

}
