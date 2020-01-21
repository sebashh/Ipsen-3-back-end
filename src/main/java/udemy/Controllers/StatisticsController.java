package udemy.Controllers;

import udemy.core.models.Project;
import udemy.core.models.Statistics;
import udemy.persistance.StatisticsDAO;

import java.util.ArrayList;

public class StatisticsController {

    StatisticsDAO statisticsDAO;

    public StatisticsController (StatisticsDAO statisticsDAO){
        this.statisticsDAO = statisticsDAO;
    }

    public Statistics getAllStatistics(){
        int userAmount = statisticsDAO.getTotalUsers();
        int studentAmount = statisticsDAO.getTotalStudents();
        int teacherAmount = statisticsDAO.getTotalTeachers();
        int clientAmount = statisticsDAO.getTotalClients();
        int projectAmount = statisticsDAO.getTotalProjects();
        Statistics statistics = new Statistics(userAmount,studentAmount,teacherAmount,clientAmount,projectAmount);
        return statistics;
    }

    private ArrayList<Project> getProjectVariables(ArrayList<Project> projects) {
        for (Project project: projects){
            project.category = statisticsDAO.getCategory(Integer.parseInt(project.category));
            project.study = statisticsDAO.getStudy(Integer.parseInt(project.study));
        }
        return projects;
    }


    public ArrayList<Project> getTopPopularProjects(){
        return getProjectVariables(statisticsDAO.getTopProjects());
    }

}
