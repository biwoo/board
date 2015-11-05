package service.board.dao.exam.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JdbcContext {
	
	private DataSource dataSource = null;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public <T> int executeUpdate(String query, T... ts ){
		return this.jdbcContextWithStatementStrategy(new StatementStrategy(){
			public PreparedStatement makeStatement(Connection con) throws SQLException{
				
				PreparedStatement ps = con.prepareStatement(query);
				
				for(int i=0; i<ts.length; i++) {
					ps.setObject(i+1, ts[i]);	
				}
				
				return ps;
			}
		});
	}
	
	public <T,S> List<S> executeQuery(String query, final RowMapperStrategy<S> rowMapperStrategy, T... ts){
		return this.jdbcContextWithStatementStrategy(new StatementStrategy(){
			public PreparedStatement makeStatement(Connection con) throws SQLException{
				
				PreparedStatement ps = con.prepareStatement(query);
				
				for(int i=0; i<ts.length; i++) {
					ps.setObject(i+1, ts[i]);	
				}
				
				return ps;
			}
		}, new ResultSetStrategy<List<S>>(){
			public List<S> extractData(ResultSet rs) throws SQLException{
				
				List<S> list = new ArrayList<S>();
				
				int rowNum = 0;
				
				while(rs.next()){
					list.add(rowMapperStrategy.mapRow(rs, rowNum++));
				}
				
				return list;
			}
		});
	}
	
	public <T,S> S executeObject(String query, final RowMapperStrategy<S> rowMapperStrategy, T... ts){
		return this.jdbcContextWithStatementStrategy(new StatementStrategy(){
			public PreparedStatement makeStatement(Connection con) throws SQLException{
				
				PreparedStatement ps = con.prepareStatement(query);
				
				for(T t : ts) {
					ps.setObject(1, t);	
				}
				
				return ps;
			}
		}, new ResultSetStrategy<S>(){
			public S extractData(ResultSet rs) throws SQLException{
				if(rs.next()){
					return rowMapperStrategy.mapRow(rs,0);
				}else{
					return null;
				}
			}
		});
	}
	
	public Connection getConnection(){
		
		Connection con = null;
		
		try {
			con =  dataSource.getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
	
	public int jdbcContextWithStatementStrategy(StatementStrategy statementStrategy){
		
		int rtn = 0;
		
		Connection con = getConnection();
		
		PreparedStatement ps = null;
		
		try {
			
			ps = statementStrategy.makeStatement(con);
			
			rtn = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				
				if(ps != null){
					ps.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rtn;
	}
	
	public <T> T jdbcContextWithStatementStrategy(StatementStrategy statementStrategy
			, ResultSetStrategy<T> resultSetStrategy){
		
		T rtn = null;
		
		Connection con = getConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			
			ps = statementStrategy.makeStatement(con);
			
			rtn = resultSetStrategy.extractData(ps.executeQuery());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				
				if(ps != null){
					ps.close();
				}
				
				if(rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rtn;
	}
}
