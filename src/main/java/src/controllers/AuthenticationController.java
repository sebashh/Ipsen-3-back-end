package src.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import src.core.LoginModel;
import src.db.UserDAO;

import javax.ws.rs.core.Response;
import java.util.Optional;

public class AuthenticationController {

    UserDAO userDAO;
    String passhash = "Wachtwoord";

    public String loginUser(String email, String password) {
//        passhash = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(4, password.toCharArray());

        if(email != null) {
            if(password != null) {
                checkUserByCredentials(email, password);
                System.out.println(passhash);
                System.out.println(email);
            }
        }
                return null;
    }

    public Optional<LoginModel> checkUserByCredentials(final String email, final String password) {

        LoginModel loginModel = userDAO.getUserByEmail(email);
        if(loginModel.getEmail() == email) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), loginModel.getPassword());
            if(result.verified) {
                return Optional.of(loginModel);
            }

        }

        return Optional.empty();
    }

    public void createUser(String email, String password) {
        password = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(4, password.toCharArray());
        userDAO.register(email, password);
    }

//    public LoginModel getUserByEmail(String emailAddress) {
//        userDAO.getUserByEmail(emailAddress);
//        return ;
//    }
//
//    public LoginModel getUserByCredentials(final String email, final String password) {
//        LoginModel user = this.getUserByEmail(email);
//        if (user.getId() != null) {
//            user.getEmail();
//            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
//            if(result.verified) {
//                return user;
//            }
//        }
//        return null;
//    }
}
