package com.project.TodoApplication.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Todo {
    int id;
    String name;
    String content;
    boolean status;
    Date currentdate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date tododate;

    public Date getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(Date currentdate) {
        this.currentdate = currentdate;
    }

    public Date getTododate() {
        return tododate;
    }

    public void setTododate(Date tododate) {
        this.tododate = tododate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", currentdate=" + currentdate +
                ", tododate=" + tododate +
                '}';
    }
}
