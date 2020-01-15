package udemy.resources;

import udemy.Controllers.PaperController;
import udemy.core.models.PaperModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("paper")
public class PaperResource {
    private PaperController paperController;

    public PaperResource(PaperController paperController) {
        this.paperController = paperController;
    }

    @GET
    @Path("/papers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PaperModel> getPapers() {
        return paperController.retrievePaperData();
    }
}
