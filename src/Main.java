import java.util.Scanner;
import java.time.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		// 스캐너 생성
		Scanner sc = new Scanner(System.in);
		LocalDate currentDate = LocalDate.now();

		HashMap<Integer, ArrayList<String>> articles = new HashMap<>();

		// 무한 루프 생성
		while (true) {
			System.out.println("명령어 목록 : 등록, 목록, 조회, 종료 ");

			// 문자열 출력
			System.out.print("명령어) ");
			// 문자열 타입 cmd 변수에 입력받은 값을 저장
			String cmd = sc.nextLine();
			// cmd 출력
			System.out.println(cmd);

			// 무한 루프 종료 조건을 생성
			// 조건문 사용
			if (cmd.equals("종료")) { // cmd 가 "종료"와 일치하는지 확인
				sc.close(); // 스캐너 닫기. 안닫으면 계속 리소스 사용됨
				break; // 반복문 종료
			} else if (cmd.equals("목록")) {

				if (articles.isEmpty()) {
					System.out.println("게시글이 없습니다.");
				} else {
					System.out.println("번호 | 제목 | 등록일자");
					int count = articles.size();
					for (int i = count; i > 0; i--) {
						ArrayList<String> item = articles.get(i);
						if (item != null) { // null 체크
							System.out.println(i + "  |  " + item.get(0) + "  |  " + item.get(2));
						}
					}
				}
			} else if (cmd.equals("등록"))

			{
				System.out.printf("제목 : ");
				String name = sc.nextLine().trim();
				System.out.printf("내용 : ");
				String text = sc.nextLine().trim();

				// nextLine().trim()을 사용하면 사용자 입력에서 앞뒤 공백을 제거

				ArrayList<String> item = new ArrayList<>();
				item.add(name);
				item.add(text);
				item.add(currentDate.toString());
				articles.put(articles.size() + 1, item);

				System.out.println(articles.size() + "번 글이 생성되었습니다.");
			} else if (cmd.equals("조회")) {
				System.out.print("조회할 게시글의 번호 : ");
				String numberStr = sc.nextLine().trim();
				try {
					int number = Integer.parseInt(numberStr);
					if (articles.isEmpty()) {
						System.out.println("게시글이 없습니다.");
					} else if (articles.containsKey(number)) {
						ArrayList<String> items = articles.get(number);
						System.out.printf("번호 : %d\n날짜 : %s\n제목 : %s\n내용: %s\n", number, items.get(2), items.get(0),
								items.get(1));
					} else {
						System.out.printf("%d번 게시글이 존재하지 않습니다.\n", number);
					}
				} catch (NumberFormatException e) {
					System.out.println("올바른 번호를 입력하세요.");
				}
			} else if (cmd.equals("수정")) {
				String numberStr = sc.nextLine().trim();
				int number = Integer.parseInt(numberStr);
				try {
					if (articles.isEmpty()) {
						System.out.println("게시글이 없습니다.");
					} else if (articles.containsKey(number)) {
						ArrayList<String> items = articles.get(number);
						System.out.printf("수정할 제목 : %s => ", items.get(0));
						String new_name = sc.nextLine().trim();
						System.out.printf("수정할 내용 : %s => ", items.get(1));
						String new_text = sc.nextLine().trim();
						items.set(0, new_name);
						items.set(1, new_text);
						items.set(2, currentDate.toString());
						articles.put(number, items);
						System.out.printf("%d번 게시글이 수정되었습니다.\n", number);

					} else {
						System.out.printf("%d번 게시글이 존재하지 않습니다.\n", number);
					}
				} catch (NumberFormatException e) {

				}
			} else if (cmd.equals("삭제")) {
				String numberStr = sc.nextLine().trim();
				int number = Integer.parseInt(numberStr);
				try {
					if (articles.isEmpty()) {
						System.out.println("게시글이 없습니다.");
					} else if (articles.containsKey(number)) {
						ArrayList<String> items = articles.get(number);
						System.out.printf("게시글 삭제 - \n번호 : %d\n날짜 : %s\n제목 : %s\n", number, items.get(2), items.get(0));
						articles.remove(number);
						System.out.printf("%d번 게시글이 삭제되었습니다.\n", number);
						// 삭제한 번호보다 큰 번호들을 하나씩 앞으로 이동
						for (int i = number + 1; i <= articles.size() + 1; i++) {
							ArrayList<String> item = articles.get(i);
							articles.put(i - 1, item);
							articles.remove(i);
						}
					} else {
						System.out.printf("%d번 게시글이 존재하지 않습니다.\n", number);
					}
				} catch (NumberFormatException e) {

				}
			} else if (cmd.isEmpty()) {
				System.out.println("아무것도 입력하지 않았습니다.");
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}

		}

		System.out.println("== 프로그램 종료 ==");
	}
}