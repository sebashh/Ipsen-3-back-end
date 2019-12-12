package udemy.resources;


import udemy.Controllers.Ipsen3ProjectController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/ipsen3projects")
public class Ipsen3ProjectResource {
    private Ipsen3ProjectController ipsen3ProjectController;

    public Ipsen3ProjectResource(Ipsen3ProjectController ipsen3ProjectController) {
        this.ipsen3ProjectController= ipsen3ProjectController;
    }

    @GET
    @Path("/file{projectId}")
    @Produces(MediaType.TEXT_PLAIN)
    public byte[] getProjectsByPage(@PathParam("projectId") int id){
        return ipsen3ProjectController.getProjectFile(id);
    }




    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTest(){
        return "testing lol";
    }


}
