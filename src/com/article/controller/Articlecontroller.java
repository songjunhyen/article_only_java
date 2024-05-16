package com.article.controller;

import com.article.container.Container;
import com.article.dto.Article;
import com.article.dto.User;
import com.article.service.ArticleService;

import java.util.List;
import java.util.Scanner;

public class Articlecontroller extends Controller {

	private List<Article> articles;
	private List<User> users;
	private ArticleService articleService;

	public Articlecontroller(Scanner sc) {
		this.sc = sc;
		this.articles = Container.articles;
		this.users = Container.users;
		this.articleService = new ArticleService();
	}

	public void doing(String cmd) {
		makeTestData(); 
		switch (cmd) {
		case "목록":
			index();
			break;
		case "등록":
			write();
			break;
		case "조회":
			search_num();
			break;
		case "수정":
			change();
			break;
		case "삭제":
			delete();
			break;
		case "검색":
			search_word();
			break;
		default:
			System.out.println("올바른 명령어를 입력하세요.");
		}
	}

	private void index() {
		if (articles.isEmpty()) {
			System.out.println("존재하는 게시글이 없습니다");
		} else {
			System.out.println(" 번호 |  제목  | 조회수 | 등록일자   ");
			for (int i = articles.size() - 1; i >= 0; i--) {
				Article item = articles.get(i);
				System.out.printf(" %-5d| %-6.6s |  %-4d  | %8s\n", item.getId(), item.getTitle(), item.getViewcount(),
						item.getDate());
			}
		}
	}

	private void write() {
		System.out.print("제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("내용 : ");
		String body = sc.nextLine().trim();

		int articleNumber = articleService.writeArticle(1, title, body, 0);

		System.out.println(articleNumber + "번 글이 생성되었습니다");
	}

	private void change() {
		System.out.print("수정할 게시글의 번호를 입력하세요: ");
		String idStr = sc.nextLine().trim();
		int id = Integer.parseInt(idStr);

		Article foundArticle = getArticleById(id);
		User foundUser = getUserByLoginId(getLoginIdByUserId(foundArticle.getId()), users); // 여기서 getUserByLoginId()를
																							// 호출

		if (foundUser != null && foundUser.getId() == loginedUser.getId()) {
			System.out.print("수정할 제목: ");
			String title = sc.nextLine().trim();
			System.out.print("수정할 내용: ");
			String body = sc.nextLine().trim();

			articleService.changeArticle(id, title, body);

			System.out.println(id + "번 게시글이 수정되었습니다.");
		} else {
			System.out.println("해당 게시물에 대한 권한이 없습니다");
		}
	}

	private void search_num() {
		System.out.print("조회할 게시글의 번호 : ");
		String idstr = sc.nextLine().trim();
		int id = Integer.parseInt(idstr);
		Article foundArticle = getArticleById(id);
		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다");
			return;
		}

		foundArticle.incviewcount();

		System.out.println("번호: " + foundArticle.getId());
		System.out.println("제목: " + foundArticle.getTitle());
		System.out.println("내용: " + foundArticle.getBody());
		System.out.println("날짜: " + foundArticle.getDate());
		System.out.println("조회수: " + foundArticle.getViewcount());
	}

	private void search_word() {
		System.out.print("검색할 게시글의 키워드를 입력하세요 : ");
		String word = sc.nextLine().trim();
		boolean found = false;

		System.out.println(" 번호 |  제목  | 조회수 | 등록일자   ");

		for (Article item : articles) {
			if (item.getTitle().contains(word)) {
				found = true;
				System.out.printf(" %-5d| %-6.6s |  %-4d  | %8s\n", item.getId(), item.getTitle(), item.getViewcount(),
						item.getDate());
			}
		}
		if (!found) {
			System.out.println("해당 관련 제목의 게시글이 없습니다.");
		}
	}

	private void delete() {
		System.out.println("삭제할 게시글의 게시글의 번호를 입려해주세요.");
		String idstr = sc.nextLine().trim();
		int id = Integer.parseInt(idstr);

		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다");
			return;
		}

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다");
			return;
		}

		if (foundArticle.getId() != loginedUser.getNum()) {
			System.out.println("해당 게시물에 대한 권한이 없습니다");
			return;
		}

		articles.remove(foundArticle);

		System.out.println(id + "번 게시물이 삭제되었습니다");
	}

	private String getLoginIdByUserId(int id) {
		for (User user : users) {
			String strid = String.valueOf(id);
			if (strid == user.getId()) {
				return user.getId();
			}
		}
		return null;
	}

	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	@Override
	public void makeTestData() {
		if (articles.size() <= 3) {
			System.out.println("테스트데이터를 생성했습니다");

			for (int i = 1; i <= 3; i++) {
				articleService.writeArticle((int) (Math.random() * 3) + 1, "제목" + i, "내용" + i, i * 10);
			}
		}
	}
}