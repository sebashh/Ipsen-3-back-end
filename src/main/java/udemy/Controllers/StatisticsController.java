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
        return projects;
    }


    public ArrayList<Project> getTopPopularProjects(){
        return getProjectVariables(statisticsDAO.getTopProjects());
    }

    public ArrayList<Integer> getRecentUserStatistics(int studentId) {
        ArrayList<Integer> recentStudentStatistics = new ArrayList<>();
        recentStudentStatistics.add(statisticsDAO.getRecentProjectsAmount(studentId));
        recentStudentStatistics.add(statisticsDAO.getRecentPapersAmount(studentId));
        return recentStudentStatistics;
    }

    public ArrayList<Integer> getRecentTeacherStatistics(int teacherId) {
        ArrayList<Integer> recentTeacherStatistics = new ArrayList<>();
        recentTeacherStatistics.add(statisticsDAO.getRecentProjectsAmount(teacherId));
        recentTeacherStatistics.add(statisticsDAO.getRecentProjectViewed(teacherId));
        recentTeacherStatistics.add(statisticsDAO.getRecentTotalViews(teacherId));
        return recentTeacherStatistics;
    }

    public ArrayList<Integer> getRecentClientStatistics(int userId){
        ArrayList<Integer> recentClientStatistics = new ArrayList<>();
        recentClientStatistics.add(statisticsDAO.getRecentTotalViewsProject(userId));
        recentClientStatistics.add(statisticsDAO.getRecentUploads(userId));
        recentClientStatistics.add(statisticsDAO.getTotalProjectsClient(userId));
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
