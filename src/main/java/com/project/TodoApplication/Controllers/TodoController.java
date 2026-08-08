package com.project.TodoApplication.Controllers;

import com.project.TodoApplication.Models.Todo;
import com.project.TodoApplication.Repositories.TodoRepository;
import com.project.TodoApplication.Services.Implementations.TodoServiceImpl;
import com.project.TodoApplication.Services.Todoservices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private Todoservices service;
    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @PostMapping("/create")
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
        logger.info("POST /create");
        Todo todo1=service.create(todo);
        logger.info("todo Created{}" ,todo);
        return new ResponseEntity<>(todo1,HttpStatus.CREATED);
    }

    @GetMapping("/gettodos")
    public ResponseEntity<List<Todo>> getTodos(){
        logger.info("GET /gettodos");
        ResponseEntity<List<Todo>> response2 = new ResponseEntity<>(service.getTodos(),HttpStatus.OK);
        return response2;
    }

    @GetMapping("/getByid/{todoId}")
    public ResponseEntity<Todo> getTodoByid(@PathVariable("todoId") int id) throws ParseException {
        logger.info("GET /getByid/{}",id);
        Todo todo = service.getByid(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PutMapping("/update/{todoId}")
    public ResponseEntity<Todo> Updatetodo(@PathVariable("todoId") int id,@RequestBody Todo todo){
        logger.info("PUT /update/{}",id);
        Todo newTodo = service.updateTodo(id,todo);
        return new ResponseEntity<>(newTodo,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{deleteId}")
    public ResponseEntity<Todo> delete(@PathVariable("deleteId") int deleteid){
        logger.info("DELETE /delete/{}",deleteid);
        Todo deletedtodo =service.deleteTodoByid(deleteid);
        return new ResponseEntity<>(deletedtodo,HttpStatus.OK);

    }

//    @ExceptionHandler(NullPointerException.class)
//    public String NullPointerExceptionHandler(NullPointerException exception){
//        logger.error("Null Pointer Eception Encoundered");
//        return "Not Found" + exception.getMessage();
//    }
}
