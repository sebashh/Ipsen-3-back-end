package udemy.resources;

import udemy.Controllers.ClientController;
import udemy.core.models.Client;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;

@Path("/users/client")
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

    @GET
    @Path("/get={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClient(@PathParam("id") int id){
        Client client = clientController.getClient(id);
        return Response
                .status(200)
                .entity(client)
                .build();
    }

    @GET
    @Path("/logo/{path}")
    @Produces("image/jpeg")
    public Response getFullImage(@PathParam("path") String path) throws IOException, URISyntaxException {

//        BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResource("Images/" + path));
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(image, "jpg", baos);
//        byte[] imageData = baos.toByteArray();

        File file = new File(getClass().getClassLoader().getResource("Images/" + path).toURI());
        FileInputStream fileInputStream = new FileInputStream(file);
        // uncomment line below to send non-streamed
        // return Response.ok(imageData).build();

        // uncomment line below to send streamed
        javax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.ok((Object) fileInputStream);
        responseBuilder.type("image/jpeg");
        responseBuilder.header("Content-Disposition", "filename=test.jpg");
        return responseBuilder.build();
    }

    @PUT
    @Path("/clientUpdate")
    public void updateClient(Client client){
        clientController.updateClient(client);
    }
}
