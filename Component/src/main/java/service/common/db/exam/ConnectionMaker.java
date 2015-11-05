package service.common.db.exam;

import java.sql.Connection;

public interface ConnectionMaker {
	public Connection getConnection();
}
