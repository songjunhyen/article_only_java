package com.article.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.article.dto.User;

public class Usermanager {
	private String currentUserId;

	public void setCurrentUserId(String userId) {
		this.currentUserId = userId;
	}

	public String getCurrentUserId() {
		return this.currentUserId;
	}

	public void signin(Scanner sc, ArrayList<User> users) {
		System.out.print("아이디 : ");
		String id = sc.nextLine().trim();
		System.out.print("비밀번호 : ");
		String password = sc.nextLine().trim();
		boolean check = false;
		for (User user : users) {
			if (user.getId().equals(id) && user.getPassword().equals(password)) {
				check = true;
				setCurrentUserId(id); // 로그인 성공시 현재 유저 아이디 설정
				break;
			}
		}
		if (check) {
			System.out.println(id+"님 로그인 되었습니다.");
		} else {
			System.out.println("등록된 회원이 없거나 아이디 또는 비밀번호가 잘못되었습니다.");
		}
	}

	public void signup(Scanner sc, ArrayList<User> users, int num) {
		while (true) {
			System.out.print("생성할 유저 아이디 : ");
			String id = sc.nextLine().trim();

			// 아이디 체크 메서드
			if (!checkId(sc, users, id)) {
				System.out.println("사용 중이거나 유효하지 않은 아이디입니다. 다시 입력해주세요.");
				continue;
			}

			System.out.print("비밀번호 : ");
			String password = sc.nextLine().trim();

			System.out.print("비밀번호 확인 : ");
			String pass2 = sc.nextLine().trim();

			if (!password.equals(pass2)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				return;
			}

			System.out.print("이름 : ");
			String name = sc.nextLine().trim();

			User user = new User(id, password, num, name);
			users.add(user);
			System.out.println(num + "번 회원님 환영합니다.");
			num++;
			break; // 아이디가 중복되지 않은 경우에만 회원가입 진행
		}
	}

	public void updateuser(Scanner sc, ArrayList<User> users) {
		signcheck();
		String inputPassword = checkPassword(sc, users);
		if (!inputPassword.equals("")) {
			for (User user : users) {
				if (user.getId().equals(currentUserId)) {
					System.out.print("수정할 이름: ");
					user.setName(sc.nextLine().trim());
					System.out.print("수정할 비밀번호: ");
					user.setPassword(sc.nextLine().trim());
					System.out.println(currentUserId + "님의 계정이 수정되었습니다.");
					return;
				}
			}
			System.out.println("해당 유저를 찾을 수 없습니다.");
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
	}

	public void deleteuser(Scanner sc, ArrayList<User> users) {
		signcheck();
		String inputPassword = checkPassword(sc, users);
		if (!inputPassword.equals("")) {
			System.out.print("계정을 삭제하시겠습니까? (Y/N): ");
			String answer = sc.nextLine().trim().toUpperCase();

			if (answer.equals("Y")) {
				// 현재 유저 아이디와 일치하는 유저를 삭제합니다.
				for (User user : users) {
					if (user.getId().equals(currentUserId)) {
						users.remove(user);
						System.out.println(currentUserId + "님의 계정이 삭제되었습니다.");
						return;
					}
				}
				System.out.println("해당 유저를 찾을 수 없습니다.");
			} else {
				System.out.println("계정 삭제가 취소되었습니다.");
			}
		} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
	}

	public void signout() {
		currentUserId = null; // 사용자 정보 초기화
		System.out.println("로그아웃 되었습니다.");
		return;
	}

	// 아이디 체크 메서드
	private boolean checkId(Scanner sc, ArrayList<User> users, String id) {
		if (id.isEmpty()) {
			System.out.println("아이디를 입력해주세요.");
			return false; // 공백임
		}

		for (User user : users) {
			if (user.getId().equals(id)) {
				return false; // 중복된 아이디가 있음
			}
		}
		return true; // 중복된 아이디가 없음
	}

	// 비번 체크 메서드
	private String checkPassword(Scanner sc, ArrayList<User> users) {
		while (true) {
			System.out.print("비밀번호 확인: ");
			String inputPassword = sc.nextLine().trim();

			// 저장된 비밀번호와 비교
			boolean passwordMatched = false;
			for (User user : users) {
				if (user.getPassword().equals(inputPassword)) {
					passwordMatched = true;
					break;
				}
			}

			if (passwordMatched) {
				return inputPassword;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			}
		}
	}

	// 로그인 확인
	public boolean signcheck() {
		if (currentUserId == null) {
			System.out.println("로그인이 필요합니다.");
			return false;
		}
		return true;
	}
}
