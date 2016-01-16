package junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.board.biz.BoardService;
import service.board.domain.Board;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/config/application-context.xml")
public class BoardUnitTest {
	
	private Board board;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private BoardService boardService;
	
	@Before
	public void setUp(){
		
		board = new Board();
		board.setBoardTitle("Title");
		board.setBoardContents("Contents");
		board.setWriter("conWriter");
		board.setOrderNum(1);
	}
	
	@Test
	public void insertBoard() throws Exception{
		
		boardService.deleteAllBoard();
		boardService.insertBoard(board);
		
		List<Board> list = boardService.selectBoard(new Board());
		Board board1 = boardService.selectBoard(list.get(0).getSeqNum());
		
		assertThat(board1.getBoardTitle(), is(board.getBoardTitle()));
	}
	
	@Test
	public void updateBoard() throws Exception{
		
		boardService.deleteAllBoard();
		boardService.insertBoard(board);
		
		List<Board> list = boardService.selectBoard(new Board());
		Board board1 = boardService.selectBoard(list.get(0).getSeqNum());
		
		board.setBoardTitle("update Title");
		board.setSeqNum(board1.getSeqNum());
		boardService.updateBoard(board);
		
		Board board2 = boardService.selectBoard(list.get(0).getSeqNum());
		
		assertThat(board2.getBoardTitle(), is(board.getBoardTitle()));
	}
	
	@Test
	public void deleteBoard() throws Exception{
				
		boardService.deleteAllBoard();
		boardService.insertBoard(board);
		
		List<Board> list = boardService.selectBoard(new Board());

		//삭제 테스트
		boardService.deleteBoard(list.get(0).getSeqNum());
				
		assertThat(boardService.getCount(), is(0));
		
	}
		
}
