import java.util.Scanner;
import java.time.*;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		LocalDate currentDate = LocalDate.now();

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
			System.out.println("명령어 목록 : 등록, 목록, 조회, 수정, 삭제, 종료 ");
			System.out.print("명령어) ");
			String cmd = sc.nextLine().trim();

			try {
				if (cmd.equals("종료")) {
					sc.close();
					break;
				} else if (cmd.equals("목록")) {
					if (articles.isEmpty()) {
						System.out.println("존재하는 게시글이 없습니다");
					} else {
						System.out.println(" 번호 |  제목  | 조회수 | 등록일자   ");
						for (int i = articles.size() - 1; i >= 0; i--) {
							Article item = articles.get(i);
							System.out.printf(" %-5d| %-6.6s |  %-4d  | %8s\n", item.id, item.title, item.viewcount,
									item.date);
							// 여기서 -는 좌측정렬 -없으면 우측정렬. 다음 숫자는 최소 할당칸. 소수점 숫자는 출력될 값의 길이 제한
						}
					}
				} else if (cmd.equals("등록")) {
					System.out.print("제목 : ");
					String title = sc.nextLine().trim();
					System.out.print("내용 : ");
					String body = sc.nextLine().trim();
					String date = currentDate.toString();

					Article article = new Article(lastArticleId, title, body, date, viewcount);
					articles.add(article);
					System.out.println(lastArticleId + "번 글이 생성되었습니다");
					lastArticleId++;
				} else if (cmd.equals("조회")) {
					System.out.print("조회할 게시글의 번호 : ");
					String idStr = sc.nextLine().trim();
					try {
						int id = Integer.parseInt(idStr);
						boolean found = false;
						for (Article article : articles) {
							if (article.id == id) {
								found = true;
								article.incviewcount();
								System.out.println("번호: " + article.id);
								System.out.println("제목: " + article.title);
								System.out.println("내용: " + article.body);
								System.out.println("날짜: " + article.date);
								System.out.println("조회수: " + article.viewcount);
								break;
							}
						}
						if (!found) {
							System.out.println("해당 번호의 게시글이 없습니다.");
						}
					} catch (NumberFormatException e) {
						System.out.println("올바른 번호를 입력하세요.");
					}
				} else if (cmd.equals("수정")) {
					System.out.print("수정할 게시글의 번호를 입력하세요: ");
					String idStr = sc.nextLine().trim();
					try {
						int id = Integer.parseInt(idStr);
						boolean found = false;
						for (Article article : articles) {
							if (article.id == id) {
								found = true;
								System.out.print("수정할 제목: ");
								article.title = sc.nextLine().trim();
								System.out.print("수정할 내용: ");
								article.body = sc.nextLine().trim();
								article.date = currentDate.toString();
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
				} else if (cmd.equals("삭제")) {
					System.out.print("삭제할 게시글의 번호를 입력하세요: ");
					String idStr = sc.nextLine().trim();
					try {
						int id = Integer.parseInt(idStr);
						boolean found = false;
						for (Article article : articles) {
							if (article.id == id) {
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

class Article {
	int id;
	String title;
	String body;
	String date;
	int viewcount;

	Article(int id, String title, String body, String date, int viewcount) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.date = date;
		this.viewcount = viewcount;
	}

	void incviewcount() {
		viewcount++;
	}
}