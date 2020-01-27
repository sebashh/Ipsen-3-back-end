package udemy.Controllers;

import udemy.core.models.UserModel;
import udemy.persistance.RegisterDAO;

public class RegisterController {

    RegisterDAO registerDAO;


    public UserModel createUser(String email, String password) {

        return registerDAO.registerUser(email, password);
    }


}
