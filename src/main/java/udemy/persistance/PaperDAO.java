package udemy.persistance;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface PaperDAO {

    @SqlUpdate ("INSERT INTO paper(id, title, author, uploaded_by, pdf_location, project_id) VALUES (:id,:title,:author,:uploaded_by,:pdf_location),:project_id")
    void uploadPaper(@Bind("id") int newPaperId,@Bind("project_id") int projectId,@Bind("title") String title, @Bind("author") String author,@Bind("uploaded_by") int uploadedBy, @Bind("pdf_location") String filePath);

    @SqlQuery ("SELECT id FROM paper ORDER BY id DESC limit 1")
    int getLastPaperId();

    @SqlQuery ("SELECT exists(SELECT 1 FROM paper)")
    boolean anyPaperExist();
}
