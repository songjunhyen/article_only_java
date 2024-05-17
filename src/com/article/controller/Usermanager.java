package com.article.controller;

import java.util.List;
import java.util.Scanner;

import com.article.container.Container;
import com.article.dto.User;
import com.article.service.UserService;

public class Usermanager extends Controller {

	private List<User> users;
	private UserService userService;

	public Usermanager(Scanner sc) {
		this.sc = sc;
		this.users = Container.users;
		this.userService = new UserService();
		loginedUser = null;
	}

	public void doing(String cmd) {
		makeTestData();
		switch (cmd) {
		case "1":
		case "로그인":
			if (signin()) {
				while (true) {
					System.out.println("명령어 목록 :\n 등록, 목록, 조회, 검색, 수정, 삭제 \n 정보수정, 탈퇴, 로그아웃 ");
					System.out.print("명령어) ");
					String cmd2 = sc.nextLine().trim();
					try {
						switch (cmd2) {
						case "정보수정":
							updateuser();
							break;
						case "탈퇴":
							deleteuser();
							signout();
							break; // 로그아웃 또는 탈퇴한 경우 while 루프를 종료함
						case "로그아웃":
							signout();
							break; // 로그아웃 또는 탈퇴한 경우 while 루프를 종료함
						default:
							Articlecontroller articleController = new Articlecontroller(sc);
							articleController.doing(cmd2);
						}
					} catch (Exception e) {
						System.out.println("오류가 발생하였습니다: " + e.getMessage());
					}
					if (cmd2.equals("로그아웃") || cmd2.equals("탈퇴")) {
						break; // 로그아웃 또는 탈퇴 시 while 루프 종료
					}
				}
			}
			break; // 로그인 후에 명령어 처리를 마치고 외부 switch 문으로 돌아감
		case "2":
		case "회원가입":
			signup();
			break;
		case "3":
		case "종료":
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
			break;
		default:
			System.out.println("올바른 명령어를 입력해주세요");
			break;
		}
	}

	private boolean signin() {
		System.out.print("아이디 : ");
		String id = sc.nextLine().trim();
		System.out.print("비밀번호 : ");
		String password = sc.nextLine().trim();
		User foundUser = getUserByLoginId(id, users);

		if (foundUser == null) {
			System.out.println("존재하지 않는 아이디 입니다");
			return false;
		}

		if (!foundUser.getPassword().equals(password)) {
			System.out.println("비밀번호를 확인해주세요");
			return false;
		}

		loginedUser = foundUser;
		System.out.println("로그인 성공!");
		return true;
	}

	private void signup() {
		String id = null;
		String pw = null;
		String pwChk = null;
		String name = null;
		while (true) {
			System.out.printf("아이디 : ");
			id = sc.nextLine().trim();

			if (id.length() == 0) {
				System.out.println("아이디는 필수 입력정보입니다");
				continue;
			}

			if (userService.loginIdDupChk(id) == false) {
				System.out.println("[" + id + "] 은(는) 이미 사용중인 아이디입니다");
				continue;
			}

			System.out.println("[" + id + "] 은(는) 사용가능한 아이디입니다");
			break;
		}

		while (true) {
			System.out.printf("비밀번호 : ");
			pw = sc.nextLine().trim();

			if (pw.length() == 0) {
				System.out.println("비밀번호는 필수 입력정보입니다");
				continue;
			}

			System.out.printf("비밀번호 확인 : ");
			pwChk = sc.nextLine().trim();

			if (pw.equals(pwChk) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}

		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();

			if (name.length() == 0) {
				System.out.println("이름은 필수 입력정보입니다");
				continue;
			}
			break;
		}

		userService.joinUser(id, pw, name);

		System.out.println("[" + id + "] 회원님의 가입이 완료되었습니다");
	}

	private void updateuser() {
		System.out.print("새 이름: ");
		String name = sc.nextLine().trim();
		System.out.print("새 비밀번호: ");
		String password = sc.nextLine().trim();
		String id = loginedUser.getId();
		userService.updateUser(id, name, password);
	}

	private void deleteuser() {
		String id = loginedUser.getId();
        userService.deleteUser(id);
	}

	private void signout() {
		loginedUser = null;
		System.out.println("로그아웃!");
	}
	

	@Override
	public void makeTestData() {
		if (users.size() < 3) {
			System.out.println("테스트용 회원 데이터를 생성했습니다");

			for (int i = 1; i <= 3; i++) {
				userService.joinUser("user" + i, "user" + i, "유저" + i);
			}
		}
	}
}