package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import udemy.Mapper.PaperMapper;
import udemy.core.models.PaperModel;

import java.util.List;

@RegisterRowMapper(PaperMapper.class)
public interface PaperDAO {

    @SqlQuery("SELECT title, author, pdf_location, project_id FROM paper")
    List<PaperModel> getPapers();
}
