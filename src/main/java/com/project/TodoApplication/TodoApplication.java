package com.project.TodoApplication;

import com.project.TodoApplication.Repositories.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {
//    @Autowired
//    private TodoRepository repository;

    Logger logger = LoggerFactory.getLogger(TodoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);


	}

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Application started:");
//        JdbcTemplate template = repository.getTemplate();
//        logger.info("Templete Object Created:{}",template);
//        logger.info("Templete INFO{}",template);

    }
}
