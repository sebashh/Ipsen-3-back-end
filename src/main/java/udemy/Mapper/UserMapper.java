package udemy.Mapper;

import udemy.core.models.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to map the resultset of the obtained usersfrom the database to a User model
 * Rowmapper is a jdbi module used to replace the manual mapping of the resultset to a User model
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet, StatementContext statementContext) throws SQLException{
        return new User(resultSet.getInt("user_id"),resultSet.getString("username"), resultSet.getString("pass")
        , resultSet.getBoolean("student"),resultSet.getBoolean("teacher"), resultSet.getBoolean("admin"));
    }

}
