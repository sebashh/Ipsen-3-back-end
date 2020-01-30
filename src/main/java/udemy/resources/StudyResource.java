package udemy.resources;


import udemy.Controllers.CategoryController;
import udemy.Controllers.StudyController;
import udemy.core.models.Category;
import udemy.core.models.Study;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/studies")
public class StudyResource {
    private StudyController studyController;

    public StudyResource(StudyController studyController) {
        this.studyController= studyController;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Study> getStudies(){
        return studyController.getStudies();

    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudy(String study){
        if(studyController.addStudy(study))
            return Response.status(200)
                    .entity(true)
                    .build();
        else return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(false)
                .build();
    }

}
