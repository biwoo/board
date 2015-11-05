package service.board.biz.impl;

import java.util.List;

import service.board.biz.BoardService;
import service.board.dao.BoardDao;
import service.board.domain.Board;

public class BoardServiceImpl implements BoardService{
	
	private BoardDao boardDao = null;
	
	public void setBoardDao(BoardDao boardDao){
		this.boardDao = boardDao;
	}
		
	public List<Board> selectBoard(Board conBoard){
		return boardDao.selectBoard(conBoard);
	}
	

	public Board selectBoard(int seqNum){
		return boardDao.selectBoard(seqNum);
	}
	

	public int insertBoard(Board board){
		return boardDao.insertBoard(board);
	}

	public int updateBoard(Board board){
		return boardDao.updateBoard(board);
	}

	public int deleteBoard(int seqNum){
		return boardDao.deleteBoard(seqNum);
	}

	public int deleteAllBoard(){
		return boardDao.deleteAllBoard();
	}

	public int getCount(){
		return boardDao.getCount();
	}
	
}
