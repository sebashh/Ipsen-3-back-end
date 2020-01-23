package udemy.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import udemy.core.models.Category;
import udemy.core.models.Interest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InterestMapper implements RowMapper<Interest> {

    @Override
    public Interest map(ResultSet resultSet, StatementContext statementContext) throws SQLException{
        return new Interest(resultSet.getString("email"), resultSet.getInt("category_id"));
    }

}

