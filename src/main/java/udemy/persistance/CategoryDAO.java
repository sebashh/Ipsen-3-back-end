package udemy.persistance;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import udemy.Mapper.CategoryMapper;
import udemy.Mapper.ProjectMapper;
import udemy.core.models.Category;
import udemy.core.models.Project;

import java.util.List;

@RegisterRowMapper(CategoryMapper.class)
public interface CategoryDAO {

    @SqlQuery("SELECT * FROM category")
    List<Category> getCategories();

    @SqlUpdate("insert into \"category\" (name) values (:category);")
    boolean addCategory(@Bind("category") String category);
}
