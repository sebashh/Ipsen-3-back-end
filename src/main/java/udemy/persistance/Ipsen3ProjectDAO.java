package udemy.persistance;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;


public interface Ipsen3ProjectDAO {

    @SqlQuery("SELECT pdf_location FROM paper WHERE id = :projectId")
    String getProjectFile(@Bind("projectId") int id);

}
