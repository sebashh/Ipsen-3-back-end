package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import udemy.Mapper.DateStatisticMapper;
import udemy.Mapper.ProjectMapper;
import udemy.core.models.DateStatistic;
import udemy.core.models.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RegisterRowMapper(ProjectMapper.class)
@RegisterRowMapper(DateStatisticMapper.class)
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

    @SqlQuery("SELECT proj.* FROM Project AS proj JOIN \"View\" AS vie ON proj.id = vie.project_id GROUP BY proj.id ORDER BY COUNT(vie.*) DESC LIMIT 3")
    ArrayList<Project> getTopProjects();

    @SqlQuery ("SELECT name FROM category WHERE id = :id")
    String getCategory(@Bind("id") int categoryNumber);

    @SqlQuery ("SELECT name FROM study WHERE id = :id")
    String getStudy(@Bind("id") int studyNumber);

    @SqlQuery("SELECT COUNT(*) FROM Project WHERE category_id IN " +
            "(SELECT category_id FROM interests WHERE user_id = " +
            "(SELECT \"id\" FROM \"User\" WHERE \"id\" = :userId))" +
            " AND created_on > (SELECT last_login FROM \"User\" WHERE \"id\" = :userId)")
    int getRecentProjectsAmount(@Bind("userId") int userId);


    @SqlQuery("SELECT COUNT(*) FROM Paper WHERE project_id IN " +
                "(SELECT id FROM Project WHERE category_id IN" +
                    "(SELECT category_id FROM interests WHERE user_id =" +
                        "(SELECT \"id\" FROM \"User\" WHERE \"id\" = :userId)))" +
                "AND upload_date > (SELECT last_login FROM \"User\" WHERE \"id\" = :userId)")
    int getRecentPapersAmount(@Bind("userId") int userId);

    @SqlQuery("SELECT COUNT(DISTINCT(Project.id)) FROM Project JOIN Paper ON Project.id = paper.project_id JOIN" +
            " \"User\" ON paper.uploaded_by = \"User\".id JOIN \"View\" ON Project.id = \"View\".project_id" +
            " WHERE \"User\".id = :userId")
    int getRecentProjectViewed(@Bind("userId") int userId);

    @SqlQuery("SELECT COUNT(*) FROM Project JOIN Paper ON Project.id = paper.project_id JOIN" +
            " \"User\" ON paper.uploaded_by = \"User\".id JOIN \"View\" ON Project.id = \"View\".project_id" +
            "  WHERE \"User\".id = :userId")
    int getRecentTotalViews(@Bind("userId") int userId);

    @SqlQuery("SELECT COUNT(*) FROM \"View\" JOIN Project ON \"View\".project_id = Project.id " +
            " JOIN Client ON Project.client_id = Client.user_id JOIN \"User\" ON Client.user_id = \"User\".id" +
            " WHERE \"User\".id = :userId")
    int getRecentTotalViewsProject(@Bind("userId") int userId);

    @SqlQuery("SELECT COUNT(*) FROM Paper JOIN Project ON Paper.project_id = Project.id" +
            " JOIN Client ON Project.client_id = Client.user_id JOIN \"User\" ON Client.user_id = \"User\".id" +
             " WHERE \"User\".id = :userId")
    int getRecentUploads(@Bind("userId") int userId);

    @SqlQuery("SELECT COUNT(*) FROM Project JOIN Client ON Project.client_id = Client.user_id JOIN \"User\" " +
            " ON Client.user_id = \"User\".id" +
            " WHERE \"User\".id = :userId")
    int getTotalProjectsClient(@Bind("userId") int userId);

    @SqlQuery("WITH projectdays AS (" +
            " SELECT date(date_trunc('day', created_on)) \"day\", count(id) \"total\"" +
            " FROM Project" +
            " GROUP BY 1)" +
            " SELECT g.series::date AS \"days\", projectdays.total" +
            " FROM generate_series(now() - interval '1 week' , now() - interval '1 day', '1 day'::interval) AS g(series) LEFT JOIN" +
            " projectdays ON projectdays.day = g.series::date")
    List<DateStatistic> getAdminProjectStatistics();

    @SqlQuery("WITH paperdays AS (" +
            " SELECT date(date_trunc('day', upload_date)) \"day\", count(id) \"total\"" +
            " FROM Paper" +
            " GROUP BY 1)" +
            " SELECT g.series::date AS \"days\", paperdays.total" +
            " FROM generate_series(now() - interval '1 week' , now() - interval '1 day', '1 day'::interval) AS g(series) LEFT JOIN" +
            " paperdays ON paperdays.day = g.series::date")
    List<DateStatistic> getAdminPaperStatistics();

    @SqlQuery("WITH viewdays AS (" +
            " SELECT date(date_trunc('day', view_date)) \"day\", count(project_id) \"total\"" +
            " FROM \"View\"" +
            " GROUP BY 1)" +
            " SELECT g.series::date AS \"days\", viewdays.total" +
            " FROM generate_series(now() - interval '1 week' , now() - interval '1 day', '1 day'::interval) AS g(series) LEFT JOIN" +
            " viewdays ON viewdays.day = g.series::date")
    List<DateStatistic> getAdminViewStatistics();

    @SqlQuery("WITH logindays AS (" +
            " SELECT date(date_trunc('day', last_login)) \"day\", count(id) \"total\"" +
            " FROM \"User\" JOIN Student ON \"User\".id = Student.user_id" +
            " GROUP BY 1)" +
            " SELECT g.series::date AS \"days\", logindays.total" +
            " FROM generate_series(now() - interval '1 week' , now() - interval '1 day', '1 day'::interval) AS g(series) LEFT JOIN" +
            " logindays ON logindays.day = g.series::date")
    List<DateStatistic> getAdminLoginStudentStatistics();

    @SqlQuery("WITH logindays AS (" +
            " SELECT date(date_trunc('day', last_login)) \"day\", count(id) \"total\"" +
            " FROM \"User\" JOIN Teacher ON \"User\".id = Teacher.user_id" +
            " GROUP BY 1)" +
            " SELECT g.series::date AS \"days\", logindays.total" +
            " FROM generate_series(now() - interval '1 week' , now() - interval '1 day', '1 day'::interval) AS g(series) LEFT JOIN" +
            " logindays ON logindays.day = g.series::date")
    List<DateStatistic> getAdminLoginTeacherStatistics();

    @SqlQuery("WITH logindays AS (" +
            " SELECT date(date_trunc('day', last_login)) \"day\", count(id) \"total\"" +
            " FROM \"User\" JOIN Client ON \"User\".id = Client.user_id" +
            " GROUP BY 1)" +
            " SELECT g.series::date AS \"days\", logindays.total" +
            " FROM generate_series(now() - interval '1 week' , now() - interval '1 day', '1 day'::interval) AS g(series) LEFT JOIN" +
            " logindays ON logindays.day = g.series::date")
    List<DateStatistic> getAdminLoginClientStatistics();
}

