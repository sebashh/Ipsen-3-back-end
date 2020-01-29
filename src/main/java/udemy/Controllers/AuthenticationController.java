package udemy.Controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;
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
        return cryptResult.verified;
    }


    public int getUserIdByEmail(String email) {
        return userDAO.getUserIdByEmail(email);
    }


    public User getUserByEmail(String email) {
        User user = userDAO.getUserByEmail(email);
        System.out.println("user: " + user);
        return user;
    }

    public String getUserRole(int userId) {
        return userDAO.getUserRole(userId);
    }

    public String getPasswordByEmail(String username) {
        return userDAO.getPassword(username);
    }

    public boolean verifyPassword(BasicCredentials basicCredentials) throws AuthenticationException {
        try {
            String storedPassword = getPasswordByEmail(basicCredentials.getUsername());
            if(passwordValidator(basicCredentials.getPassword(), storedPassword)) {
                return true;
            } else {
                return false;
            }
        }
        catch(Exception e){
                throw new AuthenticationException(e);
            }
        }
    }
