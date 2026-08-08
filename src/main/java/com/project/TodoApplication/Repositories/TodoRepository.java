package com.project.TodoApplication.Repositories;

import com.project.TodoApplication.Helper.HelperClass;
import com.project.TodoApplication.Models.Todo;
import org.jspecify.annotations.Nullable;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.URIParameter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TodoRepository {

    Logger logger = LoggerFactory.getLogger(TodoRepository.class);

    private JdbcTemplate template;

    //constructor injection
    public TodoRepository(@Autowired JdbcTemplate template) {
        this.template = template;
        //create table if does not exists
        String createTableQuery =
                "CREATE TABLE IF NOT EXISTS todolist (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(100) NOT NULL, " +
                        "content VARCHAR(500) NOT NULL, " +
                        "status BOOLEAN DEFAULT FALSE, " +
                        "currentdate DATETIME,"+
                        "tododate DATETIME"+
                        ");";

        int update = template.update(createTableQuery);
        logger.info("TODO TABLE CREATED{}", update)   ;
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //Create todo in database
    public Todo saveTodo(Todo todo){
        String insertquery=" insert into todolist(id,name,content,status,currentdate,tododate) values(?,?,?,?,?,?)";
        int update = template.update(insertquery,
                todo.getId(),
                todo.getName(),
                todo.getContent(),
                todo.isStatus(),
                todo.getCurrentdate(),
                todo.getTododate()
        );
        logger.info("JDBC OPERATION : Rows Inserted : {}",update);
        return todo;
    }

    //get a todo by id
    public Todo gettodo(int id){
        String getbyidQuery="select * from todolist where id = ?";
                                            // query      //rowmapper        //arguments
        Todo todo = template.queryForObject(getbyidQuery,new TodoRowMapper(),id);
        logger.info("Fetched Todo by ID:{}",todo);
        return todo;
    }

    //get all the todos
    public List<Todo> getAllTodos(){
        String GetallQuery="SELECT * FROM todolist";
        List<Todo> allTodos = template.query(GetallQuery,new TodoRowMapper());
        logger.info("ALL TODOS : {}", allTodos);
        return allTodos;
    }

    //update todo by id
    public Todo updateByID(int id,Todo newtodo){
        Todo oldtodo = gettodo(id);

        String UpdateQuery="update todolist set name=?,content=?,status=?,currentdate=?,tododate=? WHERE id=?";
        int update = template.update(
                UpdateQuery,
                newtodo.getName() == null ? oldtodo.getName() : newtodo.getName(),
                newtodo.getContent() == null ? oldtodo.getContent() : newtodo.getContent(),
                newtodo.isStatus(),
                newtodo.getCurrentdate() == null ? oldtodo.getCurrentdate() : newtodo.getCurrentdate(),
                newtodo.getTododate() == null ? oldtodo.getTododate() : newtodo.getTododate(),
                id
        );
        logger.info("Todo Updated On DB:{}",update);
        newtodo.setId(id);
        return newtodo;
    }

    //delete todo
    public Todo deletedByID(int id){
        Todo DeletedTodo= gettodo(id);
        String DeleteQuery="DELETE FROM todolist where id=?";
        int update = template.update(DeleteQuery,id);
        logger.info("Todo Deleted :{}",update);
        return DeletedTodo;
    }

    public void MultipleDelete(int[] ids){
        String MultipleDeleteQuery = "DELETE FROM todolist where id=?";

        template.batchUpdate(MultipleDeleteQuery, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1,id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        logger.info("Rows Deleted :{}",ids.length);
    }
}
