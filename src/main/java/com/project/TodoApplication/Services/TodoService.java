package com.project.TodoApplication.Services;

import com.project.TodoApplication.Exceptions.ResourceNotFoundException;
import com.project.TodoApplication.Models.Todo;
import com.project.TodoApplication.Repositories.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TodoService {
    Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    TodoRepository repository;
    Random random = new Random();

    List<Todo> todoList = new ArrayList<>();
    public void create(Todo todo) {
        todo.setId(random.nextInt(9999999));
        Date current = new Date();
        todo.setCurrentdate(current);
        logger.info("currentdate of todo created {}:",current);
        logger.info("formated tododate{}",todo.getTododate());
        todoList.add(todo);
    }


    public List<Todo> getTodos() {
        logger.info("Returned all Todos{}",todoList);
        return todoList;
    }

    public Todo getByid(int id) {
        Todo todobyId = todoList.stream().filter(t -> t.getId()==id).findAny().orElseThrow(()-> new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Todo not Found For id "+ id));
        logger.info("Returned Get todo By Id{}{}",id,todobyId);
        return todobyId;
    }

    public Todo updateTodo(int id, Todo todo) {
        //search for id in list
        for (Todo todoold : todoList){
            //if id found
            if(todoold.getId()==id){
                //log the oldtodo info in that id
                logger.info("old todo {}",todoold);
                //replace oldtodo fields with newtodo
                todoold.setName(todo.getName());
                todoold.setContent(todo.getContent());
                todoold.setStatus(todo.isStatus());
                todoold.setCurrentdate(todo.getCurrentdate());
                todoold.setTododate(todo.getTododate());
                //log the newtodo data
                logger.info("new Todo {}",todoold);
                //return new todo
                return todoold;
            }
        }

        //if id not found return null
        throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Todo Not Found For ID:"+id);
    }


    public Todo deleteTodoByid(int deleteid) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == deleteid) {
                logger.info("deleting todo {}",todoList.get(i));
                return todoList.remove(i);
            }
        }

        throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Todo Not Found To Delete ID:"+ deleteid);
    }
}
