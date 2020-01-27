package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import udemy.Mapper.CategoryMapper;
import udemy.Mapper.StudyMapper;
import udemy.core.models.Category;
import udemy.core.models.Study;

import java.util.List;

@RegisterRowMapper(StudyMapper.class)
public interface StudyDAO {
    @SqlQuery("SELECT * FROM Study")
    List<Study> getStudies();


    @SqlQuery("select name from educational inner join study on educational.study_id = study.id where user_id = :user_id")
    String getStudyOfClient(@Bind("user_id")int user_id);
}
