package com.article;

import com.article.controller.Usermanager;
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

        Usermanager usermanager = new Usermanager(sc, users, num,lastArticleId);

        while (true) {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            System.out.print("선택) ");
            String cmd = sc.nextLine().trim();

            try {
                usermanager.doing(cmd, articles, currentDate, viewcount, lastArticleId);
            } catch (Exception e) {
                System.out.println("오류가 발생하였습니다: " + e.getMessage());
            }
        }
    }
}