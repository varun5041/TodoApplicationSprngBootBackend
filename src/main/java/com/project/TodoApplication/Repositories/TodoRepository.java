package com.project.TodoApplication.Repositories;

import com.project.TodoApplication.Models.Todo;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.URIParameter;

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
                        "currentdate DATE, " +
                        "tododate DATE" +
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

    //get all todos

    //update todo by id

    //get todo by id

    //delete todo


}
