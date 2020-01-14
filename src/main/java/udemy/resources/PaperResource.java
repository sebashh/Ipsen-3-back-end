package udemy.resources;

import udemy.Controllers.PaperController;
import udemy.core.models.Paper;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadPaper(Paper paper){
        System.out.println("testing: " + paper.getTitle());
        System.out.println("date: " + paper.getUploadDate());
        return paperController.confirmFileUpload(paper);
    }


}
