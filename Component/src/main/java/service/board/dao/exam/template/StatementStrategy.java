package service.board.dao.exam.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	public PreparedStatement makeStatement(Connection con) throws SQLException;
}
