package udemy.Controllers;

import udemy.core.models.Category;
import udemy.core.models.Paper;
import udemy.core.models.Project;
import udemy.persistance.CategoryDAO;
import udemy.persistance.ProjectDAO;

import java.util.List;


public class CategoryController {
    private CategoryDAO categoryDAO;

    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }


    public List<Category> getCategories(){
        return categoryDAO.getCategories();
    }
}
