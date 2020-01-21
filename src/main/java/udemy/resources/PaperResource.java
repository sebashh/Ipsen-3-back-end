package udemy.resources;

import udemy.Controllers.PaperController;
import udemy.core.models.Paper;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Path("/paper")
public class PaperResource {

    private PaperController paperController;

    public PaperResource(PaperController paperController){
        this.paperController = paperController;
    }

    @POST
    @Path("/upload")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadPaper(Paper paper){
        return paperController.confirmFileUpload(paper);
    }

    @GET
    @Path("/project={id}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPapersOfProject(@PathParam("id")int id){
        List<Paper> papers = paperController.getPapersOfProject(id);
        return Response
                .status(200)
                .entity(papers)
                .build();
    }

    @GET
    @Path("/pdf={name}")
    @Produces("application/pdf")
    public Response getPdf(@PathParam("name") String name) throws Exception
    {
        File file = new File(getClass().getClassLoader().getResource("PDF/" + name).toURI());
        FileInputStream fileInputStream = new FileInputStream(file);
        javax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.ok((Object) fileInputStream);
        responseBuilder.type("application/pdf");
        responseBuilder.header("Content-Disposition", "filename=test.pdf");
        return responseBuilder.build();
    }

    @GET
    @Path("/papers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paper> getPapers() {
        return paperController.retrievePaperData();
    }
}
