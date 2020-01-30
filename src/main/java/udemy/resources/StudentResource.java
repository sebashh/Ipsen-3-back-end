package udemy.resources;

import udemy.Controllers.StudentController;
import udemy.core.models.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/student")
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

    @PUT
    @Path("/studentUpdate")
    public void updateStudent(Student student){
         studentController.updateStudent(student);
    }
}
