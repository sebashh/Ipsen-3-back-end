package udemy.Controllers;

import udemy.core.models.PaperModel;
import udemy.persistance.PaperDAO;

import java.util.List;

public class PaperController {
    private PaperDAO paperDAO;

    public PaperController(PaperDAO paperDAO) {
        this.paperDAO = paperDAO;
    }

    public List<PaperModel> retrievePaperData() {
//        List<PaperModel> papers = paperDAO.getPapers();
//        for (PaperModel paper : papers){
//            System.out.println("paper: " + paper.title);
//        }
        return paperDAO.getPapers();
    }
}
