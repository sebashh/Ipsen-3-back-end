package udemy.resources;

import udemy.Services.PdfConvertService;
import udemy.core.models.Project;
import udemy.persistance.UploadDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/upload")
public class UploadResource {

    private UploadDAO uploadDAO;

    // VOOR TESTEN BEDOELD - NIET HERGEBRUIKEN
    private PdfConvertService pdfConvertService = new PdfConvertService();
    private String filePath = "C:\\Users\\Jaime\\Desktop";
    private String filename = "\\kingsburg-rules.pdf";
    private String outputFile = "test.pdf";
    // VOOR TESTEN BEDOELD - NIET HERGEBRUIKEN

    public UploadResource(UploadDAO uploadDAO) {
        this.uploadDAO = uploadDAO;
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isATeacher(int user_id){
        System.out.println("is a teacher? " + uploadDAO.isATeacher(user_id));
        return uploadDAO.isATeacher(user_id);
    }

    @GET
    @Path("/any")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean anyProjectsExist(){
        return uploadDAO.anyProjectsExist();
    }

    @GET
    @Path("/last")
    @Produces(MediaType.TEXT_PLAIN)
    public int getLastProjectId(){
        int lastProjectId = 0;
        if(anyProjectsExist()){
            lastProjectId = uploadDAO.getLastProjectId();
        }
        return lastProjectId;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/upload")
    public Response uploadProject( Project project){
        System.out.println(project.getUploadedBy());
        if(isATeacher(project.getUploadedBy())) {
            int newProjectId = getLastProjectId() + 1;
            uploadDAO.uploadProject(newProjectId, project.getAuthor(), project.getProjectName(), project.getCategory(),
                    LocalDate.now().toString(), 0, project.getStudy(), project.getCourse(),
                    project.getUploadedBy(), project.getPdfFile());
            System.out.println("project uploaded");
            System.out.println("response goed: " + Response.ok().build());
            return Response.ok().build();
        }
        System.out.println("response fout: " + Response.status(Response.Status.UNAUTHORIZED).build());
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }



    // VOOR TESTEN VAN PROJECT - NIET HERGEBRUIKEN
    @POST
    @Path("/test/upload")
    public void testUploadProject (){
        Project project = new Project(0,"test900","epicjsjs","categoryjs",
                null,0,"study","course",1,
                pdfConvertService.convertPdfToBinary(filePath+filename));
        uploadProject(project);
        System.out.println("testing uploading project");
    }
    // VOOR TESTEN VAN PROJECT - NIET HERGEBRUIKEN

}
