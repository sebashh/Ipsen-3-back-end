package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.PaperMapper;
import udemy.core.models.Paper;
import javax.ws.rs.core.Response;
import udemy.Mapper.PaperMapper;

import java.util.List;

@RegisterRowMapper(PaperMapper.class)
public interface PaperDAO {

    @SqlQuery("SELECT * FROM paper")
    List<Paper> getPapers();

    @SqlUpdate ("INSERT INTO paper(title, author, uploaded_by, pdf_location, project_id) VALUES (:title,:author,:uploaded_by,:pdf_location,:project_id)")
    void uploadPaper(@Bind("project_id") int projectId,@Bind("title") String title, @Bind("author") String author,@Bind("uploaded_by") int uploadedBy, @Bind("pdf_location") String filePath);

    @SqlQuery ("SELECT * FROM paper WHERE project_id=:id")
    List<Paper> getProjectPapers(@Bind("id")int id);

}
