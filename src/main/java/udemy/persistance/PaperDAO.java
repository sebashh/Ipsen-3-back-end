package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.PaperMapper;

@RegisterRowMapper(PaperMapper.class)
public interface PaperDAO {

    @SqlUpdate ("INSERT INTO paper(title, author, uploaded_by, pdf_location, project_id) VALUES (:title,:author,:uploaded_by,:pdf_location,:project_id)")
    void uploadPaper(@Bind("project_id") int projectId,@Bind("title") String title, @Bind("author") String author,@Bind("uploaded_by") int uploadedBy, @Bind("pdf_location") String filePath);

}
