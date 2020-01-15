package udemy.persistance;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.core.models.UserModel;

public interface RegisterDAO {

    @SqlUpdate("INSERT INTO person(username,pass) SELECT :email, :password" +
            "WHERE NOT EXISTS (SELECT username FROM person WHERE username = :username)")
    UserModel registerUser(@Bind("username") String username, @Bind("password") String password);
}
