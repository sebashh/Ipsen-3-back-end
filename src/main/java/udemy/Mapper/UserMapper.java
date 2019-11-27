package udemy.Mapper;

import com.udemy.core.models.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet, StatementContext statementContext) throws SQLException{
        return new User(resultSet.getInt("user_id"),resultSet.getString("username"), resultSet.getString("pass")
        , resultSet.getBoolean("student"),resultSet.getBoolean("teacher"), resultSet.getBoolean("admin"));
    }

}
