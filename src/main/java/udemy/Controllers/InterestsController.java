package udemy.Controllers;


import udemy.core.models.Interest;
import udemy.persistance.InterestsDAO;

public class InterestsController {
    private InterestsDAO interestsDAO;

    public InterestsController(InterestsDAO interestsDAO) {
        this.interestsDAO = interestsDAO;
    }

    public void uploadInterests(Interest interest) throws InterruptedException {
        Thread.sleep(200);
        interestsDAO.uploadInterests(interestsDAO.getUserId(interest.email), interest.category_id);
    }

}
