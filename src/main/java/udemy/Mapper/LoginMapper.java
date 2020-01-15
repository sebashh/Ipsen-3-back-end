package udemy.Mapper;
        import org.jdbi.v3.core.mapper.RowMapper;
        import org.jdbi.v3.core.statement.StatementContext;
        import udemy.User;

        import java.sql.ResultSet;
        import java.sql.SQLException;

/**
 * This class is used to map the resultset of the obtained usersfrom the database to a User model
 * Rowmapper is a jdbi module used to replace the manual mapping of the resultset to a User model
 */
public class LoginMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet, StatementContext statementContext) throws SQLException{
        return new User(resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("userrole"));
    }

}
