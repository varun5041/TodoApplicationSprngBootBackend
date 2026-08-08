package com.project.TodoApplication.Services;

import com.project.TodoApplication.Models.Todo;

import java.util.List;

public interface Todoservices {

    public Todo create(Todo todo);

    public List<Todo> getTodos();

    public Todo getByid(int id);

    public Todo updateTodo(int id, Todo todo);

    public Todo deleteTodoByid(int deleteid);
}
