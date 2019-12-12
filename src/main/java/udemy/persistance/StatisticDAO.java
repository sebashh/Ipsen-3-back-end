package udemy.persistance;

import udemy.Mapper.ProjectMapper;
import udemy.core.models.Project;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.HashMap;
import java.util.List;

/**
 * This class request the database for certain statistical data using SQL statements
 * The class uses a SQL Object API to replace the necessary prepared statements
 * With the @bind command certain values can be inserted into the SQL queries
 * This secures the database against certain SQL injections
 */
@RegisterRowMapper(ProjectMapper.class)
public interface StatisticDAO {



    @SqlQuery("SELECT project_id, author, title, category, upload_date, likes, study, course,uploaded_by FROM project p JOIN" +
            "projectstatistics ps ON p.project_id = ps.projectstatistics_id" +
            "ORDER BY p.likes DESC, p.project_id ASC LIMIT 10 ")
    List<Project> getTopViewedProjects();

    @SqlQuery ("SELECT category, SUM(likes) AS totallikes FROM project GROUP BY category ORDER BY totallikes desc limit 10")
    HashMap<String, Integer> getTopLikedCategories();

    @SqlQuery ("SELECT count(*) FROM dateviews WHERE viewdate = :date ")
    int getViewsOnDate(@Bind("date") String date);

    @SqlQuery ("SELECT count(*) FROM dateviews WHERE viewdate = :date AND project_Id = :project_Id")
    int getViewsOnDateFromProject(@Bind("date") String date, @Bind("project_Id") int projectId);

    @SqlQuery("SELECT count(*) FROM dateviews WHERE viewdate LIKE :monthyear")
    int getViewsDuringMonth(@Bind("monthyear") String monthyear);

    @SqlQuery("SELECT count(*) FROM dateviews WHERE viewdate LIKE :monthyear AND project_Id = :project_Id")
    int getViewsDuringMonthFromProject(@Bind("monthyear") String monthyear, @Bind("project_Id") int projectId);

}
