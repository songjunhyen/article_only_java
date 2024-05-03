import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		//스캐너 생성
		Scanner sc = new Scanner(System.in);
		
		//무한 루프 생성
		while(true) {
			/*
			//입력받고 버림 
			sc.nextLine();
			*/
			
			//문자열 출력
			System.out.print("명령어) ");
			//문자열 타입 cmd 변수에 입력받은 값을 저장
			String cmd = sc.nextLine();
			//cmd 출력
			System.out.println(cmd);
			
			//무한 루프 종료 조건을 생성
			//조건문 사용
			if(cmd.equals("종료")) { //cmd 가 "종료"와 일치하는지 확인
				sc.close(); //스캐너 닫기. 안닫으면 계속 리소스 사용됨
				break; //반복문 종료
			}
		}
		
		
		System.out.println("== 프로그램 종료 ==");
	}
}
