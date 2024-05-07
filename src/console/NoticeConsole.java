package console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import entity.Notice;
import service.NoticeService;

public class NoticeConsole {
	
	
	private NoticeService noticeService;
	
	public NoticeConsole() {
		this.noticeService = new NoticeService();
	}

	public void printNoticeList() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = noticeService.getList(0);
		
		System.out.println("----------------------------------------");
		System.out.printf("<공지사항> 총 %d 게시글\n", 12);
		System.out.println("----------------------------------------");
		
		for(Notice n : list) {
			System.out.printf("%d. %s / %s / %tF\n", n.getId(), n.getTitle(), n.getWriterId(),n.getRegDate());
			
		}
		
		System.out.println("----------------------------------------");
		System.out.printf("             %d/%d pages\n", 1,2);
	}

	public int inputNoticeMenu() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("1.상세조회 / 2.이전 / 3.다음 / 4.글쓰기 / 5.종료");
		int input = Integer.parseInt(in.next());
		
		return input;
	}
}
