package service.board.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import service.board.dao.BoardDao;
import service.board.domain.Board;

public class BoardJdbcDao implements BoardDao{
	
	private JdbcTemplate jdbcTemplate = null;

	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	public List<Board> selectBoard(Board board){
		
		String query = "SELECT * FROM tbl_board WHERE 1=1";
		query += " AND board_title like '%'||?||'%'";
		query += " AND board_contents like '%'||?||'%'";
		
		return jdbcTemplate.query(query, 
				new Object[]{board.getBoardTitle(),board.getBoardContents()}, 
				new RowMapper<Board>(){
					public Board mapRow(ResultSet rs, int rowNum) throws SQLException{
					
					Board board = new Board();
	
					board.setSeqNum(rs.getInt("seq_num") );
					board.setBoardTitle(rs.getString("board_title") );
					board.setBoardContents(rs.getString("board_contents"));
					board.setWriter(rs.getString("writer"));
					board.setInputTime(rs.getString("input_time"));
					board.setParentSeqNum(rs.getInt("parent_seq_num"));
					board.setOrderNum(rs.getInt("order_num"));
					
					return board;
					
				}
		});
		
	}
	
	public Board selectBoard(int seqNum){
		
		String query = "SELECT * FROM tbl_board WHERE seq_num = ? ";
		
		return jdbcTemplate.queryForObject(query, 
				new Object[]{seqNum},
				new RowMapper<Board>(){
					public Board mapRow(ResultSet rs, int rowNum) throws SQLException{
					
					Board board = new Board();
					
					board.setSeqNum(rs.getInt("seq_num") );
					board.setBoardTitle(rs.getString("board_title") );
					board.setBoardContents(rs.getString("board_contents"));
					board.setWriter(rs.getString("writer"));
					board.setInputTime(rs.getString("input_time"));
					board.setParentSeqNum(rs.getInt("parent_seq_num"));
					board.setOrderNum(rs.getInt("order_num"));
					
					return board;
				}
		});
	}
	
	public int insertBoard(Board board){
		
		String query = "INSERT INTO tbl_board(board_title, board_contents, writer, parent_seq_num, order_num) VALUES(?, ?, ?, ?, ?)";
		
		return jdbcTemplate.update(query,
				board.getBoardTitle(),
				board.getBoardContents(),
				board.getWriter(),
				board.getParentSeqNum(),
				board.getOrderNum());
		
	}

	public int updateBoard(Board board){
		
		String query = "";
		query += "UPDATE tbl_board ";
		query += " SET board_title = ?, board_contents = ?, writer = ? ";
		query += " WHERE seq_num = ? ";
		query += "";
		
		return jdbcTemplate.update(query,
				board.getBoardTitle(), 
				board.getBoardContents(), 
				board.getWriter(),
				board.getSeqNum());
	}
	public int deleteBoard(int seqNum){
		return jdbcTemplate.update(" DELETE FROM tbl_board WHERE seq_num = ? ",seqNum);
	}
	
	public int deleteAllBoard(){
		return jdbcTemplate.update("DELETE FROM tbl_board");
	}
	
	public int getCount(){
		return jdbcTemplate.queryForInt("SELECT count(*) FROM tbl_board");
	}
	
}
