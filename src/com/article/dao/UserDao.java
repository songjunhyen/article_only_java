package com.article.dao;

import java.util.List;

import com.article.container.Container;
import com.article.dto.User;
import com.article.Util;

public class UserDao {
	private List<User> users;
	private int lastnum;

	public UserDao() {
		this.lastnum = 1;
		this.users = Container.users;
	}

	public void joinUser(String loginId, String loginPw, String name) {
		users.add(new User(lastnum, Util.getDateStr(), loginId, loginPw, name));
		lastnum++;
	}

	public void updateUser(String id, String name, String password) {
		User user = getUserByLoginId(id);
		if (user != null) {
			user.setName(name);
			user.setPassword(password);
		}
	}

	public void deleteUser(String id) {
		User user = getUserByLoginId(id);
		if (user != null) {
			users.remove(user);
		}
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