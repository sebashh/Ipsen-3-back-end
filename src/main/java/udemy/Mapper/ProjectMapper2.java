package udemy.Mapper;

import udemy.core.models.Project2;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to map the resultset of the obtained projects from the database to a Project2 model
 * However the difference is that this mapper also includes the project file which represents the PDF files
 * Rowmapper is a jdbi module used to replace the manual mapping of the resultset to a Project2 model
 */
public class ProjectMapper2 implements RowMapper<Project2> {
    @Override
    public Project2 map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Project2(rs.getInt("project_id"), rs.getString("author"), rs.getString("title"),
                rs.getString("category"), rs.getString("upload_date"), rs.getInt("likes"),
                rs.getString("study"), rs.getString("course"), rs.getInt("uploaded_by"), rs.getBytes("project_file"));
    }

}


