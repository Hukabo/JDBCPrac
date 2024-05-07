package console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import entity.Notice;
import service.NoticeService;

public class NoticeConsole {
	
	
	private NoticeService noticeService;
	private int currentPage;
	private String searchWord;
	private String searchField;
	
	public NoticeConsole() {
		this.noticeService = new NoticeService();
		this.currentPage = 1;
		this.searchField = "";
		this.searchWord = "";
	}

	public void printNoticeList() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = noticeService.getList(currentPage - 1);
		int count = noticeService.getCount();
		int lastPage = (count % 10) == 0 ? count / 10 : (count / 10) + 1;
		
		System.out.println("----------------------------------------");
		System.out.printf("<<         공지사항> 총 %d 게시글          >>\n", count);
		System.out.println("----------------------------------------");
		
		for(Notice n : list) {
			System.out.printf("%d. %s / %s / %tF\n", n.getId(), n.getTitle(), n.getWriterId(),n.getRegDate());
			
		}
		
		System.out.println("----------------------------------------");
		System.out.printf("             %d/%d pages\n", currentPage, lastPage);
	}

	public int inputNoticeMenu() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("1.상세조회 / 2.이전 / 3.다음 / 4.글쓰기 / 5.검색 / 6.종료");
		int input = Integer.parseInt(in.next());
		
		return input;
	}
	
	public void prevPage() {
		if(currentPage == 1) {
			System.out.println("====================");
			System.out.println("이전 페이지가 없습니다.");
			System.out.println("====================");
			return;
		} else {
			currentPage--;
		}
	}
	
	public void nextPage() throws SQLException { 
		
		int count = noticeService.getCount();
		int lastPage = (count % 10) == 0 ? count / 10 : (count / 10) + 1;
		
		if(currentPage < lastPage) {
			currentPage++;
		} else {
			System.out.println("====================");
			System.out.println("마지막 페이지입니다.");
			System.out.println("====================");
		}
		
	}
 
	public void inputSearchWord() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("검색 범주(title/content/writerId)중에 하나를 입력하세요.");
		System.out.print(">");
		searchField = in.nextLine();
		
		System.out.println("검색 주(title/content/writerId)중에 하나를 입력하세요.");
		System.out.print("검색어 >");
		searchWord = in.nextLine();
	}
}
