package udemy.Controllers;

import udemy.core.models.Project;
import udemy.persistance.ProjectDAO;
import udemy.persistance.UserDAO;

import java.util.Date;
import java.util.List;

public class UserController {

    private UserDAO userDAO;
    private ProjectController projectController;

    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void UpdateLastLogin(int id){
        userDAO.updateLastLogin(id, new Date());
    }

    public List<Project> getNotifications(int id) {
        List<Project> userProjects = userDAO.getNewNotifiactions(id);
        System.out.println(userProjects);
        UpdateLastLogin(id);
        return null;
    }
}
