package udemy.Controllers;

import udemy.core.models.Category;
import udemy.core.models.Study;
import udemy.persistance.CategoryDAO;
import udemy.persistance.StudyDAO;

import java.util.List;


public class StudyController {
    private StudyDAO studyDAO;

    public StudyController(StudyDAO studyDAO) {
        this.studyDAO = studyDAO;
    }

    public boolean addStudy(String study){
        return studyDAO.addStudy(study);
    }
    public List<Study> getStudies(){
        return studyDAO.getStudies();
    }


}
