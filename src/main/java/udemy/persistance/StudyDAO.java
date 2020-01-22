package udemy.persistance;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
//import udemy.core.models.Student;


public interface StudyDAO {

    @SqlQuery("select name from educational inner join study on educational.study_id = study.id where user_id = :user_id")
    String getStudyOfClient(@Bind("user_id")int user_id);

}
