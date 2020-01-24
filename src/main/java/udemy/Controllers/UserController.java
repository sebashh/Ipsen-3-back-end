package udemy.Controllers;

import udemy.core.models.Project;
import udemy.core.models.User;
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

    public boolean uploadTeacher(User user) {
        int userId = userDAO.uploadTeacher(user.study, user.email_user, user.password_user);
        if(userId != 0) {
            for (int category : user.categories) {
                userDAO.uploadInterests(userId, category);
            }
            return true;
        }
        else return false;
    }

    public boolean uploadClient(User user) {
        return userDAO.uploadClient(user.picture_company, user.name_company, user.description_company, user.email_user, user.password_user);
    }

    public boolean uploadStudent(User user) {
        int userId = userDAO.uploadStudent(user.study, user.email_user, user.password_user);
        if(userId != 0 && userId != -1) {
            for (int category : user.categories) {
                userDAO.uploadInterests(userId, category);
            }
            return true;
        }
        else return false;
    }
}
