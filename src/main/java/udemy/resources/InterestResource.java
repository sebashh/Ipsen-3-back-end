package udemy.resources;


import udemy.Controllers.CategoryController;
import udemy.Controllers.InterestsController;
import udemy.core.models.Category;
import udemy.core.models.Interest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ipsen3interests")
public class InterestResource {
    private InterestsController interestsController;

    public InterestResource(InterestsController interestsController) {
        this.interestsController = interestsController;
    }

    @POST
    @Path("/interests")
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadInterests(Interest interest) throws InterruptedException {
        interestsController.uploadInterests(interest);
        return Response.status(200).build();

    }

}
