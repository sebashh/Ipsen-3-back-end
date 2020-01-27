package udemy.Mapper;

import org.jdbi.v3.core.statement.StatementContext;
import udemy.core.models.DateStatistic;

import org.jdbi.v3.core.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DateStatisticMapper implements RowMapper<DateStatistic> {

    @Override
    public DateStatistic map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new DateStatistic(rs.getString("days"),rs.getInt("total"));
    }
}
