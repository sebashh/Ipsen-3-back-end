package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import udemy.Mapper.ProjectMapper;
import udemy.core.models.Project;

import java.util.ArrayList;

@RegisterRowMapper(ProjectMapper.class)
public interface StatisticsDAO {

    @SqlQuery("SELECT COUNT(*) FROM \"User\"")
    int getTotalUsers();

    @SqlQuery("SELECT COUNT(*) FROM Student")
    int getTotalStudents();

    @SqlQuery("SELECT COUNT(*) FROM Teacher")
    int getTotalTeachers();

    @SqlQuery("SELECT COUNT(*) FROM Client")
    int getTotalClients();

    @SqlQuery("SELECT COUNT(*) FROM Project")
    int getTotalProjects();

    @SqlQuery("SELECT proj.* FROM Project AS proj JOIN \"View\" AS vie ON proj.id = vie.project_id GROUP BY proj.id ORDER BY COUNT(vie.*) DESC LIMIT 5")
    ArrayList<Project> getTopProjects();

    @SqlQuery ("SELECT name FROM category WHERE id = :id")
    String getCategory(@Bind("id") int categoryNumber);

    @SqlQuery ("SELECT name FROM study WHERE id = :id")
    String getStudy(@Bind("id") int studyNumber);
}

