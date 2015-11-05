package service.common.db.exam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import service.board.dao.exam.template.BoardJdbcDao;
import service.common.db.exam.impl.ConnectionMakerImpl;

@Configuration
public class DaoFactory {

	@Bean
	public BoardJdbcDao boardJdbcDao(){
		return new BoardJdbcDao();
	}
	
	@Bean
	public ConnectionMaker getConnection(){
		return new ConnectionMakerImpl();
	}
	
	
	
}
