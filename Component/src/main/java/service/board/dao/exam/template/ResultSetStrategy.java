package service.board.dao.exam.template;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetStrategy<T> {
	public T extractData(ResultSet rs) throws SQLException;
}
