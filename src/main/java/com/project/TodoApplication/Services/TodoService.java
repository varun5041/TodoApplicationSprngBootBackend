package com.project.TodoApplication.Services;

import com.project.TodoApplication.Models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TodoService {
    Logger logger = LoggerFactory.getLogger(TodoService.class);

    Random random = new Random();

    List<Todo> todoList = new ArrayList<>();
    public void create(Todo todo) {
        todo.setId(random.nextInt(9999999));
        todoList.add(todo);
    }


    public List<Todo> getTodos() {
        logger.info("Returned all Todos{}",todoList);
        return todoList;
    }

    public Todo getByid(int id) {
        Todo todobyId = todoList.stream().filter(t -> t.getId()==id).findAny().get();
        logger.info("Returned Get todo By Id{}{}",id,todobyId);
        return todobyId;
    }

    public Todo updateTodo(int id, Todo todo) {
        for (Todo todoold : todoList){
            if(todoold.getId()==id){
                logger.info("old todo {}",todoold);
                todoold.setName(todo.getName());
                todoold.setContent(todo.getContent());
                todoold.setStatus(todo.isStatus());

                logger.info("new Todo {}",todoold);
                return todoold;
            }
        }

        return null;
    }


    public Todo deleteTodoByid(int deleteid) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() == deleteid) {
                logger.info("deleting todo {}",todoList.get(i));
                return todoList.remove(i);
            }
        }

        return null;
    }
}
