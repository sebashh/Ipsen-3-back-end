package udemy.Mapper;

import udemy.core.models.Project;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to map the resultset of the obtained usersfrom the database to a User model
 * Rowmapper is a jdbi module used to replace the manual mapping of the resultset to a User model
 */
public class ProjectMapper implements RowMapper<Project> {

    @Override
    public Project map(ResultSet resultSet, StatementContext statementContext) throws SQLException{
        return new Project(resultSet.getInt("id"),resultSet.getString("title"), resultSet.getString("summary")
                , resultSet.getTimestamp("created_on"), resultSet.getInt("client_id"),resultSet.getString("study_name"), resultSet.getString("category_name"));
    }

}

//this.projectId = projectId;
//        this.title = title;
//        this.description = description;
//        this.study = study;
//        this.category = category;
//        this.createdOn = createdOn;
//        this.clientId = clientId;
//    }