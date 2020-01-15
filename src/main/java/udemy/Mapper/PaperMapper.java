package udemy.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import udemy.User;
import udemy.core.models.PaperModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaperMapper implements RowMapper<PaperModel> {
    @Override
    public PaperModel map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new PaperModel(resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("pdf_location"), resultSet.getInt("project_id"));
    }
}
