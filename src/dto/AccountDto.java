package dto;


// DTO(Data Transfer Object)
public class AccountDto {
	
	// 하나의 가계부
	private String date;		
	private String title;
	private String in_ex;
	private int pay;
	private String memo;
	
	public AccountDto() {
		
	}
	// 생성자
	public AccountDto(String date, String title, String in_ex, int pay, String memo) {
		super();
		this.date = date;
		this.title = title;
		this.in_ex = in_ex;
		this.pay = pay;
		this.memo = memo;
	}

	// getter and setter
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIn_ex() {
		return in_ex;
	}

	public void setIn_ex(String in_ex) {
		this.in_ex = in_ex;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	// toString
	@Override
	public String toString() {
		return "|| 날짜 = " + date + " || 제목 = " + title + " || 수입/지출 = " + in_ex + " || 금액 = " + pay + 
				" || 메모 = " + memo + " ||";
	}
	
	// 기록 
	public String print() {
		return date + "-" + title + "-" + in_ex + "-" + pay + "-" + memo;
	}
	
}