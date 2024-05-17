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

	public int writeArticle(int num, String id, String title, String body, int viewCnt) {
		int laId = findMaxId() + 1;
		articles.add(new Article(laId, id, Util.getDateStr(), title, body, viewCnt));
		return ++lastId;
	}

	public void changeArticle(int userId, String title, String body) {
		for (Article article : articles) {
			if (article.getNum() == userId) {
				article.setTitle(title);
				article.setBody(body);
			}
		}
	}

	public void deleteArticle(int Id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).getNum() == Id) {
				// 해당 ID를 가진 Article 객체를 리스트에서 제거합니다.
				articles.remove(i);
				return;
			}
		}
	}

	private int findMaxId() {
		int maxId = 0;
		for (Article article : articles) {
			if (article.getNum() > maxId) {
				maxId = article.getNum();
			}
		}
		return maxId;
	}
}
