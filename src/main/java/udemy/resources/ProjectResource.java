package udemy.resources;

import udemy.core.models.Project;
import udemy.persistance.ProjectDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/projects")
public class ProjectResource {

    private ProjectDAO projectDAO;
    private int startingIndex;
    private int amount = 30;



    public ProjectResource(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @GET
    @Path("/all/{pageNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectsByPage(@PathParam("pageNumber") int pageNumber){
        startingIndex = Math.max(0,(pageNumber-1)*amount);
        List<Project> projects = projectDAO.getProjectByPage(amount,startingIndex);
        return projects;
    }


    @GET
    @Path("/projectId={projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProjectById(@PathParam("projectId")int projectId){
        Project project = projectDAO.getProjectById(projectId);
        System.out.println("project opgeroepen: " +project.getProjectName() + project.getPdfFile());
        //pdfConvertService.convertBinaryToPdf(filePath,outputFile, project.getPdfFile());
        return project;
    }


    @GET
    @Path("/any")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean anyProjectsExist(){
        return projectDAO.anyProjectsExist();
    }

    @GET
    @Path("/last")
    @Produces(MediaType.TEXT_PLAIN)
    public int getLastProjectId(){
        int lastProjectId = 0;
        if(anyProjectsExist()){
            lastProjectId = projectDAO.getLastProjectId();
        }
        return lastProjectId;
    }


    @GET
    @Path("/id/{projectId}/pdf")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] getProjectFileById(@PathParam("projectId") int projectId){
        return projectDAO.getProjectFileById(projectId);
    }

    @GET
    @Path("/favourited/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getFavProjects(@PathParam("userid") int userid){
        return projectDAO.getFavouritedProjects(userid);
    }


    @GET
    @Path("/filter/{title: .*}+{author: .*}+{category: .*}+{study: .*}+{course: .*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectFile(@PathParam("title") String title, @PathParam("author") String author, @PathParam("category") String category, @PathParam("study") String study, @PathParam("course") String course){
        System.out.println("dit zijn de waardes: " + title+ author+ category+study+course);
        return projectDAO.getProjectFile(title, author, category, study, course);
    }

    @GET
    @Path("/filter/{title: .*}+{author: .*}+{category: .*}+{study: .*}+{course: .*}/count")
    @Produces(MediaType.APPLICATION_JSON)
    public int getProjectsCount(@PathParam("title") String title, @PathParam("author") String author, @PathParam("category") String category, @PathParam("study") String study, @PathParam("course") String course){
        System.out.println("dit zijn de waardes: " + title+ author+ category+study+course);
        return projectDAO.getProjectsAmount(title, author, category, study, course);
    }

    @GET
    @Path("/filter/{title: .*}+{author: .*}+{category: .*}+{study: .*}+{course: .*}/{pageNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectsByPageWithFilters(@PathParam("pageNumber") int pageNumber, @PathParam("title") String title,
                                                      @PathParam("author") String author, @PathParam("category") String category,
                                                      @PathParam("study") String study, @PathParam("course") String course){
        startingIndex = Math.max(0,(pageNumber-1)*amount);
        System.out.println("showing files from page " + pageNumber + " filtered");
        title = title.replace("_", " ");
        author = author.replace("_", " ");
        category = category.replace("_", " ");
        study = study.replace("_", " ");
        course = course.replace("_", " ");

        return projectDAO.getProjectsByPageWithFilters(startingIndex, amount, title, author, category,study, course);
    }

    @GET
    @Path("/category/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCategories(){
        return projectDAO.getCategories();
    }

    @GET
    @Path("/study/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getStudy(){
        return projectDAO.getStudies();
    }

    @GET
    @Path("/course/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCourse(){
        return projectDAO.getCourses();
    }

    @GET
    @Path("/top")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getTopProjects(){
        return projectDAO.getTopProjects();
    }

}
/*

// VOOR TESTEN BEDOELD - NIET HERGEBRUIKEN
    private PdfConvertService pdfConvertService = new PdfConvertService();
    private String filePath = "C:\\Users\\Jaime\\Desktop";
    private String filename = "\\kingsburg-rules.pdf";
    private String outputFile = "test.pdf";
    // VOOR TESTEN BEDOELD - NIET HERGEBRUIKEN


    @PUT
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_JSON)
    public void uploadProject( Project project){
        int newProjectId = getLastProjectId() + 1;
        projectDAO.uploadProject(newProjectId,project.getAuthor(), project.getProjectName(),project.getCategory(),
                project.getUpload_date(),project.getLikes(),project.getStudy(),project.getCourse(),
                project.getUploaded_by(),project.getPdfFile());
        System.out.println("project uploaded");
    }


    // VOOR TESTEN VAN PROJECT - NIET HERGEBRUIKEN
    @PUT
    @Path("/test/upload")
    public void testUploadProject (){
        LocalDate localDate = LocalDate.of(2018,5,12);
        Project project = new Project(0,"author","name","category",
                localDate,0,"study","course",2,
                pdfConvertService.convertPdfToBinary(filePath+filename));
        uploadProject(project);
        System.out.println("testing uploading project");
    }
    // VOOR TESTEN VAN PROJECT - NIET HERGEBRUIKEN



 */