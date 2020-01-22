package udemy.resources;

import udemy.Controllers.StudentController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/student")
public class StudentResource {
    private StudentController studentController;

    public StudentResource(StudentController studentController) {
        this.studentController= studentController;
    }

    @GET
    @Path("/getAllStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentInfo(){
        return Response
                .status(200)
                .entity(studentController.getStudentInfo())
                .build();
    }


}
