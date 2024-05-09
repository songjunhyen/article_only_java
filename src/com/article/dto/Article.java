package com.article.dto;

public class Article {
	private int id;
	private String title;
	private String body;
	private String date;
	private int viewcount;

	public Article(int id, String title, String body, String date, int viewcount) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.date = date;
		this.viewcount = viewcount;
	}

	// 게터 메서드
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public String getDate() {
		return date;
	}

	public int getViewcount() {
		return viewcount;
	}

	// 세터 메서드
	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public void incviewcount() {
		viewcount++;
	}
}