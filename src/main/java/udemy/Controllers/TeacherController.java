package udemy.Controllers;

import udemy.core.models.ExtendThisUser;
import udemy.core.models.Teacher;
import udemy.core.models.User;
import udemy.persistance.StudyDAO;
import udemy.persistance.TeacherDAO;
import udemy.persistance.UserDAO;

import java.util.ArrayList;
import java.util.List;


public class TeacherController {
    private UserDAO userDAO;
    private TeacherDAO teacherDAO;
    private StudyDAO studyDAO;

    public TeacherController(UserDAO userDAO, TeacherDAO teacherDAO, StudyDAO studyDAO) {

        this.userDAO = userDAO;
        this.teacherDAO = teacherDAO;
        this.studyDAO = studyDAO;
    }

    public void uploadTeacher(User user) {
        int id = userDAO.getStudyId(user.study);
        userDAO.uploadTeacher(id, user.email_user, user.password_user);

    }

    public List<Teacher> getTeacherInfo(){
        List<Teacher> teachers = new ArrayList<>();
        List<ExtendThisUser> users = teacherDAO.getAllTeachers();

        for(int i = 0; i < users.size(); i++){
            Teacher teacher = new Teacher(users.get(i), studyDAO.getStudyOfClient(users.get(i).id));
            teachers.add(teacher);
        }
        return teachers;
    }

    public void updateTeacher(Teacher teacher) {
        System.out.println(teacher.email);
    }
}
