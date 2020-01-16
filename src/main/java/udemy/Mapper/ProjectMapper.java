package udemy.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import udemy.core.models.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {
    @Override
    public Project map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Project(rs.getInt("id"),rs.getString("title"),rs.getString("summary"),
                rs.getString("study_id"),rs.getString("category_id"),rs.getString("created_on"),rs.getInt("client_id"));
    }
}
