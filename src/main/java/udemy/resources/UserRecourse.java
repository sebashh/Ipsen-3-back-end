package udemy.resources;

import udemy.Controllers.ClientController;
import udemy.Controllers.StudentController;
import udemy.Controllers.TeacherController;
import udemy.core.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ipsen3users")
public class UserRecourse {
    private ClientController clientController;
    private StudentController studentController;
    private TeacherController teacherController;

    public UserRecourse(ClientController clientController, StudentController studentController, TeacherController teacherController) {
        this.clientController= clientController;
        this.studentController = studentController;
        this.teacherController = teacherController;
    }

    @POST
    @Path("/client")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postClient(User user){
        clientController.uploadClient(user);
        return Response.status(200).build();
    }

    @POST
    @Path("/student")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postStudent(User user){
        studentController.uploadStudent(user);
        return Response.status(200).build();
    }

    @POST
    @Path("/teacher")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postTeacher(User user){
        teacherController.uploadTeacher(user);
        return Response.status(200).build();
    }

}
