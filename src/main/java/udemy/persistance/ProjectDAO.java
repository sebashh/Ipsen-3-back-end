package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.core.models.Project;
import udemy.Mapper.ProjectMapper;

import java.util.List;

@RegisterRowMapper(ProjectMapper.class)
public interface ProjectDAO {

    @SqlQuery("select project.id, title, summary, created_on, client_id, study.name as study_name, category.name as category_name\n" +
            "from project\n" +
            "INNER JOIN category ON project.category_id = category.id\n" +
            "INNER JOIN study ON project.study_id = study.id \n" +
            "WHERE project.id = :id")
    Project getProject(@Bind("id") int id);

    @SqlQuery("select project.id, title, summary, created_on, client_id, study.name as study_name, category.name as category_name\n" +
            "from project\n" +
            "INNER JOIN category ON project.category_id = category.id\n" +
            "INNER JOIN study ON project.study_id = study.id \n" +
            "WHERE project.id = :id")
    Project getProject(@Bind("id") int id);

    @SqlUpdate("INSERT INTO project(id, title, summary, client_id, study_id, category_id) VALUES(:id, :title, :summary, :client_id, :study_id, :category_id)")
    void uploadProject(@Bind("id")int id,
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
    @SqlQuery("select project.id, title, summary, created_on, client_id, study.name as study_name, category.name as category_name\n" +
            "from project\n" +
            "INNER JOIN category ON project.category_id = category.id\n" +
            "INNER JOIN study ON project.study_id = study.id \n" +
            "WHERE client_id = :client_id")
    List<Project> getAllProjectsOfClient(@Bind("client_id")int client_id);
}
