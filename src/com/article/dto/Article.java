package com.article.dto;

public class Article {
	private int num;
	private String title;
	private String body;
	private String date;
	private String id;
	private int viewcount;

	public Article(int num, String id, String date, String title, String body, int viewcount) {
		this.num = num;
		this.title = title;
		this.date = date;
		this.body = body;
		this.setId(id);
		this.viewcount = viewcount;
	}

	// 게터 메서드
	public int getNum() {
		return num;
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

	public String getId() {
		return id;
	}

	// 세터 메서드
	public void setNum(int id) {
		this.num = num;
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

	public void setId(String id) {
		this.id = id;
	}

}