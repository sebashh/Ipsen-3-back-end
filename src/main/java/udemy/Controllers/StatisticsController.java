package udemy.Controllers;

import udemy.core.models.DateStatistic;
import udemy.core.models.Project;
import udemy.core.models.Statistics;
import udemy.persistance.StatisticsDAO;

import java.util.*;

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

    public ArrayList<Integer> getRecentUserStatistics(String studentName) {
        ArrayList<Integer> recentStudentStatistics = new ArrayList<>();
        recentStudentStatistics.add(statisticsDAO.getRecentProjectsAmount(studentName));
        recentStudentStatistics.add(statisticsDAO.getRecentPapersAmount(studentName));
        return recentStudentStatistics;
    }

    public ArrayList<Integer> getRecentTeacherStatistics(String teacherName) {
        ArrayList<Integer> recentTeacherStatistics = new ArrayList<>();
        recentTeacherStatistics.add(statisticsDAO.getRecentProjectsAmount(teacherName));
        recentTeacherStatistics.add(statisticsDAO.getRecentProjectViewed(teacherName));
        recentTeacherStatistics.add(statisticsDAO.getRecentTotalViews(teacherName));
        return recentTeacherStatistics;
    }

    public ArrayList<Integer> getRecentClientStatistics(String clientName){
        ArrayList<Integer> recentClientStatistics = new ArrayList<>();
        recentClientStatistics.add(statisticsDAO.getRecentTotalViewsProject(clientName));
        recentClientStatistics.add(statisticsDAO.getRecentUploads(clientName));
        recentClientStatistics.add(statisticsDAO.getTotalProjectsClient(clientName));
        return recentClientStatistics;
    }

    public List<DateStatistic> getAdminProjectStatistics(){
        return statisticsDAO.getAdminProjectStatistics();
    }

    public List<DateStatistic> getAdminPaperStatistics(){
        return statisticsDAO.getAdminPaperStatistics();
    }

    public List<DateStatistic> getAdminViewStatistics(){
        return statisticsDAO.getAdminViewStatistics();
    }

    public List<DateStatistic> getAdminLoginStatistics(String userType){
        switch(userType){
            case "student":
                return statisticsDAO.getAdminLoginStudentStatistics();
            case "teacher":
                return statisticsDAO.getAdminLoginTeacherStatistics();
            case "client":
                return statisticsDAO.getAdminLoginClientStatistics();
        }
        return null;
    }

}
