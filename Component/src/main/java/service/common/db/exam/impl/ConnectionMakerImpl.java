package service.common.db.exam.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import service.common.db.exam.ConnectionMaker;

public class ConnectionMakerImpl implements ConnectionMaker{

	public Connection getConnection(){
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/springboard", "board", "1111");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 실패");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
		return conn;
		
	}
	
}
