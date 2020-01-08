package src.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import src.core.LoginModel;
import java.sql.SQLException;
import java.sql.ResultSet;

public class LoginMapper implements RowMapper<LoginModel> {
    @Override
    public LoginModel map (ResultSet rs, StatementContext ctx) throws SQLException {
        return new LoginModel(rs.getString("username"), rs.getString("pass"));
    }
}
