package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.ProjectMapper;
//import udemy.core.models.Student;
import udemy.Mapper.StudentMapper;
import udemy.core.models.ExtendThisUser;

import java.util.List;

@RegisterRowMapper(StudentMapper.class)
public interface StudentDAO {

    @SqlQuery("select * from \"User\" inner join student on id = student.user_id")
    List<ExtendThisUser> getAllStudents();

    @SqlUpdate("update \"User\" set email = :email where id = :id")
    void updateStudent(@Bind("id")int id,
                       @Bind("email")String email);
}
