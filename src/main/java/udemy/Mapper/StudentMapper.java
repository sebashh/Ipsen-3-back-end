package udemy.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
//import udemy.core.models.Student;
import udemy.core.models.StudentUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<StudentUser> {
    @Override
    public StudentUser map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new StudentUser(rs.getInt("id"),rs.getString("email"),rs.getString("password"), rs.getString("last_login"));
    }

}