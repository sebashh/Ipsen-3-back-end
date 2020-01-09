package udemy.Controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import udemy.User;
import udemy.persistance.UserDAO;

public class AuthenticationController {

    private final UserDAO userDAO;

    public AuthenticationController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String getEncrPass(String password) {
        password = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(4, password.toCharArray());
        return password;
    }


    public boolean passwordValidator(String passCredential, String password) {
        BCrypt.Result cryptResult = BCrypt.verifyer().verify(passCredential.toCharArray(), password);
        if (cryptResult.verified) {
            return true;
        } else {
            return false;
        }
    }


    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
