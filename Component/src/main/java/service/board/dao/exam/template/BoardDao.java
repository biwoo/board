package service.board.dao.exam.template;

import java.util.List;

import service.board.domain.Board;

public interface BoardDao{
	
	
	public List<Board> selectBoard(Board board);
	
	
	public Board selectBoard(int seqNum);
	
	
	public int insertBoard(Board freeBoard);
	
	
	public int updateBoard(Board freeBoard);
	
	
	public int deleteBoard(int seqNum);
	
	
	public int deleteAllBoard();
	
	
	public int getCount();
}
