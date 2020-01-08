package src.controllers;

import src.core.UserModel;
import src.db.RegisterDAO;

public class RegisterController {

    RegisterDAO registerDAO;

    public UserModel createUser(String email, String password) {
        return registerDAO.registerUser(email, password);
    }
}
