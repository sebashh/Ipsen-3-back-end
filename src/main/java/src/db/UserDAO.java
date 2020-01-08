package src.db;


import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import src.Mapper.LoginMapper;
import src.api.User;
import src.core.LoginModel;
import src.core.UserModel;

import java.util.List;

/**
 * This class request the database for certain user data using SQL statements
 * The class uses a SQL Object API to replace the necessary prepared statements
 * With the @bind command certain values can be inserted into the SQL queries
 * This secures the database against certain SQL injections
 */
@RegisterRowMapper(LoginMapper.class)
public interface UserDAO {

    @SqlQuery("SELECT * FROM person")
    List<UserModel> getAllUsers();

    @SqlQuery("SELECT exists(SELECT 1 FROM person)")
    boolean anyUserExists();

    @SqlQuery("SELECT * FROM person WHERE user_id = :id")
    UserModel getUserById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM person WHERE username = :username AND pass = :password")
    UserModel getUserByLogin(@Bind("username")String username, @Bind("password") String password);

    @SqlQuery("SELECT user_id FROM person ORDER BY user_id DESC limit 1")
    int getLastUserId();

    @SqlUpdate("INSERT INTO person(user_id,username,pass,student,teacher, admin) SELECT :id, :username, :password, :student, :teacher , :admin " +
            "WHERE NOT EXISTS (SELECT username FROM person WHERE username = :username)")
    void registerUser(@Bind("id")int id, @Bind("username")String username, @Bind("password")String password,
                      @Bind("student")boolean student, @Bind("teacher")boolean teacher, @Bind("admin")boolean admin);

    @SqlQuery("SELECT EXISTS(SELECT * FROM person WHERE username =:username)")
    Boolean checkUserExistence(@Bind("email") String email);

    @SqlQuery("SELECT * FROM person WHERE email = :email")
    User getUserByEmail(@Bind("email") String email);

    @SqlQuery("SELECT * FROM person WHERE password = :password")
    LoginModel getUserPassword(@Bind("password") String password);

    @SqlUpdate("INSERT INTO person(username, pass) VALUES(:user, :password)")
        String register(@Bind("user") String username, @Bind("pass") String password);
}

