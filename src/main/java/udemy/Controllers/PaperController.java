package udemy.Controllers;

import udemy.core.models.Paper;
import udemy.persistance.PaperDAO;
import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

public class PaperController {

    private PaperDAO paperDAO;
    private String path = getClass().getClassLoader().getResource("PDF/").getPath();
    private String requiredFileType = "application/pdf";
    private int requiredStringLength = 8;

    public PaperController(PaperDAO paperDAO) {
        this.paperDAO = paperDAO;
    }

    public List<Paper> retrievePaperData() {
        return paperDAO.getPapers();
    }

        public void uploadFile (Paper paper){
            byte[] decoder = Base64.getDecoder().decode(paper.paperFile.split(",")[1]);
            FileOutputStream fos = null;
            String filePath = (paper.title.substring(0, checkStringLength(paper.title) ? requiredStringLength : paper.title.length())) + "_" + getCurrentDateTime() + ".pdf";
            uploadFilePathToDatabase(paper, filePath);
            try {
                fos = new FileOutputStream(path + filePath);
                fos.write(decoder);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private boolean checkStringLength (String totalString){
            return totalString.length() >= requiredStringLength;
        }

        private void uploadFilePathToDatabase (Paper paper, String filePath){
            paperDAO.uploadPaper(paper.projectId, paper.title, paper.author, paper.uploadedBy, filePath);
        }

        public Response confirmFileUpload (Paper paper){
            if (checkFileType(paper)) {
                uploadFile(paper);
                System.out.println("response: " + Response.status(200).build());
                return Response.status(200).build();
            }
            System.out.println("response: " + Response.status(415).build());
            return Response.status(415).build();

        }

        private String getCurrentDateTime () {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            return dateTimeFormatter.format(currentDateTime);
        }

        private boolean checkFileType (Paper paper){
            return paper.paperFile.split(",")[0].contains(requiredFileType);
        }


        public List<Paper> getPapersOfProject ( int id){
            List<Paper> papers = paperDAO.getProjectPapers(id);
            return papers;
        }

        public void deletePaper(int id){
            System.out.println("Sent ID is :" + id);
            paperDAO.deletePaper(id);
        }

    public void updatePaper(Paper paper) {
        paperDAO.updatePaper(paper.id, paper.title, paper.author, paper.paperFile);
    }

    public int getPaperAmount(int id) {
        return paperDAO.getPaperAmount(id);
    }
}
