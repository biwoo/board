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
		
		System.out.println(boardJdbcDao.insertBoard(board)==1 ? "��ϼ���" : "��Ͻ���");
		
		List<Board> list = boardJdbcDao.selectBoard(new Board());
		
		System.out.println("��ȸ �Ǽ� : "+boardJdbcDao.getCount());
		
		Board board1 = boardJdbcDao.selectBoard(list.get(0).getSeqNum());
		
		System.out.println("��ȸ �Ϸù�ȣ : "+board1.getSeqNum());
		
		//������Ʈ �׽�Ʈ
		board.setBoardTitle("update Title");
		board.setSeqNum(board1.getSeqNum());
		boardJdbcDao.updateBoard(board);
		Board board2 = boardJdbcDao.selectBoard(board1.getSeqNum());
		
		System.out.println(board.getBoardTitle().equals(board2.getBoardTitle()) ? "��������" : "��������");
		
		//���� �׽�Ʈ
		boardJdbcDao.deleteBoard(board1.getSeqNum());
		Board board3 = boardJdbcDao.selectBoard(board1.getSeqNum());
		
		System.out.println(board3 == null ? "��������" : "��������");
		
	}
}
