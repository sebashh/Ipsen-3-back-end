package udemy.Controllers;

import udemy.core.models.Admin;
import udemy.core.models.Project;
import udemy.core.models.User;
import udemy.core.models.UserModel;
import udemy.persistance.ProjectDAO;
import udemy.persistance.UserDAO;

import java.util.Date;
import java.util.List;

public class UserController {

    private UserDAO userDAO;
    private ProjectController projectController;
    private AuthenticationController authController;

    public UserController(UserDAO userDAO, AuthenticationController authenticationController){
        this.userDAO = userDAO;
        this.authController = authenticationController;
    }

    public void UpdateLastLogin(int id){
        userDAO.updateLastLogin(id, new Date());
    }

    public List<Project> getNotifications(int id) {
        List<Project> userProjects = userDAO.getNewNotifiactions(id);
        UpdateLastLogin(id);
        return null;
    }

    public boolean uploadAdmin(Admin admin){
        System.out.println(admin.name);
        String encryptPassword = authController.getEncrPass(admin.password);
        return userDAO.uploadAdmin(admin.name, encryptPassword);
    }

    public boolean uploadTeacher(User user) {
        String encryptPassword = authController.getEncrPass(user.password_user);
        int userId = userDAO.uploadTeacher(user.study, user.email_user, encryptPassword);
        if(userId != 0) {
            for (int category : user.categories) {
                userDAO.uploadInterests(userId, category);
            }
            return true;
        }
        else return false;
    }

    public boolean uploadClient(User user) {
        String encryptPassword = authController.getEncrPass(user.password_user);
        return userDAO.uploadClient(user.picture_company, user.name_company, user.description_company, user.email_user, encryptPassword);
    }

    public boolean uploadStudent(User user) {
        String encryptPassword = authController.getEncrPass(user.password_user);
        int userId = userDAO.uploadStudent(user.study, user.email_user, encryptPassword);
        if(userId != 0 && userId != -1) {
            for (int category : user.categories) {
                userDAO.uploadInterests(userId, category);
            }
            return true;
        }
        else return false;
    }

    public String getUserEmail(int teacher) {
        return userDAO.getEmailById(teacher);
    }

    public void deleteUser(int id){
        userDAO.deleteUser(id);
    }
}
