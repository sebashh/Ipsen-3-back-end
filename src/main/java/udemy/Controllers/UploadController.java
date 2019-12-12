package udemy.Controllers;

import udemy.core.models.Project;
import udemy.persistance.UploadDAO;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

/**
 * The controller class for the uploading of project based requests
 * This class uses the data from the uploadResource layer, applies some logic
 * and requests the uploadDAO for data accordingly.
 */
public class UploadController {
    private UploadDAO uploadDAO;

    private String pdfHexCode = "25504446";

    public UploadController(UploadDAO uploadDAO){
        this.uploadDAO = uploadDAO;
    }

    public boolean isATeacher(int user_id){ return uploadDAO.isATeacher(user_id); }

    public boolean anyProjectsExist(){
        return uploadDAO.anyProjectsExist();
    }

    /**
     * Get the highest project id from the database for further use
     * @return
     */
    public int getLastProjectId(){
        int lastProjectId = 0;
        if(anyProjectsExist()){
            lastProjectId = uploadDAO.getLastProjectId();
        }
        return lastProjectId;
    }

    /**
     * firstly the file is checked if it is a pdf file
     * afterwards the user is checked if it is a teacher
     * thirdly the project statistics are created for the project and a response is send back to the frontend
     * @param project
     * @return
     */
    public Response uploadProject(Project project){
        checkIfFileIsPDF(project.getPdfFile());
        if(isATeacher(project.getUploadedBy()) && checkIfFileIsPDF(project.getPdfFile())) {
            int newProjectId = getLastProjectId() + 1;
            uploadDAO.uploadProject(newProjectId, project.getAuthor(), project.getProjectName(), project.getCategory(),
                    LocalDate.now().toString(), 0, project.getStudy(), project.getCourse(),
                    project.getUploadedBy(), project.getPdfFile());
            createProjectStatistics(newProjectId);
            return Response.ok().build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * In order to check if a file is a pdf the first 4 bits are always the same
     * the first 4 bits of the file is compared to the hexcode of the 4 first pdf bits
     * @param pdfFile
     * @return
     */
    private boolean checkIfFileIsPDF(byte[] pdfFile) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <4; i++) {
            sb.append(String.format("%02x", pdfFile[i]));
        }
        return sb.toString().equals(pdfHexCode);
    }

    private void createProjectStatistics(int id) {
        uploadDAO.createProjectStatistics(id);
    }


    public List<Project> getProjectsUploadedBy(int userId) {
        return uploadDAO.getProjectsUploadedBy(userId);
    }

    public Response deleteProject(int projectId) {
        uploadDAO.deleteProject(projectId);
        return Response.ok().build();
    }
}
