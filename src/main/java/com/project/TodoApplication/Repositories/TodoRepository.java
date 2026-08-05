package com.project.TodoApplication.Repositories;

import com.project.TodoApplication.Helper.HelperClass;
import com.project.TodoApplication.Models.Todo;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.URIParameter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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

    //get all todos

    public Todo gettodo(int id){
        //Query for getting row by id
        String getbyidQuery="select * from todolist where id = ?";
        //row columns and value stored in Map get
        //| KEY  |  VALUE |
        //(String)(Object)|
        //|------|------- |
        //| "id" | "12345"|
        //|"name"| "work" |
        Map<String,Object> todoData=template.queryForMap(getbyidQuery,id);
        Set<Map.Entry<String, Object>> entrySet = todoData.entrySet();
        for(Map.Entry<String,Object> i : entrySet){
            System.out.println(i.getKey() +" : " + i.getValue());
        }
        logger.info("Fetched Todo by ID:{}",todoData);
        //creating Object and setting fetched values to return
        Todo todo = new Todo();
        todo.setId((int)todoData.get("id"));
        todo.setName((String)todoData.get("name"));
        todo.setContent((String) todoData.get("content"));
        //sql returns LocalDateTime for Sql DataType DATETIME so we need to Convert it to Type of our field ->Date
        todo.setCurrentdate(HelperClass.parseDate((LocalDateTime) todoData.get("currentdate")));
        todo.setCurrentdate(HelperClass.parseDate((LocalDateTime) todoData.get("currentdate")));
        todo.setTododate(HelperClass.parseDate((LocalDateTime) todoData.get("tododate")));
        //return Todo fetched
        return todo;
    }

    //update todo by id

    //get todo by id

    //delete todo


}
