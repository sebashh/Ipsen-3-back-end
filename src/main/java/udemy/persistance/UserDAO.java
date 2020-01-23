package udemy.persistance;


import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.LoginMapper;
import udemy.Mapper.ProjectMapper;
import udemy.User;
import udemy.core.models.LoginModel;
import udemy.core.models.Project;
import udemy.core.models.UserModel;

import java.util.Date;
import javassist.bytecode.ByteArray;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

/**
 * This class request the database for certain user data using SQL statements
 * The class uses a SQL Object API to replace the necessary prepared statements
 * With the @bind command certain values can be inserted into the SQL queries
 * This secures the database against certain SQL injections
 */
@RegisterRowMapper(LoginMapper.class)
@RegisterRowMapper(ProjectMapper.class)
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

    @SqlQuery("SELECT email,password,userrole FROM person WHERE email = :email")
    User getUserByEmail(@Bind("email") String email);

    @SqlQuery("SELECT * FROM person WHERE password = :password")
    LoginModel getUserPassword(@Bind("password") String password);

    @SqlUpdate("select createClient(:picture_company, :name_company, :description_company, :email_user, :password_user)")
    void uploadClient(
            @Bind("picture_company") ByteArray picture_company,
            @Bind("name_company")String name_company,
            @Bind("description_company")String description_company,
            @Bind("email_user") String email_user,
            @Bind("password_user") String password_user
    );

    @SqlUpdate("select createStudent(:id_study, :email_user, :password_user)")
    void uploadStudent(
            @Bind("id_study") int id_study,
            @Bind("email_user") String email_user,
            @Bind("password_user") String password_user
    );

    @SqlUpdate("select createTeacher(:id_study, :email_user, :password_user)")
    void uploadTeacher(
            @Bind("id_study") int id_study,
            @Bind("email_user") String email_user,
            @Bind("password_user") String password_user
    );

    @SqlQuery("select id from study where name = :study")
    int getStudyId(
            @Bind("study") String study
    );
    
    @SqlQuery("SELECT * FROM project " +
            "WHERE id = (SELECT project_id FROM follow_project WHERE user_id = :id)" +
            "AND (SELECT last_login FROM \"User\" WHERE id = :id) <= " +
            "(SELECT MAX(upload_date) FROM paper WHERE project_id IN (SELECT project_id FROM follow_project WHERE user_id = :id))")
    List<Project> getNewNotifiactions(@Bind("id")int id);

    @SqlUpdate("UPDATE \"User\" SET last_login = :date WHERE id = :id")
    void updateLastLogin(@Bind("id")int id, @Bind("date") Date date);

    @SqlUpdate("delete from \"User\" where id= :user_id")
    void deleteUser(@Bind("user_id")int user_id);
}
