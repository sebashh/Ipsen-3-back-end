package udemy.Controllers;

import udemy.core.models.Client;
import udemy.core.models.ExtendThisUser;
import udemy.core.models.User;
import udemy.persistance.ClientDAO;
import udemy.persistance.UserDAO;

import java.util.ArrayList;
import java.util.List;


public class ClientController {
    private UserDAO userDAO;
    private ClientDAO clientDAO;

    public ClientController(UserDAO userDAO, ClientDAO clientDAO) {
        this.userDAO = userDAO;
        this.clientDAO = clientDAO;
    }

    public Client getClient(int id){
        return clientDAO.getClient(id);
    }

    public void uploadClient(User user) {

        userDAO.uploadClient(user.picture_company, user.name_company, user.description_company, user.email_user, user.password_user);

    }

    public List<Client> getClientInfo(){
        return clientDAO.getAllClients();
    }

    public void updateClient(Client client) {
        userDAO.updateUser(client.id, client.email);
        clientDAO.updateClient(client.id, client.company_name, client.description);
    }
}
