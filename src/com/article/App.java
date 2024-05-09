package com.article;

import com.article.controller.Controller;
import com.article.dto.Article;
import java.util.Scanner;
import java.time.*;
import java.util.ArrayList;

public class App {
	void Run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		LocalDate currentDate = LocalDate.now();
		Controller controller = new Controller();

		ArrayList<Article> articles = new ArrayList<>();
		int lastArticleId = 1;
		int viewcount = 0;

		System.out.println("테스트 데이터 생성");
		for (int i = 1; i < 4; i++) {
			Article article = new Article(lastArticleId, "제목" + i, "내용" + i, currentDate.toString(), i * 10);
			articles.add(article);
			lastArticleId++;
		}

		while (true) {
			System.out.println("명령어 목록 : 등록, 목록, 조회, 검색, 수정, 삭제, 종료 ");
			System.out.print("명령어) ");
			String cmd = sc.nextLine().trim();

			try {
				if (cmd.equals("종료")) {
					sc.close();
					break;
				} else if (cmd.equals("목록")) {

					controller.index(articles);

				} else if (cmd.equals("등록")) {

					controller.write(sc, articles, viewcount, currentDate, viewcount);

				} else if (cmd.equals("조회")) {

					controller.search_num(sc, articles);

				} else if (cmd.equals("수정")) {

					controller.change(sc, articles, currentDate);

				} else if (cmd.equals("삭제")) {

					controller.delete(sc, articles);

				} else if (cmd.equals("검색")) {

					controller.search_word(sc, articles);

				} else {
					System.out.println("존재하지 않는 명령어입니다.");
				}
			} catch (Exception e) {
				System.out.println("오류가 발생하였습니다: " + e.getMessage());
			}
		}

		System.out.println("== 프로그램 종료 ==");
	}
}
