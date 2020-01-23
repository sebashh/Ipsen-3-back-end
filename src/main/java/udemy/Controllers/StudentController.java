package udemy.Controllers;

//import udemy.core.models.Student;
import udemy.core.models.ExtendThisUser;
import udemy.core.models.Student;
import udemy.core.models.User;
import udemy.persistance.StudentDAO;
import udemy.persistance.StudyDAO;
import udemy.persistance.UserDAO;

import java.util.ArrayList;
import java.util.List;


public class StudentController {
    private UserDAO userDAO;
    private StudentDAO studentDAO;
    private StudyDAO studyDAO;

//    private Student student;

    public StudentController(UserDAO userDAO, StudentDAO studentDAO, StudyDAO studyDAO) {

        this.userDAO = userDAO;
        this.studentDAO = studentDAO;
        this.studyDAO = studyDAO;
    }

    public void uploadStudent(User user) {
        int id = userDAO.getStudyId(user.study);
        userDAO.uploadStudent(id, user.email_user, user.password_user);
    }

    public List<Student> getStudentInfo(){
        List<Student> students = new ArrayList<>();
        List<ExtendThisUser> users = studentDAO.getAllStudents();

        for(int i = 0; i < users.size(); i++){
            Student student = new Student(users.get(i), studyDAO.getStudyOfClient(users.get(i).id));
            students.add(student);
        }
        return students;
    }
}
