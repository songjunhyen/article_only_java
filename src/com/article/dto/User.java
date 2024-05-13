package com.article.dto;

public class User {
	private String id;
	private String password;
	private int num;
	private String name;

	public User(String id, String password, int num, String name) {

		this.id = id;
		this.password = password;
		this.num = num;
		this.name = name;
	}

	// 게터 메서드
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	// 세터 메서드
	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setName(String name) {
		this.name = name;
	}
}
