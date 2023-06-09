package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.AccountDto;
import singleton.Singleton;

// DAO(Data Access Object)
public class AccountDao {
	// 데이터를 편집하는 클래스
	
	// composition
	private Scanner sc = new Scanner(System.in);
	
	public AccountDao() {		
	
	}
	
	/* 1. 삽입 */
	public void insert() {
		// TODO: insert()
		System.out.println("[가계부 삽입]");
		
		System.out.print("날짜 ex)2023.06.03 = ");
		String date = sc.next();
		
		System.out.print("용도(제목) = ");
		String title = sc.next();
		
		System.out.print("수입/지출 = ");
		String in_ex = sc.next();
		
		System.out.print("금액 = ");
		int pay = sc.nextInt();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		System.out.print("내용 = ");
		String memo = "";
		try {
			memo = br.readLine();
		} catch (IOException e) { e.printStackTrace(); }
		
		Singleton s = Singleton.getInstance();
		s.addressList.add(new AccountDto(date, title, in_ex, pay, memo));		
	}
	
	/* 2. 삭제 */
	public void delete() {	
		System.out.println("[가계부 삭제]");
		
		// 날짜, 제목 완벽히 일치해야 삭제
		System.out.print("삭제할 가계부 날짜를 입력하세요 ex)2023.06.09: ");
		String date = sc.next();
		
		System.out.print("1. 모두 삭제 \n2. 선택 삭제\n메뉴 번호를 입력하세요. >>");
		int select_menu = sc.nextInt(); // 제목 or 날짜 선택 번호
		
		// 2-1. 해당 날짜 전체 삭제 
		if (select_menu == 1) {
			Singleton s = Singleton.getInstance();
			
			ArrayList<Integer> index = new ArrayList<>(); // 인덱스 기록 리스트
			
			// 날짜가 같은 인덱스들만 찾기
			for (int i = 0; i < s.addressList.size(); i++) {
				AccountDto dto = s.addressList.get(i);
				if(date.equals(dto.getDate())) {
					index.add(i); // 모든 인덱스 기록
				}
			}
			
			// 찾을 수 없음
			if(index.size() == 0) {
				System.out.println("해당 날짜의 가계부를 찾을 수 없습니다.");
				return;
			}
			
			// 해당 index들의 가계부 내역을 모두 삭제
			else {
				for (int i = 0; i< index.size();i++) {
					s.addressList.remove(i);
				}
			}
			System.out.println("해당 날짜의 가계부 내역을 삭제했습니다.");
		}
		
		// 2-2. 해당 날짜 선택 삭제
		else if (select_menu == 2) {
			System.out.print("해당 날짜의 삭제할 제목을 입력하세요: ");
			String title = sc.next();
			int index = search(date, title);
		
			if(index == -1) {
				System.out.println("해당 날짜의 가계부를 찾을 수 없습니다.");
			}
			
			else {
				// 삭제
				Singleton s = Singleton.getInstance();
				s.addressList.remove(index);
				System.out.println("해당 날짜의 가계부 내역을 삭제했습니다.");
			}
		}
		
		// 2-3. 번호 잘못 입력
		else {
			System.out.println("번호를 잘못 입력하셨습니다.");
			return;
		}
		
		
	}
	
	/* 3. 검색 */
	public void select() {	// 제목으로 검색하는데 제목안에 특정단어가 포함되면 검색 ex) 데이트비용 -> 데이트만 검색해도 가능하게
		System.out.print("1. 제목으로 검색 \n2. 날짜로 검색\n메뉴 번호를 입력하세요. >>");
		int select_menu = sc.nextInt(); // 제목 or 날짜 선택 번호
		
		// 3-1. 제목으로 검색
		if (select_menu == 1) {
			System.out.print("검색 하고자하는 제목을 입력하세요: ");
			String title = sc.next();
			
			Singleton s = Singleton.getInstance();
			
			List<AccountDto> findNameList = new ArrayList<AccountDto>();		
			for (int i = 0; i < s.addressList.size(); i++) {
				AccountDto dto = s.addressList.get(i);
				if(dto.getTitle().contains(title)) { // 입력한 문자열이 title안에 포함되어 있으면
					findNameList.add(dto);
				}
			}
			
			if(findNameList.size() == 0) {
				System.out.println("검색한 내용을 찾을 수 없습니다.");
				return;
			}		
			
			System.out.println("--- 검색된 가계부입니다. --- ");
			for (int i = 0; i < findNameList.size(); i++) {
				AccountDto dto = findNameList.get(i);
				System.out.println(dto.toString());
			}			
		}
		
		// 3-2. 날짜로 검색
		else if (select_menu == 2) {
			System.out.print("검색 하고자하는 날짜를 입력하세요 ex)2023.06.09: ");
			String date = sc.next();
			
			Singleton s = Singleton.getInstance();
			
			List<AccountDto> findNameList = new ArrayList<AccountDto>();		
			for (int i = 0; i < s.addressList.size(); i++) {
				AccountDto dto = s.addressList.get(i);
				if(date.equals(dto.getDate())) { // 날짜가 완벽히 일치하면
					findNameList.add(dto);
				}
			}
			
			if(findNameList.size() == 0) {
				System.out.println("검색한 내용을 찾을 수 없습니다.");
				return;
			}		
			
			System.out.println("--- 검색된 가계부입니다. --- ");
			for (int i = 0; i < findNameList.size(); i++) {
				AccountDto dto = findNameList.get(i);
				System.out.println(dto.toString());
			}			
		}
		
		// 3-3. 번호 잘못입력
		else {
			System.out.println("번호를 잘못 입력하셨습니다.");
			return;
		}
		
	}
	
	/* 4. 수정 */
	public void update() {	
		
		// 수정할 것을 찾을떄 날짜, 제목 모두 일치해야 수정
		System.out.print("수정할 가계부의 날짜 >> ");
		String search_date = sc.next();
		
		System.out.print("수정할 가계부의 제목(용도) >> ");
		String search_title = sc.next();
		
		
		// 검색
		int index = search(search_date, search_title);
		if(index == -1) {
			System.out.println("해당 가계부 정보를 찾을 수 없습니다");
			return;
		}
		
		// 수정
		System.out.print("수정할 날짜 >> ");
		String date = sc.next();
		
		System.out.print("수정할 제목 >> ");
		String title = sc.next();
		
		System.out.print("수입/지출 >> ");
		String in_ex = sc.next();
		
		System.out.print("금액 >> ");
		int pay = sc.nextInt();
		
		System.out.print("수정 내용 >> ");
		String memo = sc.next();
		
		Singleton s = Singleton.getInstance();
		
		AccountDto dto = s.addressList.get(index);
		dto.setDate(date);
		dto.setTitle(title);
		dto.setIn_ex(in_ex);
		dto.setPay(pay);
		dto.setMemo(memo);
				
		System.out.println("수정을 완료했습니다");
	}
	
	/* index search */
	public int search(String date, String title) {
		
		Singleton s = Singleton.getInstance();
		
		int index = -1;
		// 날짜, 제목 모두 일치해야 찾기
		for (int i = 0; i < s.addressList.size(); i++) {
			AccountDto dto = s.addressList.get(i);
			if(title.equals(dto.getTitle()) && date.equals(dto.getDate())) {
				index = i;
				break;
			}
		}		
		return index;
	}
	
	/* 5. 가계부 모두 출력 */
	public void allDataPrint() {
		// TODO: allDataPrint
		
		Singleton s = Singleton.getInstance();
		
		// 비어있을 경우
		if(s.addressList.isEmpty()) {	
			System.out.println("가계부 데이터가 없습니다");
			return;
		}
		
		// 데이터가 존재할 경우 
		for (int i = 0; i < s.addressList.size(); i++) {
			System.out.println(s.addressList.get(i).toString());
		}
		
	}
	
	/* 6. 월별 결산 */
	public void monthResult() {
		System.out.print("결산할 년도를 입력하세요 : ");
		int year = sc.nextInt();
		System.out.print("결산할 월을 입력하세요 : ");
		int month = sc.nextInt();
		
		int income = 0; // 수입
		int expenditure = 0; // 지출
		
		Singleton s = Singleton.getInstance();
		List<AccountDto> findMonthList = new ArrayList<>();
		
		// 월 일치 데이터 찾기
		for (int i = 0; i < s.addressList.size(); i++) {
			AccountDto dto = s.addressList.get(i);
			String date[] = dto.getDate().split("\\."); // .으로 구분
			
			// index 0:년 1:월 2:일
			if (Integer.parseInt(date[0]) == year && Integer.parseInt(date[1]) == month) {
				if(dto.getIn_ex().equals("수입")) {
					income += dto.getPay();
				}
				else if(dto.getIn_ex().equals("지출")) {
					expenditure += dto.getPay();
				}
				findMonthList.add(dto);
			}
		}
		// 해당 월 없을 경우 
		if(findMonthList.isEmpty()) {	
			System.out.println("해당 월 데이터가 없습니다");
			return;
		}
		
		System.out.println("[결산 결과]");
	    System.out.println("결산 월: " + year + "년 " + month + "월");
	    System.out.println("총 수입액: " + income);
	    System.out.println("총 지출액: " + expenditure);
	    System.out.println("해당 월 잔액: " + (income - expenditure));
	    System.out.println("[결산 상세 내역]");
        for (AccountDto dto : findMonthList) {
            System.out.println(dto.toString());
        }
	}
	
	/* 7. 기간별 결산 */
	public void termResult() {
		
		System.out.print("결산 시작 년도를 입력하세요: ");
	    int startYear = sc.nextInt();
	    System.out.print("결산 시작 월을 입력하세요: ");
	    int startMonth = sc.nextInt();
	    System.out.print("결산 시작 일을 입력하세요: ");
	    int startDay = sc.nextInt();
	    
	    System.out.print("결산 종료 년도를 입력하세요: ");
	    int endYear = sc.nextInt();
	    System.out.print("결산 종료 월을 입력하세요: ");
	    int endMonth = sc.nextInt();
	    System.out.print("결산 종료 일을 입력하세요: ");
	    int endDay = sc.nextInt();

	    int income = 0; // 수입
	    int expenditure = 0; // 지출

	    Singleton s = Singleton.getInstance();
	    List<AccountDto> findTermList = new ArrayList<>();

	    // 기간 일치 데이터 찾기
	    for (int i = 0; i < s.addressList.size(); i++) {
			AccountDto dto = s.addressList.get(i);
			String date[] = dto.getDate().split("\\."); // .으로 구분

	        // index 0:년 1:월 2:일
	        int year = Integer.parseInt(date[0]);
	        int month = Integer.parseInt(date[1]);
	        int day = Integer.parseInt(date[2]); 

	        // case.1 시작년도 or 종료년도 사이에 존재하는 경우 --> 바로 pass
	        // case.2 시작년도와 같은데 월이 더 큰경우 + 끝나는년도와 같고 월이 더 작아야함
	        // case.3 시작년도같고 월까지 같으면 일이 커야함 + 끝나는년도와 같고 월까지 같으면 일이 더 작아야함 (일은 같을수도 있음)
	        if ((year > startYear || (year == startYear && month > startMonth) || 
	        	(year == startYear && month == startMonth && day >= startDay)) && // start end 구분점
	            (year < endYear || (year == endYear && month < endMonth) || 
	            (year == endYear && month == endMonth && day <= endDay))) {
	            if (dto.getIn_ex().equals("수입")) {
	                income += dto.getPay();
	            } 
	            else if (dto.getIn_ex().equals("지출")) {
	                expenditure += dto.getPay();
	            }
	            findTermList.add(dto);
	        }
	    }

	    // 해당 기간 없을 경우
	    if (findTermList.isEmpty()) {
	        System.out.println("해당 기간 데이터가 없습니다");
	        return;
	    }

	    System.out.println("[결산 결과]");
	    System.out.println("결산 기간: " + startYear + "년 " + startMonth + "월" +"-"+ endYear + "년 " + endMonth + "월");
	    System.out.println("총 수입: " + income);
	    System.out.println("총 지출: " + expenditure);
	    System.out.println("잔액: " + (income - expenditure));
	    System.out.println("[결산 상세 내역]");
        for (AccountDto dto : findTermList) {
            System.out.println(dto.toString());
        }
	}
}
