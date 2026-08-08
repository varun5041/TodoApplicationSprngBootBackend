package com.project.TodoApplication.Services.Implementations;

import com.project.TodoApplication.Exceptions.ResourceNotFoundException;
import com.project.TodoApplication.Models.Todo;
import com.project.TodoApplication.Repositories.TodoRepository;
import com.project.TodoApplication.Services.Todoservices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Primary
@Service
public class SqlTodoServiceImpl implements Todoservices {

    Logger logger = LoggerFactory.getLogger(SqlTodoServiceImpl.class);
    @Autowired
    private TodoRepository repository;
    Random random = new Random();
    @Override
    public Todo create(Todo todo) {
        todo.setId(random.nextInt(9999999));
        Date current = new Date();
        todo.setCurrentdate(current);
        return repository.saveTodo(todo);
    }

    @Override
    public List<Todo> getTodos() {
        return repository.getAllTodos();
    }

    @Override
    public Todo getByid(int id) {

        if(repository.gettodo(id)==null){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Todo For ID "+ id +"Not Found");
        }
        return repository.gettodo(id);
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        repository.updateByID(id,todo);

        return repository.gettodo(id);
    }

    @Override
    public Todo deleteTodoByid(int deleteid) {
        return repository.deletedByID(deleteid);
    }

}
