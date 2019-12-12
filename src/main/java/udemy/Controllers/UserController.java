package udemy.Controllers;

import udemy.core.models.User;
import udemy.persistance.UserDAO;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The controller class for the general user based requests
 * This class uses the data from the userResource layer, applies some logic
 * and requests the userDAO for data accordingly.
 */
public class UserController {

    private UserDAO userDAO;

    public UserController (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public User getUserById(int id){
        return userDAO.getUserById(id);
    }

    /**
     * The user should exist in the database before returning
     * This function checks this.
     * @param username
     * @param password
     * @return
     */
    public User authenticateUser(String username, String password){
        if(userDAO.checkUserExistence(username, password)) {
            return userDAO.getUserByLogin(username, password);
        }
        else {
            System.out.println("Geen user");
            return null;
        }
    }

    public boolean anyUserExists(){
        return userDAO.anyUserExists();
    }

    public int getLastUserId(){
        int lastUserId = 0;
        if(anyUserExists()){
            lastUserId = userDAO.getLastUserId();
        }
        return lastUserId;
    }

    /**
     * The auth integer is used to determine what type of account is registered (user,teacher,admin)
     * With a switch case the right variables are determined
     * @param username
     * @param password
     * @param auth
     */
    public void registerUser(String username, String password ,int auth){
        int newUserId = getLastUserId() + 1;
        boolean student = false;
        boolean teacher = false;
        boolean admin = false;
        switch (auth){
            case 1:
                student = true;
                break;
            case 2:
                teacher = true;
                break;
            case 3:
                admin = true;
                break;
        }
        userDAO.registerUser(newUserId,username,password, student, teacher, admin);
    }

    public Boolean checkUserExistence(String username, String password){
        return userDAO.checkUserExistence(username,password);
    }

    /**
     * If the user already exists, a 204 response for No Content Change is returned
     * otherwise a 200 OK is returned
     * @param username
     * @param password
     * @param auth
     * @return
     */
    public Response createRegisterResponse(String username, String password, int auth){
        if(!checkUserExistence(username, password)){
            registerUser(username,password, auth);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }


}
