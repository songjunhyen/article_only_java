package com.article.controller;

import com.article.dto.Article;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Controller {

	public void index(ArrayList<Article> articles) {
		if (articles.isEmpty()) {
			System.out.println("존재하는 게시글이 없습니다");
		} else {
			System.out.println(" 번호 |  제목  | 조회수 | 등록일자   ");
			for (int i = articles.size() - 1; i >= 0; i--) {
				Article item = articles.get(i);
				System.out.printf(" %-5d| %-6.6s |  %-4d  | %8s\n", item.getId(), item.getTitle(), item.getViewcount(),
						item.getDate());
				// 여기서 -는 좌측정렬 -없으면 우측정렬. 다음 숫자는 최소 할당칸. 소수점 숫자는 출력될 값의 길이 제한
			}
		}
	}

	public void write(Scanner sc, ArrayList<Article> articles, int lastArticleId, LocalDate currentDate,
			int viewcount) {
		System.out.print("제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("내용 : ");
		String body = sc.nextLine().trim();
		String date = currentDate.toString();

		Article article = new Article(lastArticleId, title, body, date, viewcount);
		articles.add(article);
		System.out.println(lastArticleId + "번 글이 생성되었습니다");
		lastArticleId++;
	}

	public void search_num(Scanner sc, ArrayList<Article> articles) {
		System.out.print("조회할 게시글의 번호 : ");
		String idStr = sc.nextLine().trim();
		try {
			int id = Integer.parseInt(idStr);
			boolean found = false;
			for (Article article : articles) {
				if (article.getId() == id) {
					found = true;
					article.incviewcount();
					System.out.println("번호: " + article.getId());
					System.out.println("제목: " + article.getTitle());
					System.out.println("내용: " + article.getBody());
					System.out.println("날짜: " + article.getDate());
					System.out.println("조회수: " + article.getViewcount());
					break;
				}
			}
			if (!found) {
				System.out.println("해당 번호의 게시글이 없습니다.");
			}
		} catch (NumberFormatException e) {
			System.out.println("올바른 번호를 입력하세요.");
		}
	}

	public void search_word(Scanner sc, ArrayList<Article> articles) {
		System.out.print("검색할 게시글의 키워드를 입력하세요 : ");
		String word = sc.nextLine().trim();
		boolean found = false;

		System.out.println(" 번호 |  제목  | 조회수 | 등록일자   ");

		for (int i = articles.size() - 1; i >= 0; i--) {
			Article item = articles.get(i); // 한 객체씩 Article클래스의 item이란 객체에 담는다
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

	public void change(Scanner sc, ArrayList<Article> articles, LocalDate currentDate) {
		System.out.print("수정할 게시글의 번호를 입력하세요: ");
		String idStr = sc.nextLine().trim();
		try {
			int id = Integer.parseInt(idStr);
			boolean found = false;
			for (Article article : articles) {
				if (article.getId() == id) {
					found = true;
					System.out.print("수정할 제목: ");
					article.setTitle(sc.nextLine().trim());
					System.out.print("수정할 내용: ");
					article.setBody(sc.nextLine().trim());
					article.setDate(currentDate.toString());
					System.out.println(id + "번 게시글이 수정되었습니다.");
					break;
				}
			}
			if (!found) {
				System.out.println("해당 번호의 게시글이 없습니다.");
			}
		} catch (NumberFormatException e) {
			System.out.println("올바른 번호를 입력하세요.");
		}
	}

	public void delete(Scanner sc, ArrayList<Article> articles) {
		System.out.print("삭제할 게시글의 번호를 입력하세요: ");
		String idStr = sc.nextLine().trim();
		try {
			int id = Integer.parseInt(idStr);
			boolean found = false;
			for (Article article : articles) {
				if (article.getId() == id) {
					found = true;
					articles.remove(article);
					System.out.println(id + "번 게시글이 삭제되었습니다.");
					break;
				}
			}
			if (!found) {
				System.out.println("해당 번호의 게시글이 없습니다.");
			}
		} catch (NumberFormatException e) {
			System.out.println("올바른 번호를 입력하세요.");
		}
	}
}
