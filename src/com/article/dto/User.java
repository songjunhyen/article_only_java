package com.article.dto;

public class User {
	private String id;
	private String password;
	private int num;
	private String name;
	private String dateStr;

	public User(int num, String dateStr, String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.num = num;
		this.name = name;
		this.setDateStr(dateStr);
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

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

}
