package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import udemy.Mapper.TeacherMapper;
import udemy.core.models.ExtendThisUser;

import java.util.List;


@RegisterRowMapper(TeacherMapper.class)
public interface TeacherDAO {

    @SqlQuery("select * from \"User\" inner join teacher on id = teacher.user_id")
    List<ExtendThisUser> getAllTeachers();
}
