package udemy.resources;

import io.dropwizard.auth.Auth;
import udemy.Controllers.PaperController;
import udemy.auth.AuthUser;
import udemy.core.models.Paper;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

@Path("/paper")
public class PaperResource {

    private PaperController paperController;

    public PaperResource(PaperController paperController){
        this.paperController = paperController;
    }

    @POST
    @Path("/upload")
    @RolesAllowed({"teacher", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadPaper(Paper paper,@Auth Optional<AuthUser> user){
        System.out.println(paper.uploadedBy);
        if(paper.uploadedBy == 0) {
            int userId = Integer.parseInt(user.get().getName());
            paper.uploadedBy = userId;
        }
            return paperController.confirmFileUpload(paper);

    }

    @GET
    @Path("/project={id}/amount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaperAmountOfProject(@PathParam("id")int id){
        int amount = paperController.getPaperAmount(id);
        return Response
                .status(200)
                .entity(amount)
                .build();
    }


    @GET
    @Path("/project={id}")
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

    @DELETE
    @Path("/delete={id}")
    public void delete(@PathParam("id") int id) {
        paperController.deletePaper(id);
    }

    @PUT
    @Path("/paperUpdate")
    public void updatePaper(Paper paper){
        paperController.updatePaper(paper);
    }
}
