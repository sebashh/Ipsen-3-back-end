package udemy.Mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import udemy.core.models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {
    @Override
    public Client map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Client(rs.getInt("id"),rs.getString("email"),rs.getString("password"), rs.getString("last_login"),
                rs.getString("company_name"), rs.getString("description"), rs.getBytes("picture"));
    }

}