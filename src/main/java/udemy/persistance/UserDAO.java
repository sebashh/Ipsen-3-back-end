package udemy.persistance;

import javassist.bytecode.ByteArray;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


public interface UserDAO {

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

}
