package udemy.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import udemy.core.models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category map(ResultSet resultSet, StatementContext statementContext) throws SQLException{
        return new Category(resultSet.getString("name"));
    }

}

