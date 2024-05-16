package com.article.container;

import java.util.ArrayList;
import java.util.List;

import com.article.dto.Article;
import com.article.dto.User;

public class Container {
	public static List<User> users;
	public static List<Article> articles;
	
	static {
		users = new ArrayList<>();
		articles = new ArrayList<>();
	}
}