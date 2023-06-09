package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dto.AccountDto;
import singleton.Singleton;

public class FileProc {

	private File file = null;
	
	public FileProc(String filename) {
		file = new File("C:\\Users\\교육생38\\Desktop\\최민규\\" + filename + ".txt");
		
		try {
			if(file.createNewFile()) {
				System.out.println("[파일 생성 성공]");
			}else {
				System.out.println("[파일 이미 존재]");
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	public void write() {
		
		Singleton s = Singleton.getInstance();
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < s.addressList.size(); i++) {
				AccountDto dto = s.addressList.get(i); 
				pw.println(dto.print());
			}
			
			pw.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		
		System.out.println("[파일 저장 완료]");
	}
	
	public void read() {
		Singleton s = Singleton.getInstance();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String str = "";
			while((str = br.readLine()) != null) {
				// 잘라줘야 함
				// 날짜-용도-수입/지출-금액-내용
				String split[] = str.split("-"); 
				AccountDto dto = new AccountDto(split[0], split[1], split[2], Integer.parseInt(split[3]), split[4]);
				s.addressList.add(dto);
			}	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[파일 로드 완료]");
	}
	
}