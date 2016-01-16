package exam;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import service.board.dao.exam.template.BoardJdbcDao;
import service.board.domain.Board;


public class BoardTest {
	public static void main(String args[]){
		
		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		ApplicationContext context = new GenericXmlApplicationContext("/config/application-context.xml");
		
		BoardJdbcDao boardJdbcDao = (BoardJdbcDao)context.getBean("boardJdbcDao",BoardJdbcDao.class);
		
		Board board = new Board();
		board.setBoardTitle("Title");
		board.setBoardContents("Contents");
		board.setWriter("conWriter");
		board.setOrderNum(1);
		
		boardJdbcDao.deleteAllBoard();
		
		System.out.println(boardJdbcDao.insertBoard(board)==1 ? "등록성공" : "등록실패");
		
		List<Board> list = boardJdbcDao.selectBoard(new Board());
		
		System.out.println("조회 건수 : "+boardJdbcDao.getCount());
		
		Board board1 = boardJdbcDao.selectBoard(list.get(0).getSeqNum());
		
		System.out.println("조회 일련번호 : "+board1.getSeqNum());
		
		//업데이트 테스트
		board.setBoardTitle("update Title");
		board.setSeqNum(board1.getSeqNum());
		boardJdbcDao.updateBoard(board);
		Board board2 = boardJdbcDao.selectBoard(board1.getSeqNum());
		
		System.out.println(board.getBoardTitle().equals(board2.getBoardTitle()) ? "수정성공" : "수정실패");
		
		//삭제 테스트
		boardJdbcDao.deleteBoard(board1.getSeqNum());
		Board board3 = boardJdbcDao.selectBoard(board1.getSeqNum());
		
		System.out.println(board3 == null ? "삭제성공" : "식제실패");
		
	}
}
