package com.article;

import com.article.controller.Usermanager;
import java.util.Scanner;

public class App {
	void Run() {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		Usermanager usermanager = new Usermanager(sc);

		while (true) {
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");
			System.out.print("선택) ");
			String cmd = sc.nextLine().trim();

			try {
				usermanager.doing(cmd);
			} catch (Exception e) {
				System.out.println("오류가 발생하였습니다: " + e.getMessage());
			}
		}
	}
}
