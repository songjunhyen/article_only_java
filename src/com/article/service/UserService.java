package com.article.service;

import com.article.dao.UserDao;

public class UserService {
	private UserDao userDao;

	public UserService() {
		this.userDao = new UserDao();
	}

	public boolean loginIdDupChk(String loginId) {
		return userDao.loginIdDupChk(loginId);
	}

	public void joinUser(String loginId, String loginPw, String name) {
		userDao.joinUser(loginId, loginPw, name);
	}

	public void updateUser(String id,String name,String password) {
		userDao.updateUser(id, name, password);
	}

	public void deleteUser(String id) {
		userDao.deleteUser(id);
	}
}
