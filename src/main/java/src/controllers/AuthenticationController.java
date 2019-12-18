package src.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import src.api.User;
import src.db.UserDAO;

public class AuthenticationController {

    UserDAO userDAO;

    public String getEncrPass(String password) {
        password = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(4, password.toCharArray());
        return password;
    }


    public boolean passwordValidator(String passCredential, String password) {
        BCrypt.Result cryptResult = BCrypt.verifyer().verify(passCredential.toCharArray(), password);
        if(cryptResult.verified) {
            return true;
        } else {
            return false;
        }
    }


    public User getUserByEmail(String emailAddress) {
        return userDAO.getUserByEmail(emailAddress);
    }
}
