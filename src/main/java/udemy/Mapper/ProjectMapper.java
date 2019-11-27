package udemy.Mapper;

import com.udemy.core.models.Project;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {
    @Override
    public Project map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Project(rs.getInt("project_id"), rs.getString("author"),rs.getString("title"),
                rs.getString("category"),rs.getString("upload_date"),rs.getInt("likes"),
                rs.getString("study"),rs.getString("course"),rs.getInt("uploaded_by"),null);
    }

}
