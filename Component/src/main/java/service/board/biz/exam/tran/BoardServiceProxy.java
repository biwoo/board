package service.board.biz.exam.tran;

import java.util.List;

import service.board.biz.BoardService;
import service.board.dao.BoardDao;
import service.board.domain.Board;

public class BoardServiceProxy implements BoardService{
	
	private BoardDao boardDao = null;

	public void setBoardDao(BoardDao boardDao){
		this.boardDao = boardDao;
	}

	/**
	 * selectBoard 게시글 목록 조회
	 * @param conBoard
	 * @return
	 */
	public List<Board> selectBoard(Board conBoard){
		return boardDao.selectBoard(conBoard);
	}
	
	/**
	 * selectBoard 게시글 상세조회
	 * @param seqNum
	 * @return
	 */
	public Board selectBoard(int seqNum){
		return boardDao.selectBoard(seqNum);
	}
	
	/**
	 * insertBoard 게시글 등록
	 * @param board
	 * @return
	 */
	public int insertBoard(Board board){
		return boardDao.insertBoard(board);
	}
	
	/**
	 * updateBoard 게시글 수정
	 * @param board
	 * @return
	 */
	public int updateBoard(Board board){
		return boardDao.updateBoard(board);
	}
	/**
	 * deleteContent 계시글 삭제
	 * @param seqNum : 삭제 결과
	 * @return
	 */
	public int deleteBoard(int seqNum){
		return boardDao.deleteBoard(seqNum);
	}
	
	/**
	 * deleteAllBoard 게시글 전체 삭제
	 * @return
	 */
	public int deleteAllBoard(){
		return boardDao.deleteAllBoard();
	}
	
	
	/**
	 * getCount 게시글 건수 조회
	 * @return
	 */
	public int getCount(){
		return boardDao.getCount();
	}
	
}
