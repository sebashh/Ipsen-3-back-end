package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.PaperMapper;
import udemy.core.models.Paper;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Response;
import udemy.Mapper.PaperMapper;

import java.util.List;

@RegisterRowMapper(PaperMapper.class)
public interface PaperDAO {

    @SqlQuery("SELECT * FROM paper")
    List<Paper> getPapers();


    @SqlUpdate ("INSERT INTO paper(title, author, uploaded_by, pdf_location, project_id) VALUES (:title,:author,:uploaded_by,:pdf_location,:project_id)")
    void uploadPaper(@Bind("project_id") int projectId,@Bind("title") String title, @Bind("author") String author,@Bind("uploaded_by") int uploadedBy, @Bind("pdf_location") String filePath);

    @RolesAllowed("admin")
    @SqlQuery ("SELECT * FROM paper WHERE project_id=:id")
    List<Paper> getProjectPapers(@Bind("id")int id);

    @SqlQuery ("SELECT COUNT(*) FROM paper WHERE project_id=:id")
    int getPaperAmount(@Bind("id")int id);

    @SqlUpdate("update paper set title = :title, author = :author, pdf_location = :pdf_location where id = :id")
    void updatePaper(@Bind("id")int id,
                     @Bind("title")String title,
                     @Bind("author")String author,
                     @Bind("pdf_location")String pdf_location);

    @SqlUpdate("delete from paper where id= :paper_id")
    void deletePaper(@Bind("paper_id")int paper_id);
}
