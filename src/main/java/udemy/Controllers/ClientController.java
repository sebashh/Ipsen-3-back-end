package udemy.Controllers;

import udemy.core.models.User;
import udemy.persistance.UserDAO;


public class ClientController {
    private UserDAO userDAO;

    public ClientController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void uploadClient(User user) {

        userDAO.uploadClient(user.picture_company, user.name_company, user.description_company, user.email_user, user.password_user);

    }
}
