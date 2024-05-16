package com.article.service;

import com.article.dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = new ArticleDao();
	}

	public int writeArticle(int memberId, String title, String body, int viewCnt) {
		return articleDao.writeArticle(memberId, title, body, viewCnt);
	}

	public void changeArticle(int articleId, String title, String body) {
		articleDao.changeArticle(articleId, title, body);
	}

}
