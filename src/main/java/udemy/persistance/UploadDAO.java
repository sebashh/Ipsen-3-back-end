package udemy.persistance;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

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
}
