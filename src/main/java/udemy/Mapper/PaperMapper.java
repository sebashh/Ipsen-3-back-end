package udemy.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import udemy.core.models.Paper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaperMapper implements RowMapper<Paper> {
    @Override
    public Paper map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Paper(rs.getInt("id"),rs.getString("title"),rs.getString("author"), rs.getInt("uploaded_by"), rs.getString("upload_date"),rs.getString("pdf_location"));
    }

}