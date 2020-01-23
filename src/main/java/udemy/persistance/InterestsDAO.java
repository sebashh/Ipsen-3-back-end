package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.InterestMapper;
import udemy.core.models.Category;

import java.util.List;

@RegisterRowMapper(InterestMapper.class)
public interface InterestsDAO {

    @SqlUpdate("INSERT INTO Interests(user_id, category_id) VALUES(:user_id, :category_id)")
    void uploadInterests(

            @Bind("user_id") int user_id, @Bind("category_id") int category_id
    );

    @SqlQuery("SELECT \"id\" FROM \"User\" WHERE email = :email")
    int getUserId(
        @Bind("email") String email
    );
}
