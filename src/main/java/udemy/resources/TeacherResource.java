package udemy.resources;

import udemy.Controllers.TeacherController;
import udemy.core.models.Teacher;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/teacher")
public class TeacherResource {
    private TeacherController teacherController;

    public TeacherResource(TeacherController teacherController) {
        this.teacherController = teacherController;
    }

    @GET
    @Path("/getAllTeachers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacherInfo(){
        return Response
                .status(200)
                .entity(teacherController.getTeacherInfo())
                .build();
    }

    @PUT
    @Path("/teacherUpdate")
    public void updateTeacher(Teacher teacher){
        teacherController.updateTeacher(teacher);
    }
}
