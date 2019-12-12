package udemy.persistance;

import udemy.core.models.Project;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * This class request the database for certain data to upload using SQL statements
 * The class uses a SQL Object API to replace the necessary prepared statements
 * With the @bind command certain values can be inserted into the SQL queries
 * This secures the database against certain SQL injections
 */
public interface UploadDAO {

    @SqlQuery("SELECT teacher FROM person WHERE user_id = :user_id")
    boolean isATeacher(@Bind("user_id") int user_id);

    @SqlQuery("SELECT exists(SELECT 1 FROM project)")
    boolean anyProjectsExist();

    @SqlQuery("SELECT project_id FROM project ORDER BY project_id DESC limit 1")
    int getLastProjectId();

    @SqlUpdate("INSERT INTO project(project_id,author,title,category,upload_date," +
            "likes,study,course,uploaded_by,project_file) VALUES(:projectId,:author,:projectName,:category," +
            " :upload_date, :likes, :study, :course, :uploaded_by, :projectFile)")
    void uploadProject(@Bind("projectId") int projectId, @Bind("author") String author, @Bind("projectName") String projectName,
                       @Bind("category") String category, @Bind("upload_date") String upload_date
            , @Bind("likes") int likes, @Bind("study") String study, @Bind("course") String course,
                       @Bind("uploaded_by") int uploaded_by, @Bind("projectFile") byte[] projectFile);

    @SqlUpdate("INSERT INTO projectstatistics VALUES (:project_id,0)")
    void createProjectStatistics(@Bind("project_id") int id);

    @SqlQuery("SELECT  project_id, author, title, category, upload_date, likes, study, course,uploaded_by FROM project WHERE uploaded_by = :userId")
    List<Project> getProjectsUploadedBy(@Bind("userId") int userId);

    @SqlUpdate("DELETE FROM project WHERE project_id = :projectId")
    void deleteProject(@Bind("projectId") int projectId);
}
