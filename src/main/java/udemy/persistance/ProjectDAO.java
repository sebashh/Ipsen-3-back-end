package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.ProjectMapper;
import udemy.core.models.Project;

import java.util.List;

@RegisterRowMapper(ProjectMapper.class)
public interface ProjectDAO {

    @SqlQuery("SELECT pdf_location FROM paper WHERE id = :projectId")
    String getProjectFile(@Bind("projectId") int id);

    @SqlQuery("select project.id, title, summary, created_on, client_id, study_id, category_id " +
            "from project " +
            "WHERE project.id = :id")
    Project getProject(@Bind("id") int id);

    @SqlUpdate("INSERT INTO project(title, summary, client_id, study_id, category_id) VALUES(:title, :summary, :client_id, :study_id, :category_id)")
    void uploadProject(
                       @Bind("title")String title,
                       @Bind("summary")String summary,
                       @Bind("client_id")int clientId,
                       @Bind("study_id")int studyId,
                       @Bind("category_id")int categoryId);

    @SqlQuery("SELECT id FROM study WHERE name = :name")
    int getStudyId(@Bind("name")String name);

    @SqlQuery("SELECT id FROM category WHERE name = :name")
    int getCategoryId(@Bind("name")String name);

    @SqlQuery("SELECT id FROM project ORDER BY id DESC limit 1")
    int getNewProjectId();

//    @SqlQuery("SELECT * FROM project WHERE title = :title")
//    int getAll(@Bind("title")String title);


    @SqlQuery("SELECT * FROM project INNER JOIN follow_project ON project.id = follow_project.project_id AND follow_project.user_id = :id")
    List<Project> getUserFollowedProjects(@Bind("id")int id);


    @SqlQuery("SELECT Project.id, title, summary, created_on, client_id, study_id, category_id" +
            " FROM Project INNER JOIN follow_project ON Project.id = follow_project.Project_id " +
            " AND follow_project.user_id = :id ORDER BY Random() LIMIT 3")
    List<Project> getUserFollowedProjectsRandom(@Bind("id")int id);

    @SqlQuery("select project.id, title, summary, created_on, client_id, study_id, category_id\n" +
            "from project\n" +
            "WHERE client_id = :client_id")
    List<Project> getAllProjectsOfClient(@Bind("client_id")int client_id);



    @SqlQuery("SELECT Project.id, title, summary, created_on, client_id, study_id, Project.category_id  FROM Project JOIN interests ON Project.category_id = interests.category_id JOIN \"User\" ON interests.user_id = \"User\".id" +
            " WHERE \"User\".id = :studentId AND Project.created_on > \"User\".last_login " +
            " ORDER BY last_login DESC LIMIT 3")
    List<Project> getCreatedProjectWithInterests(@Bind("studentId") int studentId);


    @SqlQuery("SELECT Project.id, Project.title, summary, created_on, client_id, study_id, category_id FROM Project JOIN Paper ON Project.id = Paper.project_id JOIN Client ON Client.user_id = " +
            " Project.client_id JOIN \"User\" ON Client.user_id = \"User\".id" +
            " WHERE \"User\".id = :clientId ORDER BY Paper.upload_date DESC LIMIT 3")
    List<Project> getRecentlyUpdatedProjects(@Bind("clientId") int clientId);



    @SqlQuery("SELECT Project.id, Project.title, summary, created_on, client_id,study_id, category_id FROM Project JOIN \"View\" ON \"View\".project_id = Project.id" +
            " JOIN Client ON Project.client_id = Client.user_id" +
            " WHERE Client.user_id = :clientId" +
            " GROUP BY Project.id" +
            " ORDER BY COUNT(\"View\".*) DESC LIMIT 3")
    List<Project> getTopViewedProjectsClient(@Bind("clientId") int clientId);


    @SqlUpdate("update project set title = :title, summary = :summary, study_id = :study_id, category_id = :category_id where id = :id")
    void updateProject(@Bind("id")int id,
                       @Bind("title")String title,
                       @Bind("summary")String summary,
                       @Bind("study_id")int study_id,
                       @Bind("category_id")int category_id);

    @SqlUpdate("delete from project where id= :project_id")
    void deleteProject(@Bind("project_id")int project_id);

    @SqlQuery("select COUNT(*) FROM follow_project WHERE project_id=:id")
    int getFollowAmount(@Bind("id")int id);

    @SqlUpdate("INSERT INTO follow_project(project_id, user_id) VALUES(:projectId, :userId)")
    void followProject(@Bind("projectId")int project_id, @Bind("userId")int user_id);

    @SqlUpdate("DELETE FROM follow_project WHERE project_id = :projectId AND user_id = :userId")
    void unFollowProject(@Bind("projectId")int project_id, @Bind("userId")int user_id);

    @SqlQuery("SELECT EXISTS(SELECT 1 FROM follow_project WHERE project_id = :projectId AND user_id = :userId)")
    boolean isFollowing(@Bind("projectId")int project_id, @Bind("userId")int user_id);

    @SqlQuery("SELECT EXISTS(SELECT 1 FROM project_acces WHERE teacher_id = :userId AND project_id = :projectId AND in_progress = FALSE)")
    boolean hasAccess(@Bind("projectId")int id, @Bind("userId")int userId);

    @SqlQuery("SELECT EXISTS(SELECT 1 FROM project_acces WHERE teacher_id = :userId AND project_id = :projectId)")
    boolean isInAccessTable(@Bind("projectId")int id, @Bind("userId")int userId);

    @SqlUpdate("INSERT INTO project_acces(teacher_id, project_id, in_progress) VALUES(:userId, :id, TRUE)")
    void requestAccess(@Bind("id")int id, @Bind("userId")int userId);

    @SqlQuery("SELECT client_id FROM project WHERE id = :projectId")
    int getProjectOwner(@Bind("projectId")int id);

    @SqlUpdate("UPDATE project_acces SET in_progress = FALSE WHERE teacher_id = :userId AND project_id = :projectId")
    void acceptAcces(@Bind("projectId")int id, @Bind("userId")int userId);


    @SqlUpdate("DELETE FROM project_acces WHERE teacher_id = :userId AND project_id = :projectId")
    void denieAcces(@Bind("projectId")int id, @Bind("userId")int userId);

    @SqlQuery("SELECT teacher_id FROM project_acces WHERE project_id = :projectId AND in_progress = FALSE")
    int[] getAllAccess(@Bind("projectId")int id);

    @SqlQuery("SELECT teacher_id FROM project_acces WHERE project_id = :projectId AND in_progress = TRUE")
    int[] getAllRequests(@Bind("projectId")int projectId);

    @SqlQuery("SELECT * FROM project")
    List<Project> getAllProjects();
}
