package udemy.resources;

import udemy.Controllers.ClientController;
import udemy.core.models.Client;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class ClientResource {
    private ClientController clientController;

    public ClientResource(ClientController clientController) {
        this.clientController = clientController;
    }

    @GET
    @Path("/getAllClients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientInfo(){
        return Response
                .status(200)
                .entity(clientController.getClientInfo())
                .build();
    }

    @PUT
    @Path("/clientUpdate")
    public void updateClient(Client client){
        clientController.updateClient(client);
    }
}
