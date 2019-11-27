package udemy.persistance;

import com.udemy.Mapper.ProjectMapper;
import com.udemy.core.models.Project;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(ProjectMapper.class)
public interface UserProjectDAO {

    @SqlUpdate("INSERT INTO favourited_project(user_id,project_id) VALUES (:userId, :projectId)")
    void favouriteProject(@Bind("projectId") int projectId, @Bind("userId") int userId);

    @SqlQuery("SELECT exists(SELECT 1 FROM favourited_project WHERE user_id = :userId AND project_id = :projectId)")
    boolean alreadyFavourited(@Bind("userId") int userId, @Bind("projectId") int projectId);

    @SqlUpdate("UPDATE project SET likes = likes + 1 WHERE project_id = :projectId")
    void increaseLikes(@Bind("projectId") int projectId);

    @SqlUpdate("UPDATE project SET likes = likes - 1 WHERE project_id = :projectId")
    void decreaseLikes(@Bind("projectId") int projectId);

    @SqlUpdate("DELETE FROM favourited_project WHERE project_id = :projectId AND user_id = :userId")
    void unfavouriteProject(@Bind("projectId") int projectId, @Bind("userId") int userId);


    @SqlQuery("select project_id, author, title, category, upload_date, likes, study, course,uploaded_by from project WHERE project_id = " +
            "ANY (SELECT project_id FROM favourited_project WHERE user_id = :userId)")
    List<Project> getFavouritedProjects(@Bind("userId") int userId);

}
