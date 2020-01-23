package udemy.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import udemy.core.models.Study;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyMapper implements RowMapper<Study> {

    @Override
    public Study map(ResultSet resultSet, StatementContext statementContext) throws SQLException{
        return new Study(resultSet.getString("name"));
    }

}

