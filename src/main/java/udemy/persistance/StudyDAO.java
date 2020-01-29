package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.StudyMapper;
import udemy.core.models.Study;

import java.util.List;

@RegisterRowMapper(StudyMapper.class)
public interface StudyDAO {
    @SqlQuery("SELECT * FROM Study")
    List<Study> getStudies();


    @SqlQuery("select id from study where user_id = :user_id")
    int getStudyOfClient(@Bind("user_id")int user_id);

    @SqlQuery("select id from study where name = :name")
    int getStudyIdFromName(@Bind("name")String name);

    @SqlUpdate("update educational set study_id = :study_id where user_id = :user_id")
    void updateEducational(@Bind("study_id")int study_id,
                           @Bind("user_id")int user_id);
}
