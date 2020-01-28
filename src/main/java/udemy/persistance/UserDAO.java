package udemy.persistance;


import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
//import udemy.Mapper.LoginMapper;
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
//@RegisterRowMapper(LoginMapper.class)
@RegisterRowMapper(ProjectMapper.class)
public interface UserDAO {


    @SqlQuery("SELECT email,password,user_role FROM \"User\" WHERE email = :email")
    User getUserByEmail(@Bind("email") String email);

    @SqlQuery("SELECT password FROM \"User\" WHERE email = :email")
    String getPassword(@Bind("email") String email);

    @SqlQuery("SELECT whatID(:email)")
    int getUserIdByEmail(@Bind("email") String email);

    @SqlQuery("SELECT whatUser(:id)")
    String getUserRole(@Bind("id") int id);

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

    @SqlUpdate("update \"User\" set email = :email where id = :id")
    void updateUser(@Bind("id")int id,
                    @Bind("email")String email);
}
