package udemy.Controllers;

import udemy.core.models.User;
import udemy.persistance.UserDAO;


public class StudentController {
    private UserDAO userDAO;

    public StudentController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void uploadStudent(User user) {
        int id = userDAO.getStudyId(user.study);
        userDAO.uploadStudent(id, user.email_user, user.password_user);

    }
}
