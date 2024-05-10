package com.article;

import com.article.controller.Usermanager;
import com.article.controller.Controller;
import com.article.dto.Article;
import com.article.dto.User;
import java.util.Scanner;
import java.time.*;
import java.util.ArrayList;

public class App {
	void Run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		LocalDate currentDate = LocalDate.now();
		Controller controller = new Controller();
		Usermanager usermanager = new Usermanager();

		ArrayList<Article> articles = new ArrayList<>();
		ArrayList<User> users = new ArrayList<>();

		int lastArticleId = 1;
		int num = 1;
		int viewcount = 0;

		System.out.println("테스트 데이터 생성");
		for (int i = 1; i < 4; i++) {
			Article article = new Article(lastArticleId, "제목" + i, "내용" + i, currentDate.toString(), i * 10);
			articles.add(article);
			lastArticleId++;

			User user = new User("id" + i, "password" + i, i * 10, "이름" + i);
			users.add(user);
			num++;
		}

		while (true) {
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");
			System.out.print("선택) ");
			String cmd = sc.nextLine().trim();

			try {
				if (cmd.equals("1") || cmd.equals("로그인")) {
					usermanager.signin(sc, users);
					while (true) {
						System.out.println("명령어 목록 :\n 등록, 목록, 조회, 검색, 수정, 삭제, 종료 \n 정보수정, 탈퇴, 로그아웃 ");
						System.out.print("명령어) ");
						String cmd2 = sc.nextLine().trim();

						try {
							if (cmd2.equals("종료")) {
								sc.close();
								return;
							} else if (!usermanager.signcheck()) { // 로그인 확인
								break; // 로그인이 안되어있으면 다시 로그인 화면으로 돌아감
							} else if (cmd2.equals("목록")) {
								controller.index(articles);
							} else if (cmd2.equals("등록")) {
								controller.write(sc, articles, viewcount, currentDate, viewcount);
							} else if (cmd2.equals("조회")) {
								controller.search_num(sc, articles);
							} else if (cmd2.equals("수정")) {
								controller.change(sc, articles, currentDate);
							} else if (cmd2.equals("삭제")) {
								controller.delete(sc, articles);
							} else if (cmd2.equals("검색")) {
								controller.search_word(sc, articles);
							} else if (cmd2.equals("정보수정")) {
								usermanager.updateuser(sc, users);
							} else if (cmd2.equals("탈퇴")) {
								usermanager.deleteuser(sc, users);
								usermanager.signout();
								break; // 로그아웃 또는 탈퇴한 경우 while 루프를 종료함
							} else if (cmd2.equals("로그아웃")) {
								usermanager.signout();
								break; // 로그아웃 또는 탈퇴한 경우 while 루프를 종료함
							} else {
								System.out.println("존재하지 않는 명령어입니다.");
							}
						} catch (Exception e) {
							System.out.println("오류가 발생하였습니다: " + e.getMessage());
						}
					}
				} else if (cmd.equals("2") || cmd.equals("회원가입")) {
					usermanager.signup(sc, users, num);
				} else if (cmd.equals("3") || cmd.equals("종료")) {
					System.out.println("프로그램을 종료합니다.");
					return;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("오류가 발생하였습니다: " + e.getMessage());
			}
		}
	}
}