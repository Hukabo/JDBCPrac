package ex1;

import java.sql.SQLException;

import console.NoticeConsole;

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		NoticeConsole console = new NoticeConsole();
		
		
		
		EXIT:while(true) {
			console.printNoticeList();
			int menu = console.inputNoticeMenu();
			
			switch(menu) {
			case 1: // 상세조회
				break;
			case 2: // 이전
				console.prevPage();
				break;
			case 3: // 다음
				console.nextPage();
				break;
			case 4: // 글쓰기
				break;
			case 5:
				console.inputSearchWord();
				break;
			case 6: // 종료
				System.out.println("Bye");
				break EXIT;
			default:
				System.out.println("<<메뉴는 1~6번까지 있습니다.(1.상세조회 / 2. 이전 / 3. 다음 / 4.글쓰기 / 5.검색 / 6.종료)>>");
				break;
			}
		}
		
	}
}
