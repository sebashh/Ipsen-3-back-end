package udemy.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import udemy.core.models.ExtendThisUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<ExtendThisUser> {

    @Override
    public ExtendThisUser map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new ExtendThisUser(rs.getInt("id"),rs.getString("email"),rs.getString("password"), rs.getString("last_login"));
    }
}
