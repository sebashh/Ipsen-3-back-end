package udemy.persistance;


import com.udemy.Mapper.UserMapper;
import com.udemy.core.models.User;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(UserMapper.class)
public interface UserDAO {

    @SqlQuery("SELECT * FROM person")
    List<User> getAllUsers();

    @SqlQuery("SELECT exists(SELECT 1 FROM person)")
    boolean anyUserExists();

    @SqlQuery("SELECT * FROM person WHERE user_id = :id")
    User getUserById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM person WHERE username = :username AND pass = :password")
    User getUserByLogin(@Bind("username") String username, @Bind("password") String password);

    @SqlQuery("SELECT user_id FROM person ORDER BY user_id DESC limit 1")
    int getLastUserId();

    @SqlUpdate("INSERT INTO person(user_id,username,pass,student,teacher) SELECT :id, :username, :password, :student, :teacher " +
            "WHERE NOT EXISTS (SELECT username FROM person WHERE username = :username)")
    void registerUser(@Bind("id") int id, @Bind("username") String username, @Bind("password") String password,
                      @Bind("student") boolean student, @Bind("teacher") boolean teacher);

    @SqlQuery("SELECT EXISTS(SELECT 1 FROM person WHERE username =:username)")
    Boolean checkUserExistence(@Bind("username") String username);

}

