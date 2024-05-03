import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		// 스캐너 생성
		Scanner sc = new Scanner(System.in);

		HashMap<Integer, ArrayList<String>> articles = new HashMap<>();

		// 무한 루프 생성
		while (true) {

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
					for (int i = 0; i <= articles.size(); i++) {
						ArrayList<String> item = articles.get(i);
						System.out.println((i + 1) + ": 제목-" + item.get(0) + " 내용-" + item.get(1));
					}
				}
			} else if (cmd.equals("등록")) {
				System.out.printf("제목 : ");
				String name = sc.nextLine().trim();
				System.out.printf("내용 : ");
				String text = sc.nextLine().trim();
				// nextLine().trim()을 사용하면 사용자 입력에서 앞뒤 공백을 제거

				ArrayList<String> item = new ArrayList<>();
				item.add(name);
				item.add(text);
				articles.put(articles.size() + 1, item);

				System.out.println(articles.size() + "번 글이 생성되었습니다.");

			} else if (cmd.isEmpty()) {
				System.out.println("아무것도 입력하지 않았습니다.");
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		System.out.println("== 프로그램 종료 ==");
	}
}