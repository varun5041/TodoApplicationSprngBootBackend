package com.project.TodoApplication;

import com.project.TodoApplication.Models.Todo;
import com.project.TodoApplication.Repositories.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {
    @Autowired
    private TodoRepository repository;

    Logger logger = LoggerFactory.getLogger(TodoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);


	}

    @Override
    public void run(String... args) throws Exception {
////        System.out.println("Application started:");
////        JdbcTemplate template = repository.getTemplate();
////        logger.info("Templete Object Created:{}",template);
////        logger.info("Templete INFO{}",template);
//        //CREATE TODO
////        Todo todo = new Todo();
////        todo.setId(12345);
////        todo.setName("Timepass no 1");
////        todo.setContent("bohot timepass karna hai");
////        todo.setCurrentdate(new Date());
////        todo.setTododate(new Date());
////        todo.setStatus(false);
////        repository.saveTodo(todo);
//        //GET BY ID
////        repository.gettodo(12345);
//        //GETALL TODOS
//        repository.getAllTodos();
//
////      Todo newTODOTest = new Todo();
///     newTODOTest.setName("Go to Dmart");
///     newTODOTest.setContent("Buy groceries and stationary");
////
////            newTODOTest.setCurrentdate(new Date());
////            newTODOTest.setTododate(new SimpleDateFormat("dd/MM/yyyy").parse("09/08/2026"));
//            newTODOTest.setStatus(true);
//            repository.updateByID(8384247,newTODOTest);
//            repository.deletedByID(2058401);
    }
}
