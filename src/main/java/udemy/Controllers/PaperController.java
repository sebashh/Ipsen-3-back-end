package udemy.Controllers;

import udemy.core.models.Paper;
import udemy.persistance.PaperDAO;

import javax.ws.rs.core.Response;
import java.io.*;

public class PaperController {

    private PaperDAO paperDAO;
    private String path = System.getProperty("user.home") + "\\Desktop\\";
    private String pdfHexCode = "25504446";

    public PaperController(PaperDAO paperDAO) {
        this.paperDAO = paperDAO;
    }

    public Response confirmUploadFile(Paper paper) {
        if (checkFileType(paper)) {
            uploadFile(paper);
            return Response.status(200).build();
        }
        return Response.status(422).build();
    }

    public boolean checkFileType(Paper paper) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(String.format("%02x", paper.getPaperFile()[i]));
        }
        return sb.toString().equals(pdfHexCode);
    }

    private void uploadFile(Paper paper) {
        int newPaperId = getNewPaperId();
        String filePath = paper.getTitle() + newPaperId;
        File file = new File(path+filePath);
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(paper.getPaperFile());
            paperDAO.uploadPaper(newPaperId, paper.getProjectId(), paper.getTitle(),
                    paper.getAuthor(), paper.getUploadedBy(), filePath);
            System.out.println("file created and saved");
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getNewPaperId() {
        int lastProjectId = 0;
        if (anyProjectsExist()) {
            lastProjectId = paperDAO.getLastPaperId();
        }
        return lastProjectId + 1;
    }

    public boolean anyProjectsExist() {
        return paperDAO.anyPaperExist();

    }
}
