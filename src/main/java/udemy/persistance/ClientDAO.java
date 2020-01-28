package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.ClientMapper;
import udemy.core.models.Client;

import java.util.List;

@RegisterRowMapper(ClientMapper.class)
public interface ClientDAO {

    @SqlQuery("select * from \"User\" inner join client on id = client.user_id")
    List<Client> getAllClients();

    @SqlUpdate("update client set company_name = :company_name, description = :description where user_id = :user_id")
    void updateClient(@Bind("user_id")int user_id,
                      @Bind("company_name")String company_name,
                      @Bind("description")String descripion);

}
