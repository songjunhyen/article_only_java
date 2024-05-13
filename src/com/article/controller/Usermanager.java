package com.article.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.article.dto.Article;
import com.article.dto.User;

public class Usermanager {
	private String currentUserId;
    private Scanner sc;
    private ArrayList<User> users;
	private int num;
	private int lastArticleId;
	private Controller controller;
	
	public Usermanager(Scanner sc, ArrayList<User> users, int num, int lastArticleId) {
	    this.sc = sc;
	    this.users = users;
	    this.num = num;
	    this.lastArticleId = lastArticleId;
	    this.controller = new Controller(sc, lastArticleId);
	}
	
    public void setCurrentUserId(String userId) {
        this.currentUserId = userId;
    }

    public String getCurrentUserId() {
        return this.currentUserId;
    }
    

    public void doing(String cmd, ArrayList<Article> articles, LocalDate currentDate, int viewcount, int lastArticleId2) {
		if (cmd.equals("1") || cmd.equals("로그인")) {
			signin(sc, users);
			while (true) {
				System.out.println("명령어 목록 :\n 등록, 목록, 조회, 검색, 수정, 삭제 \n 정보수정, 탈퇴, 로그아웃 ");
				System.out.print("명령어) ");
				String cmd2 = sc.nextLine().trim();
				try {
					 if (!signcheck()) { // 로그인 확인
						break; // 로그인이 안되어있으면 다시 로그인 화면으로 돌아감
					} else if (cmd2.equals("정보수정")) {
						updateuser(sc, users);
					} else if (cmd2.equals("탈퇴")) {
						deleteuser(sc, users);
						signout();
						break; // 로그아웃 또는 탈퇴한 경우 while 루프를 종료함
					} else if (cmd2.equals("로그아웃")) {
						signout();
						break; // 로그아웃 또는 탈퇴한 경우 while 루프를 종료함
					} else {
						controller.doing(cmd2, articles, currentDate, viewcount);
					}
				} catch (Exception e) {
					System.out.println("오류가 발생하였습니다: " + e.getMessage());
				}
			}
		} else if (cmd.equals("2") || cmd.equals("회원가입")) {
			signup(sc, users, num);
		} else if (cmd.equals("3") || cmd.equals("종료")) {
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}	
	}
    

	private void signin(Scanner sc, ArrayList<User> users) {
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
			System.out.println(id + "님 로그인 되었습니다.");
		} else {
			System.out.println("등록된 회원이 없거나 아이디 또는 비밀번호가 잘못되었습니다.");
		}
	}

	private void signup(Scanner sc, ArrayList<User> users, int num) {
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
			this.num++;
			break; // 아이디가 중복되지 않은 경우에만 회원가입 진행
		}
	}


	private void updateuser(Scanner sc, ArrayList<User> users) {
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

	private void deleteuser(Scanner sc, ArrayList<User> users) {
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

	private void signout() {
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
	private boolean signcheck() {
		if (currentUserId == null) {
			System.out.println("로그인이 필요합니다.");
			return false;
		}
		return true;
	}

}
