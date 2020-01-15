package udemy.Controllers;

import udemy.core.models.User;
import udemy.persistance.UserDAO;


public class TeacherController {
    private UserDAO userDAO;

    public TeacherController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void uploadTeacher(User user) {
        int id = userDAO.getStudyId(user.study);
        userDAO.uploadTeacher(id, user.email_user, user.password_user);

    }
}
