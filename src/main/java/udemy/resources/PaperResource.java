package udemy.resources;

import udemy.Controllers.PaperController;
import udemy.core.models.Paper;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/paper")
public class PaperResource {

    private PaperController paperController;

    public PaperResource(PaperController paperController){
        this.paperController = paperController;
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadPaper(Paper paper){
        return paperController.confirmUploadFile(paper);
    }


}
