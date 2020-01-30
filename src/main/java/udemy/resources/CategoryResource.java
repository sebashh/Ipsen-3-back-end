package udemy.resources;


import udemy.Controllers.CategoryController;
import udemy.Controllers.ProjectController;
import udemy.core.models.Category;
import udemy.core.models.Paper;
import udemy.core.models.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
public class CategoryResource {
    private CategoryController categoryController;

    public CategoryResource(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories(){
        return Response
                .status(200)
                .entity(categoryController.getCategories())
                .build();

    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCategory(String category){
        if(categoryController.addCategory(category))
            return Response.status(200)
                    .entity(true)
                    .build();
        else return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(false)
                .build();
    }

}
