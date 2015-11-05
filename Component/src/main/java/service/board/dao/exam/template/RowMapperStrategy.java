package service.board.dao.exam.template;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapperStrategy<T> {
	public T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
