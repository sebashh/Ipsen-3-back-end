package udemy.Controllers;

import udemy.core.models.Paper;
import udemy.persistance.PaperDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

public class PaperController {

    private PaperDAO paperDAO;
    private String path = System.getProperty("user.home") + "\\Desktop\\";
    private String requiredFileType = "application/pdf";
    private int requiredStringLength = 8;

    public PaperController(PaperDAO paperDAO) {
        this.paperDAO = paperDAO;
    }


    public void uploadFile(Paper paper) {
        byte[] decoder = Base64.getDecoder().decode(paper.getPaperFile().split(",")[1]);
        FileOutputStream fos = null;
        String filePath = (paper.getTitle().substring(0, checkStringLength(paper.getTitle())? requiredStringLength : paper.getTitle().length())) + "_" + getCurrentDateTime();
        uploadFilePathToDatabase(paper, filePath);
        try {
            fos = new FileOutputStream(path + filePath + ".pdf");
            fos.write(decoder);
            fos.close();
            System.out.println("file saved to desktop");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkStringLength(String totalString){
        return totalString.length() >= requiredStringLength;
    }

    private void uploadFilePathToDatabase(Paper paper, String filePath) {
        paperDAO.uploadPaper(paper.getProjectId(), paper.getTitle(), paper.getAuthor(), paper.getUploadedBy(), filePath);
    }

    public Response confirmFileUpload(Paper paper) {
        if (checkFileType(paper)) {
            uploadFile(paper);
            System.out.println("response: " + Response.status(200).build());
            return Response.status(200).build();
        }
        System.out.println("response: " + Response.status(415).build());
        return Response.status(415).build();

    }

    private String getCurrentDateTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return dateTimeFormatter.format(currentDateTime);
    }

    private boolean checkFileType(Paper paper) {
        return paper.getPaperFile().split(",")[0].contains(requiredFileType);
    }


    public List<Paper> getPapersOfProject(int id) {
        List<Paper> papers = paperDAO.getProjectPapers(id);
        return papers;
    }
}
