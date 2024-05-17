package com.article.controller;

import java.util.List;
import java.util.Scanner;

import com.article.dto.User;

public abstract class Controller {

	public Scanner sc;
	public String cmd;
	public static User loginedUser;

	public abstract void doing(String cmd);

	public abstract void makeTestData();

	public static boolean isLogined() {
		return loginedUser != null;
	}

	protected User getUserByLoginId(String loginId, List<User> users) {
		for (User user : users) {
			if (user.getId().equals(loginId)) {
				return user;
			}
		}
		return null;
	}
}