package udemy.persistance;

import com.udemy.Mapper.ProjectMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import udemy.core.models.Project;

import java.util.List;


@RegisterRowMapper(ProjectMapper.class)
public interface ProjectDAO {

    @SqlQuery("SELECT project_id, author, title, category, upload_date, likes, study, course,uploaded_by FROM project ORDER BY project_id DESC LIMIT :amount OFFSET :startindex")
    List<Project> getProjectByPage(@Bind("amount") int amount, @Bind("startindex") int startindex);

    @SqlQuery("SELECT project_id, author, title, category, upload_date, likes, study, course,uploaded_by FROM project WHERE project_id = :projectId")
    Project getProjectById(@Bind("projectId") int projectId);

    @SqlQuery("SELECT exists(SELECT 1 FROM project)")
    boolean anyProjectsExist();

    @SqlQuery("SELECT project_id FROM project ORDER BY project_id DESC limit 1")
    int getLastProjectId();

    @SqlQuery("SELECT project_file FROM project WHERE project_id = :projectId")
    byte[] getProjectFileById(@Bind("projectId") int projectId);

    @SqlQuery("SELECT project_id, author, title, category, upload_date, likes, study, course,uploaded_by FROM project WHERE title ~* :title AND author ~* :author AND category ~* :category AND study ~* :study AND course ~* :course ORDER BY likes")
    List<Project> getProjectFile(@Bind("title") String title, @Bind("author") String author, @Bind("category") String category, @Bind("study") String study, @Bind("course") String course);

    @SqlQuery("SELECT COUNT(project_id) FROM project WHERE title ~* :title AND author ~* :author AND category ~* :category AND study ~* :study AND course ~* :course")
    int getProjectsAmount(@Bind("title") String title, @Bind("author") String author, @Bind("category") String category, @Bind("study") String study, @Bind("course") String course);

    @SqlQuery("SELECT P.project_id, P.author, P.title, P.category, p.upload_date, P.likes, P.study, P.course, P.uploaded_by " +
            "FROM project P inner join favourited_project F on P.project_id = F.project_id WHERE F.user_id = :user")
    List<Project> getFavouritedProjects(@Bind("user") int userId);

    @SqlQuery("SELECT DISTINCT category FROM project ORDER BY category")
    List<String> getCategories();

    @SqlQuery("SELECT DISTINCT study FROM project ORDER BY study")
    List<String> getStudies();

    @SqlQuery("SELECT DISTINCT course FROM project ORDER BY course")
    List<String> getCourses();

    @SqlQuery("SELECT project_id, author, title, category, upload_date, likes, study, course,uploaded_by FROM project WHERE title ~* :title AND author ~* :author AND category ~* :category AND study ~* :study AND course ~* :course " +
            "ORDER BY project_id DESC LIMIT :amount OFFSET :startIndex")
    List<Project> getProjectsByPageWithFilters(@Bind("startIndex") int startIndex, @Bind("amount") int amount, @Bind("title") String title,
                                               @Bind("author") String author, @Bind("category") String category, @Bind("study") String study, @Bind("course") String course);

    @SqlQuery("SELECT project_id, author, title, category, upload_date, likes, study, course,uploaded_by FROM project ORDER BY likes DESC, project_id ASC LIMIT 10")
    List<Project> getTopProjects();
}

/*
    @SqlUpdate("INSERT INTO project(project_id,author,title,category,upload_date," +
            "likes,study,course,uploaded_by,project_file) VALUES(:projectId,:author,:projectName,:category," +
            " :upload_date, :likes, :study, :course, :uploaded_by, :projectFile)")
    void uploadProject(@Bind("projectId")int projectId, @Bind("author") String author, @Bind("projectName") String projectName,
                       @Bind("category")String category, @Bind("upload_date") LocalDate upload_date
            ,@Bind("likes")int likes, @Bind("study")String study, @Bind("course") String course,
                       @Bind("uploaded_by")int uploaded_by,@Bind("projectFile") byte[] projectFile);

 */

