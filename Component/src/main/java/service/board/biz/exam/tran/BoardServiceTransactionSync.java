package service.board.biz.exam.tran;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import service.board.dao.BoardDao;
import service.board.domain.Board;

public class BoardServiceTransactionSync {
	
	private BoardDao boardDao = null;

	private DataSource dataSource;
	
	public void setBoardDao(BoardDao boardDao){
		this.boardDao = boardDao;
	}
		
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	public List<Board> selectBoard(Board conBoard){
		return boardDao.selectBoard(conBoard);
	}
	
	public Board selectBoard(int seqNum){
		return boardDao.selectBoard(seqNum);
	}
	
	public int insertBoard(Board board) throws Exception{
		
		TransactionSynchronizationManager.initSynchronization();
		Connection c = DataSourceUtils.getConnection(dataSource);
		
		int cnt = 0;
		
		try {
			c.setAutoCommit(false);
			
			cnt = boardDao.insertBoard(board);
			
			c.commit();
			
		} catch (SQLException e) {
			c.rollback();
			throw e;
		} finally{
			DataSourceUtils.releaseConnection(c, dataSource);
			TransactionSynchronizationManager.unbindResource(dataSource);
			TransactionSynchronizationManager.clearSynchronization();
		}
		
		return cnt;
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
