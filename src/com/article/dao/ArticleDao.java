package com.article.dao;

import java.util.List;

import com.article.Util;
import com.article.container.Container;
import com.article.dto.Article;

public class ArticleDao {
	private List<Article> articles;
	private int lastId;
	
	public ArticleDao() {
		this.lastId = 1;
		this.articles = Container.articles;
	}

	public int writeArticle(int memberId, String title, String body, int viewCnt) {
		articles.add(new Article(lastId, Util.getDateStr(), title, body, viewCnt));
		return lastId++;
	}
	
	public void changeArticle(int userId, String title, String body) {
		for (Article article : articles) {
	        if (article.getId() == userId) {
	            article.setTitle(title);
	            article.setBody(body);
	        }
	    }
	}
}
