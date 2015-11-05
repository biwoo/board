package service.board.dao;

import java.util.List;

import service.board.domain.Board;

public interface BoardDao{
	
	public List<Board> selectBoard(Board board);
	
	public Board selectBoard(int seqNum);
	
	public int insertBoard(Board board);
	
	public int updateBoard(Board board);
	
	public int deleteBoard(int seqNum);
	
	public int deleteAllBoard();
	
	public int getCount();
}
