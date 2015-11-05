package service.board.dao.exam.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import service.board.dao.BoardDao;
import service.board.dao.exam.template.JdbcContext;
import service.board.dao.exam.template.RowMapperStrategy;
import service.board.domain.Board;

public class BoardJdbcDao implements BoardDao{
	
	private JdbcContext jdbcContext = null;

	public void setJdbcContext(JdbcContext jdbcContext){
		this.jdbcContext = jdbcContext;
	}
		
	public List<Board> selectBoard(Board board){
		
		String query = "SELECT * FROM tbl_board WHERE 1=1";
		query += " AND board_title like '%'||?||'%'";
		query += " AND board_contents like '%'||?||'%'";
		
		return jdbcContext.executeQuery(query, new RowMapperStrategy<Board>(){
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException{
				
				Board board = new Board();

				board.setSeqNum(rs.getInt("seq_num") );
				board.setBoardTitle(rs.getString("board_title") );
				board.setBoardContents(rs.getString("board_contents"));
				board.setWriter(rs.getString("writer"));
				board.setInputTime(rs.getString("input_time"));
				board.setParentSeqNum(rs.getInt("parent_seq_num"));
				board.setOrderNum(rs.getInt("group_num"));
				
				return board;
			}
		}, 
		board.getBoardTitle(), 
		board.getBoardContents());
		
	}
	
	public Board selectBoard(int seqNum){
		
		String query = "SELECT * FROM tbl_board WHERE seq_num = ? ";
		
		return jdbcContext.executeObject(query,new RowMapperStrategy<Board>(){
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException{
				
				Board board = new Board();
				
				board.setSeqNum(rs.getInt("seq_num") );
				board.setBoardTitle(rs.getString("board_title") );
				board.setBoardContents(rs.getString("board_contents"));
				board.setWriter(rs.getString("writer"));
				board.setInputTime(rs.getString("input_time"));
				board.setParentSeqNum(rs.getInt("parent_seq_num"));
				board.setOrderNum(rs.getInt("group_num"));
				
				return board;
			}
		}, seqNum);
	}
	
	@SuppressWarnings("unchecked")
	public int insertBoard(Board board){
		
		String query = "INSERT INTO tbl_board(board_title, board_contents, writer, parent_seq_num, order_num) VALUES(?, ?, ?, ?, ?)";
		
		return jdbcContext.executeUpdate(query,
				board.getBoardTitle(),
				board.getBoardContents(),
				board.getWriter(),
				board.getParentSeqNum(),
				board.getOrderNum());
		
	}

	@SuppressWarnings("unchecked")
	public int updateBoard(Board board){
		
		String query = "";
		query += "UPDATE tbl_board ";
		query += " SET board_title = ?, board_contents = ?, writer = ? ";
		query += " WHERE seq_num = ? ";
		query += "";
		
		return jdbcContext.executeUpdate(query,
				board.getBoardTitle(), 
				board.getBoardContents(), 
				board.getWriter(),
				board.getSeqNum());
	}
	public int deleteBoard(int seqNum){
		return jdbcContext.executeUpdate(" DELETE FROM tbl_board WHERE seq_num = ? ",seqNum);
	}
	
	public int deleteAllBoard(){
		return jdbcContext.executeUpdate("DELETE FROM tbl_board");
	}
	
	public int getCount(){
		String query = "SELECT count(*) FROM tbl_board";
		
		return jdbcContext.executeObject(query,new RowMapperStrategy<Integer>(){
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
				return rs.getInt(1);
			}
		});
	}
	
}
