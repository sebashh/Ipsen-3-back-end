package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import udemy.Mapper.ClientMapper;
import udemy.core.models.Client;

import java.util.List;

@RegisterRowMapper(ClientMapper.class)
public interface ClientDAO {

    @SqlQuery("select * from \"User\" inner join client on id = client.user_id")
    List<Client> getAllClients();



}
