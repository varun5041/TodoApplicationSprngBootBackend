package com.project.TodoApplication.Repositories;

import com.project.TodoApplication.Helper.HelperClass;
import com.project.TodoApplication.Models.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TodoRowMapper implements RowMapper<Todo> {

    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {

        Todo todo = new Todo();
        todo.setId(rs.getInt("id"));
        todo.setName(rs.getString("name"));
        todo.setContent( rs.getString("content"));
        //sql returns LocalDateTime for Sql DataType DATETIME so we need to Convert it to Type of our field ->Date
        todo.setCurrentdate(HelperClass.parseDate((LocalDateTime) rs.getObject("currentdate")));
        todo.setTododate(HelperClass.parseDate((LocalDateTime) rs.getObject("tododate")));


        return todo;
    }
}
