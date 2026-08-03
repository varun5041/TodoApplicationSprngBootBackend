package com.project.TodoApplication.Controllers;

import com.project.TodoApplication.Models.Todo;
import com.project.TodoApplication.Repositories.TodoRepository;
import com.project.TodoApplication.Services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService service;

    @Autowired
    TodoRepository repository;
    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @PostMapping("/create")
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
        logger.info("todo Create called");
        service.create(todo);
        repository.saveTodo(todo);
        logger.info("todo Created{}" ,todo);
        ResponseEntity<Todo> response1 = new ResponseEntity<>(todo,HttpStatus.CREATED);
        return response1;
    }

    @GetMapping("/gettodos")
    public ResponseEntity<List<Todo>> getTodos(){
        logger.info("Get Todos Called!");
        ResponseEntity<List<Todo>> response2 = new ResponseEntity<>(service.getTodos(),HttpStatus.OK);
        return response2;
    }

    @GetMapping("/getByid/{todoId}")
    public ResponseEntity<Todo> getTodoByid(@PathVariable("todoId") int id) {
        logger.info("getTodoById Called");

        Todo todo = service.getByid(id);

        if(todo==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PutMapping("/update/{todoId}")
    public ResponseEntity<Todo> Updatetodo(@PathVariable("todoId") int id,@RequestBody Todo todo){
        logger.info("Update Todo called!");
        logger.info("request to update id {}",id);
        Todo newTodo = service.updateTodo(id,todo);
        return new ResponseEntity<>(newTodo,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{deleteId}")
    public ResponseEntity<Todo> delete(@PathVariable("deleteId") int deleteid){
        logger.info("Delete todo Called");
        Todo deletedtodo =service.deleteTodoByid(deleteid);
        return new ResponseEntity<>(deletedtodo,HttpStatus.OK);

    }

//    @ExceptionHandler(NullPointerException.class)
//    public String NullPointerExceptionHandler(NullPointerException exception){
//        logger.error("Null Pointer Eception Encoundered");
//        return "Not Found" + exception.getMessage();
//    }







}
