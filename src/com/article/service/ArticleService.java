package com.article.service;

import com.article.dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = new ArticleDao();
	}

	public int writeArticle(int num, String id, String title, String body, int viewCnt) {
		return articleDao.writeArticle(num, id, title, body, viewCnt);
	}

	public void changeArticle(int Id, String title, String body) {
		articleDao.changeArticle(Id, title, body);
	}

	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

}
