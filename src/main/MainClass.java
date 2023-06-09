package main;

import java.util.Scanner;

import dao.AccountDao;
import file.FileProc;

public class MainClass {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);		
		AccountDao dao = new AccountDao();
		FileProc fp = new FileProc("addressbook");
		
		// file read 호출
		fp.read();
		
		// 메뉴
		while(true) {
			boolean end = false; // 종료 변수
			System.out.println("----- AccountBook 프로그램 -----");
			System.out.println("1. 가계부 추가");
			System.out.println("2. 가계부 삭제");
			System.out.println("3. 가계부 검색");
			System.out.println("4. 가계부 수정");
			System.out.println("5. 가계부 전체 출력");
			System.out.println("6. 가계부 월별 결산");
			System.out.println("7. 가계부 기간 결산");
			System.out.println("8. 가계부 저장");
			System.out.println("9. 가계부 종료");
			
			System.out.print("메뉴 번호를 선택하세요. >> ");
			int menuNumber = sc.nextInt();
			
			switch(menuNumber)
			{
				case 1:
					dao.insert();
					break;
				case 2:	
					dao.delete();
					break;
				case 3:
					dao.select();
					break;
				case 4:
					dao.update();
					break;
				case 5:
					dao.allDataPrint();
					break;
				case 6:
					dao.monthResult();
					break;
				case 7:
					dao.termResult();
					break;
				case 8:
					fp.write();
					break;
				case 9:
					end = true;
					break;
			}
			// case7 -> 종료
			if (end == true) {
				break;
			}
		}	
	}

}