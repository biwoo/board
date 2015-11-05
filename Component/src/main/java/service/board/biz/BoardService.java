package service.board.biz;

import java.util.List;

import service.board.domain.Board;

public interface BoardService {
	/**
	 * selectBoard 게시글 목록 조회
	 * @param conBoard
	 * @return
	 */
	public List<Board> selectBoard(Board conBoard);
	
	/**
	 * selectBoard 게시글 상세조회
	 * @param seqNum
	 * @return
	 */
	public Board selectBoard(int seqNum);
	
	/**
	 * insertBoard 게시글 등록
	 * @param board
	 * @return
	 */
	public int insertBoard(Board board);
	
	/**
	 * updateBoard 게시글 수정
	 * @param board
	 * @return
	 */
	public int updateBoard(Board board);
	
	/**
	 * deleteBoard 게시글 삭제
	 * @param seqNum
	 * @return
	 */
	public int deleteBoard(int seqNum);
	
	/**
	 * deleteAllBoard 게시글 전체 삭제
	 * @return
	 */
	public int deleteAllBoard();
	
	/**
	 * getCount 게시글 건수 조회
	 * @return
	 */
	public int getCount();
}
